package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyect.programacion.service.ServicioUsuario;

import javax.servlet.http.HttpSession;

@RequestMapping("/usuario")
@Controller
public class ControladorUsuario {
    @Autowired
    private ServicioUsuario servicioUsuario;


    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/validarSesion")
    public String validarSesion(@RequestParam(name = "email") String email,
                                @RequestParam(name = "contraseña") String contraseña, HttpSession session){
        if (!servicioUsuario.existe(email)){
            //el usuario no existe
            return "login";
        }
        if (!servicioUsuario.validarInicioDeSesion(email,contraseña)){
            //credenciales incorrectas
            return "login";
        }
        session.setAttribute("usuarioEmail",email);
        return "redirect:http://localhost:8080/catalogo";
    }
    @PostMapping("/registrarse")
    public String registrarce(@RequestParam(name = "nombreDeUsuario")String nombreDeUsuario,
                              @RequestParam(name = "nombre") String nombre,
                              @RequestParam(name = "apellidos")String apellidos,
                              @RequestParam(name = "email")String email,
                              @RequestParam(name = "telefono") String telefono,
                              @RequestParam(name = "direccion") String direccion,
                              @RequestParam(name = "contraseña")String contraseña){
        if (servicioUsuario.existe(email)){
            //error el usuario ya existe;
            return "login";
        }
        try {
            String apPaterno=apellidos.split(" ")[0];
            String apMaterno=apellidos.split(" ")[1];
            servicioUsuario.registrar(email,nombreDeUsuario,nombre,apPaterno,apMaterno,direccion,telefono,contraseña);
            return "login";
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return "login";
        }
    }
    @GetMapping("/listado")
    public String listado(Model model){
        model.addAttribute("listado",servicioUsuario.encontrarTodos());
        return "listadoDeUsuarios";
    }
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam(name = "eliminar")String email){
        servicioUsuario.eliminar(email);
        return "redirect:listado";
    }

}
