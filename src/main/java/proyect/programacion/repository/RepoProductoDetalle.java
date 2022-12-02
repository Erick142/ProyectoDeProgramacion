package proyect.programacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.ProductoDetalle;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepoProductoDetalle extends CrudRepository<ProductoDetalle,Integer> {
    List<ProductoDetalle> findAllByPedido (Pedido pedido);

    ProductoDetalle findProductoDetalleByPedido(Pedido pedido);


}
