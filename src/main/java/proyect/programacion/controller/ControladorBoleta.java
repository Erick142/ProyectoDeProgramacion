package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import proyect.programacion.model.Boleta;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.ProductoDetalle;
import proyect.programacion.model.Usuario;
import proyect.programacion.service.ServicioBoleta;
import proyect.programacion.service.ServicioUsuario;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class ControladorBoleta {

    @Autowired
    private ServicioBoleta servicioBoleta;
    @Autowired
    private ServicioUsuario servicioUsuario;

    @GetMapping("/boletas")
    public void listarBoletas(Model model){
        model.addAttribute("boletas",servicioBoleta.verBoletas());
    }

    @GetMapping("/buscarboletaporid")
    public Optional<Boleta> buscarBoletaPorID(@PathVariable(value = "id") int id){
        Optional<Boleta> boleta = servicioBoleta.encontrarPorId(id);
        return boleta;
    }

    @GetMapping("/buscarboletaporusuario")
    public Optional<Boleta> buscarBoletaPorUsuario(Principal principal){
        Usuario usuario = servicioUsuario.encontrarUsuarioPorEmail(principal.getName());
        Optional<Boleta> boleta = servicioBoleta.encontrarPorUsuario(usuario);
        return boleta;
    }

    @PostMapping("/nuevaboleta")
    public String nuevaBoleta(Pedido p, Usuario u, int total, int subtotal, int descuento, LocalDate fecha){
        servicioBoleta.nuevaBoleta(p);
        return "verboleta";
    }

    /*
    @PostMapping("/reemplazarboleta")
    public String boletaCambio(@PathVariable(value = "id") int id,
                               Boleta boletaNueva){
        servicioBoleta.reemplazarBoleta(pedido,boletaNueva);
        return "verboleta";
    }

     */

}






/*
    @PostMapping("/nuevaboleta")
    public String nuevaBoleta(ProductoDetalle p, Usuario u){
        servicioBoleta.nuevaBoleta(p,u);
        return "verboleta";
    }

 */

