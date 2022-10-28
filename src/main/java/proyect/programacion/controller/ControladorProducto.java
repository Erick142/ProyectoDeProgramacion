package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proyect.programacion.model.Producto;
import proyect.programacion.repository.RepoProducto;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
public class ControladorProducto {
    @Autowired
    private RepoProducto repoProducto;

    @GetMapping("/productos")
    public List<Producto> usuarios(){
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
                        @RequestParam(name = "stock",    required = true) int stock,
                        @RequestParam(name = "precio",  required = true) int precio,
                        @RequestParam(name = "categoria",  required = true) String categoria){
        repoProducto.save(new Producto(nombre,descripcion,stock,precio,categoria));
        return "confirmacion";
    }



}
