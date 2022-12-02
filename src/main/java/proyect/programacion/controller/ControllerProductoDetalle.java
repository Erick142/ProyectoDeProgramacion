package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import proyect.programacion.service.ServicioProductoDetalle;

@Controller
public class ControllerProductoDetalle {
    @Autowired
    private ServicioProductoDetalle servicioProductoDetalle;
}
