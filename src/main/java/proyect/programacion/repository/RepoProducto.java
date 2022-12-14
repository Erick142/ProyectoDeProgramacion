package proyect.programacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Producto;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RepoProducto extends CrudRepository<Producto,Integer> {
}
