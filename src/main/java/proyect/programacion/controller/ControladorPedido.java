package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Producto;
import proyect.programacion.model.Usuario;
import proyect.programacion.repository.RepoPedido;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;

public class ControladorPedido {
    @Autowired
    private RepoPedido repoPedido;

    @GetMapping("/pedido")
    public String pedidos(Model model, Principal principal){

        model.addAttribute("pedidos",repoPedido.findById(id).get().getProducto());
        return "pedidos";
    }

    @PostMapping("/nuevoPedido")
    public String crear(@RequestParam(name = "producto", required = true, defaultValue = "null") Producto producto,
                        @RequestParam(name = "cantidad", required = true, defaultValue = "null") int cantidad,
                        @RequestParam(name = "fecha",    required = true) LocalDate fecha,
                        @RequestParam(name = "usuario",  required = true) Usuario usuario,
                        Principal principal) throws IOException {
        repoPedido.save(new Pedido(usuario,producto,cantidad,fecha));
        return "confirmacion";
    }



}
