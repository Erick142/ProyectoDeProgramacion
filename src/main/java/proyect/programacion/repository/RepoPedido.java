package proyect.programacion.repository;

import org.springframework.data.repository.CrudRepository;
import proyect.programacion.model.Pedido;

public interface RepoPedido extends CrudRepository<Pedido,Integer> {
}
