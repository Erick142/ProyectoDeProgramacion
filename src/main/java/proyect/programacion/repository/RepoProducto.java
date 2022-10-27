package proyect.programacion.repository;

import org.springframework.data.repository.CrudRepository;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Producto;

public interface RepoProducto extends CrudRepository<Producto,Integer> {
}
