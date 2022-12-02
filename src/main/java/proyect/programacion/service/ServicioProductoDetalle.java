package proyect.programacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.ProductoDetalle;
import proyect.programacion.repository.RepoProductoDetalle;

import java.util.List;
import java.util.Optional;


@Service
public class ServicioProductoDetalle {
    @Autowired
    private RepoProductoDetalle repoProductoDetalle;

    public ProductoDetalle guardar(ProductoDetalle productoDetalle){
        productoDetalle.getPedido().setDetalle(productoDetalle);
        return repoProductoDetalle.save(productoDetalle);
    }
    public boolean existePorId(Integer id){
        return repoProductoDetalle.existsById(id);
    }

    public ProductoDetalle encontrarPorID(Integer id){
        return repoProductoDetalle.findById(id).get();
    }

    public List<ProductoDetalle> encontrarTodosPorPedido(Pedido pedido){
        return repoProductoDetalle.findAllByPedido(pedido);
    }
    public void eliminarPorId(Integer id){
        repoProductoDetalle.deleteById(id);
    }

    public ProductoDetalle encontrarPorPedido(Pedido pedido){
        return repoProductoDetalle.findProductoDetalleByPedido(pedido);
    }


}
