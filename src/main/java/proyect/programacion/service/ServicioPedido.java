package proyect.programacion.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect.programacion.model.Pedido;
import proyect.programacion.repository.RepoPedido;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public abstract class ServicioPedido implements RepoPedido{
    @Autowired
    private RepoPedido repoPedido;


    public Iterable<Pedido> findAllPedidos() {
        Iterable<Pedido> listadoPedidos = repoPedido.findAll();
        return listadoPedidos;
    }
}

