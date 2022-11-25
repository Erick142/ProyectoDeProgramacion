package proyect.programacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyect.programacion.model.Pedido;
import javax.transaction.Transactional;

@Repository
public interface RepoPedido extends CrudRepository<Pedido,Integer> {
}
