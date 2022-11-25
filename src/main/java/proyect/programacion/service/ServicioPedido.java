package proyect.programacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect.programacion.model.Pedido;
import proyect.programacion.repository.RepoPedido;

import java.util.Optional;

@Service
public class ServicioPedido {
    @Autowired
    private RepoPedido repoPedido;

    public Iterable<Pedido> findAllPedidos() {
        Iterable<Pedido> listadoPedidos = repoPedido.findAll();
        return listadoPedidos;
    }
    public Pedido guardar(Pedido pedido){
        return repoPedido.save(pedido);
    }

    public Optional<Pedido> encontrarPorId(Integer id){
        return repoPedido.findById(id);
    }
    public void  eliminarPorId(Integer id){
        repoPedido.deleteById(id);
    }
}

