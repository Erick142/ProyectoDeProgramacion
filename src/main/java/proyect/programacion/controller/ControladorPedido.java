package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Producto;
import proyect.programacion.model.Usuario;
import proyect.programacion.repository.RepoPedido;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ControladorPedido {
    @Autowired
    private RepoPedido repoPedido;


    @GetMapping("/pedidos")
    public List<Pedido> pedidos(){
        return (List<Pedido>) repoPedido.findAll();
    }


    /*
    //con vistas
    @GetMapping("/pedidos")
    public String pedidos(Model model){
        model.addAttribute("pedidos",repoPedido.findAll());
        return "pedidos";
    }
     */


    @PostMapping("/nuevoPedido")
    public String crear(@RequestParam(name = "producto", required = true, defaultValue = "null") Producto producto,
                        @RequestParam(name = "cantidad", required = true, defaultValue = "null") int cantidad,
                        @RequestParam(name = "fecha",    required = true) LocalDate fecha,
                        @RequestParam(name = "usuario",  required = true) Usuario usuario){
        repoPedido.save(new Pedido(usuario,producto,cantidad,fecha));
        return "confirmacion";
    }

}


