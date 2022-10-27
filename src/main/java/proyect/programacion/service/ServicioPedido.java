package proyect.programacion.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect.programacion.repository.RepoPedido;

import javax.persistence.*;
import java.time.LocalDate;

@Service
public class ServicioPedido {
    @Autowired
    private RepoPedido repoPedido;


    public Object findAllClientes() {
        return null;
    }

    public Object findAllProductos() {
        return null;
    }
}

