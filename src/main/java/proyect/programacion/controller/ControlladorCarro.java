package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import proyect.programacion.model.Carro;
import proyect.programacion.model.Producto;
import proyect.programacion.service.ServicioPedido;
import proyect.programacion.service.ServicioProducto;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;
@Controller

public class ControlladorCarro {

    @Autowired
    private ServicioProducto servicioProducto;
    @GetMapping("/carrito")
    public String carrito(HttpSession session, Model model){
        if (session.getAttribute("carrito")==null){
            session.setAttribute("carrito", new Carro());
        }
        model.addAttribute("carrito",(Carro) session.getAttribute("carrito"));
        return "carrito";
    }
    @PostMapping("/añadircarro")
    @ResponseBody
    public HashMap añadirAlCarro(@RequestBody HashMap<String, Integer> hash, HttpSession session){
        Carro carrito=(Carro) session.getAttribute("carrito");
        Optional<Producto> producto=servicioProducto.encontrarPorID(hash.get("id"));
        HashMap<String,String>json=new HashMap<>();
        //si no existe
        if (!producto.isPresent()){
            json.put("nombre","null");
            json.put("code","2");
            return json;
        }
        json.put("nombre",producto.get().getNombre());
        //si el stock es menor a 1
        if(producto.get().getStock()<=0){
            json.put("code","3");
            return json;
        }
        //si esta en el carro
        if (carrito.estaEnElCarro(producto.get())) {
            json.put("code", "1");
            carrito.añadirAlCarro(producto.get());
            if (!carrito.haySuficienteStock(producto.get())) {
                carrito.quitarUno(producto.get());
                json.put("code", "3");
            }
            return json;
        }
        carrito.añadirAlCarro(producto.get());
        json.put("code","0");
        return json;
    }
}
