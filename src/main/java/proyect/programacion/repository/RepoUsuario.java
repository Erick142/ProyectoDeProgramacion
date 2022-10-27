package proyect.programacion.repository;

import org.springframework.data.repository.CrudRepository;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Usuario;

public interface RepoUsuario extends CrudRepository<Usuario,Integer> {
}
