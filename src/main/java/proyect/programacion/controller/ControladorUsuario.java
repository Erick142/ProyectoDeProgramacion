package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyect.programacion.model.Usuario;
import proyect.programacion.repository.RepoUsuario;
import proyect.programacion.service.ServicioUsuario;

import java.util.List;

@Controller
public class ControladorUsuario {

    @Autowired
    private ServicioUsuario servicioUsuario;


    @PostMapping("/registrarse")
    public String crear(@RequestParam(name = "nombre", required = true) String nombre,
                        @RequestParam(name = "apPaterno", required = true) String apPaterno,
                        @RequestParam(name = "apMaterno", required = true) String apMaterno,
                        @RequestParam(name = "email", required = true) String email,
                        @RequestParam(name = "direccion", required = true) String direccion,
                        @RequestParam(name = "telefono", required = true) String telefono,
                        @RequestParam(name = "password", required = true) String password) {
        servicioUsuario.guardar(new Usuario(nombre, apPaterno, apMaterno, email, direccion, telefono, password));
        return "confirmacion";
    }

    @PostMapping("/validar")
    public String validar(@RequestParam(name = "email") String email,
                          @RequestParam(name = "password") String password) {
        Usuario user = servicioUsuario.encontrarUsuarioPorEmail(email);
        if (servicioUsuario.validar(user)) {

        }
        return "redirect:/registrarse";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @GetMapping("/inicioSesion")
    public String inicioSesion(){
        return "login";
    }

}
