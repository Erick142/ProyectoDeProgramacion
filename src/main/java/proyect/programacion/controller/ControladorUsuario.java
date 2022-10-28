package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyect.programacion.model.Usuario;
import proyect.programacion.repository.RepoUsuario;
import java.util.List;

@Controller
public class ControladorUsuario {

    @Autowired
    private RepoUsuario repoUsuario;

    @GetMapping("/usuarios")
    public List<Usuario> usuarios(){
        return (List<Usuario>) repoUsuario.findAll();
    }

    @GetMapping("/listausuarios")
    public String usuarios(Model model){
        model.addAttribute("listaUsuarios",repoUsuario.findAll());
        return "Gestionusuarios";
    }

    @PostMapping("/registrarse")
    public String crear(@RequestParam(name = "nombre", required = true) String nombre,
                        @RequestParam(name = "apPaterno", required = true) String apPaterno,
                        @RequestParam(name = "apMaterno", required = true) String apMaterno,
                        @RequestParam(name = "email", required = true) String email,
                        @RequestParam(name = "direccion", required = true) String direccion,
                        @RequestParam(name = "telefono", required = true) String telefono){
        repoUsuario.save(new Usuario(nombre,apPaterno, apMaterno,  email,  direccion, telefono));
        return "confirmacion";
    }


}
