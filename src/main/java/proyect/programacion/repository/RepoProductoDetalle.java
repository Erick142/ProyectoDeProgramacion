package proyect.programacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.ProductoDetalle;

import java.util.List;

@Repository
public interface RepoProductoDetalle extends CrudRepository<ProductoDetalle,Integer> {
    List<ProductoDetalle> findAllByPedido (Pedido pedido);

}
