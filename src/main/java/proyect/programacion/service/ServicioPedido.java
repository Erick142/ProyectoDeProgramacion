package proyect.programacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect.programacion.model.Pedido;
import proyect.programacion.repository.RepoPedido;

@Service
public abstract class ServicioPedido implements RepoPedido{
    @Autowired
    private RepoPedido repoPedido;

    public Iterable<Pedido> findAllPedidos() {
        Iterable<Pedido> listadoPedidos = repoPedido.findAll();
        return listadoPedidos;
    }
}

