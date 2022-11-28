package proyect.programacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.ProductoDetalle;
import proyect.programacion.model.Usuario;
import proyect.programacion.repository.RepoPedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioPedido {
    @Autowired
    private RepoPedido repoPedido;
    @Autowired
    private ServicioProductoDetalle servicioProductoDetalle;

    public Iterable<Pedido> findAllPedidos() {
        Iterable<Pedido> listadoPedidos = repoPedido.findAll();
        return listadoPedidos;
    }

    public Pedido guardar(Pedido pedido) {
        return repoPedido.save(pedido);
    }

    public Optional<Pedido> encontrarPorId(Integer id) {
        return repoPedido.findById(id);
    }

    public void eliminarPorId(Integer id) {
        repoPedido.deleteById(id);
    }

    public Pedido encontrarPedidoActual(Usuario usuario) {
       return repoPedido.findAllByUsuarioAndComprado(usuario,false).get(0);
    }
    public ArrayList<Pedido> encontrarPedidosCompletados(Usuario usuario){
        ArrayList<Pedido> array=new ArrayList();
        array=repoPedido.findAllByUsuarioAndComprado(usuario,true);
        return array;
    }
    public ProductoDetalle agregar(ProductoDetalle productoDetalle){
        Pedido pedidoActualutal=encontrarPedidoActual(productoDetalle.getPedido().getUsuario());
        //si esta en el pedido actual
        if (servicioProductoDetalle.encontrarTodosPorPedido(pedidoActualutal).stream().anyMatch(productoDetalle1 -> productoDetalle1.getProducto().getId().equals(productoDetalle.getProducto().getId()))){
            //obtener el Producto detalle guardado
            ProductoDetalle detalle=servicioProductoDetalle.encontrarTodosPorPedido(pedidoActualutal).stream().filter(productoDetalle1 -> productoDetalle1.getProducto().getId().equals(productoDetalle.getProducto().getId())).collect(Collectors.toList()).get(0);
            detalle.setCantidad(productoDetalle.getCantidad());
            return servicioProductoDetalle.guardar(detalle);
        }
        else {
            return servicioProductoDetalle.guardar(productoDetalle);
        }
    }
}