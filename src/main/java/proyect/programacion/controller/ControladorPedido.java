package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Producto;
import proyect.programacion.model.Usuario;
import proyect.programacion.repository.RepoPedido;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ControladorPedido {
    @Autowired
    private RepoPedido repoPedido;

    @GetMapping("/pedidos")
    public List<Pedido> pedidos(){
        return (List<Pedido>) repoPedido.findAll();
    }

    @GetMapping("/listapedidos")
    public String pedidos(Model model){
        model.addAttribute("listaPedidos",repoPedido.findAll());
        return "GestionPedidos";
    }


    @PostMapping("/nuevoPedido")
    public String crear(@RequestParam(name = "producto", required = true) Producto producto,
                        @RequestParam(name = "cantidad", required = true) int cantidad,
                        @RequestParam(name = "fecha",    required = true) LocalDate fecha,
                        @RequestParam(name = "usuario",  required = true) Usuario usuario){
        repoPedido.save(new Pedido(usuario,producto,cantidad,fecha));
        return "CRUDpedidos";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable(value = "id") int id,
                         @RequestParam(name = "producto", required = true) Producto producto,
                        @RequestParam(name = "cantidad", required = true) int cantidad,
                        @RequestParam(name = "fecha",    required = true) LocalDate fecha,
                        @RequestParam(name = "usuario",  required = true) Usuario usuario){

        Pedido pedido=repoPedido.findById(id).get();
        pedido.setProducto(producto);
        pedido.setCantidad(cantidad);
        repoPedido.save(pedido);

        return "CRUDpedidos";
    }

    @DeleteMapping("/borrar/{id}")
    public String borrar(@PathVariable(value = "id") int id) {
        repoPedido.deleteById(id);
        return "redirect:/listapedidos";
    }



}


