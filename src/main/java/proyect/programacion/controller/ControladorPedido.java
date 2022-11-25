package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Producto;
import proyect.programacion.model.Usuario;
import proyect.programacion.repository.RepoPedido;
import proyect.programacion.service.ServicioPedido;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ControladorPedido {
    @Autowired
    private ServicioPedido servicioPedido;

    @GetMapping("/pedidos")
    public List<Pedido> pedidos(){
        return (List<Pedido>) servicioPedido.findAllPedidos();
    }

    @GetMapping("/listapedidos")
    public String pedidos(Model model){
        model.addAttribute("listaPedidos",servicioPedido.findAllPedidos());
        return "GestionPedidos";
    }


    @PostMapping("/nuevopedido")
    public String crear(@RequestParam(name = "producto") Producto producto,
                        @RequestParam(name = "cantidad") int cantidad,
                        @RequestParam(name = "usuario") Usuario usuario){
        servicioPedido.guardar(new Pedido(usuario,producto,cantidad));
        return "GestionPedidos";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable(value = "id") int id,
                         @RequestParam(name = "producto") Producto producto,
                        @RequestParam(name = "cantidad") int cantidad){

        Pedido pedido=servicioPedido.encontrarPorId(id).get();
        pedido.setProducto(producto);
        pedido.setCantidad(cantidad);
        servicioPedido.guardar(pedido);

        return "GestionPedidos";
    }

    @DeleteMapping("/borrar/{id}")
    public String borrar(@PathVariable(value = "id") int id) {
        servicioPedido.eliminarPorId(id);
        return "redirect:/listapedidos";
    }



}


