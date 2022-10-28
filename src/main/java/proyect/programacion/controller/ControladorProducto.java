package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyect.programacion.model.Producto;
import proyect.programacion.repository.RepoProducto;
import proyect.programacion.repository.RepoUsuario;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorProducto {
    @Autowired
    private RepoProducto repoProducto;
    @Autowired
    private RepoUsuario repoUsuario;

    @GetMapping("/productos")
    public List<Producto> usuarios() {
        return (List<Producto>) repoProducto.findAll();
    }

    /*
    //con vistas
    @GetMapping("/productos")
    public String pedidos(Model model){
        model.addAttribute("productos",repoProducto.findAll());
        return "productos";
    }
     */

    @PostMapping("/nuevoProducto")
    public String crear(@RequestParam(name = "nombre", required = true, defaultValue = "null") String nombre,
                        @RequestParam(name = "descripcion", required = true, defaultValue = "null") String descripcion,
                        @RequestParam(name = "stock", required = true) int stock,
                        @RequestParam(name = "precio", required = true) int precio,
                        @RequestParam(name = "categoria", required = true) String categoria) {
        repoProducto.save(new Producto(nombre, descripcion, stock, precio, categoria));
        return "confirmacion";
    }

    @GetMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("carrito") == null) {
            session.setAttribute("carrito", new HashMap<Integer, Integer>());
        }
        return "index";
    }
    @GetMapping("/nuevoProducto")
    public String nuevo(HttpSession session){
        if (session.getAttribute("usuarioEmail")==null){
            return "redirect:http://localhost:8080/usuario/login";
        }
        if (!repoUsuario.findById(session.getAttribute("usuarioEmail").toString()).get().isEsAdmin()){
            return "redirect:http://localhost:8080/catalogo";
        }
        return "crearProducto";
    }

    @PostMapping("/añadir")
    @ResponseBody
    public boolean añadirAlCarro(HttpSession session,
                                 @RequestBody HashMap<String, Integer> pregunta) {
        if (session.getAttribute("carrito") == null) {
            session.setAttribute("carrito", new HashMap<Integer, Integer>());
        }
        HashMap<Integer, Integer> carrito = (HashMap) session.getAttribute("carrito");
        if (carrito.containsKey(pregunta.get("key"))){
            if (carrito.get(pregunta.get("key"))<repoProducto.findById(pregunta.get("key")).get().getStock()){
                carrito.put(pregunta.get("key"),carrito.get(pregunta.get("key"))+1);
            }
            else return false;
        }else {
            carrito.put(pregunta.get("key"),1);
        }
        System.out.println(carrito);
        return true;
    }
    @GetMapping("/catalogo")
    public String catalogo(Model model,HttpSession session){
        model.addAttribute("productos",repoProducto.findAll());
        if (session.getAttribute("carrito") == null) {
            session.setAttribute("carrito", new HashMap<Integer, Integer>());
        }
        return "catalogo";
    }
    @GetMapping("/carrito")
    public String carrito(Model model, HttpSession session){
        model.addAttribute("repo", repoProducto);
        return "carrito";
    }

}
