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
import proyect.programacion.repository.RepoUsuario;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ControladorUsuario {

    @Autowired
    private RepoUsuario repoUsuario;

    @GetMapping("/usuarios")
    public List<Usuario> usuarios(Model model){
        return (List<Usuario>) repoUsuario.findAll();
    }

    /*
    //con vistas
     @GetMapping("/usuarios")
    public String pedidos(Model model){
        model.addAttribute("pedidos",repoUsuario.findAll());
        return "pedidos";
    }
     */


    @PostMapping("/registrarse")
    public String crear(@RequestParam(name = "nombre", required = true, defaultValue = "null") String nombre,
                        @RequestParam(name = "apPaterno", required = true, defaultValue = "null") String apPaterno,
                        @RequestParam(name = "apMaterno", required = true, defaultValue = "null") String apMaterno,
                        @RequestParam(name = "email", required = true, defaultValue = "null") String email,
                        @RequestParam(name = "direccion", required = true, defaultValue = "null") String direccion,
                        @RequestParam(name = "telefono", required = true, defaultValue = "null") String telefono,
                        Principal principal) throws IOException {
        repoUsuario.save(new Usuario(nombre,apPaterno, apMaterno,  email,  direccion, telefono));
        return "confirmacion";
    }


}
