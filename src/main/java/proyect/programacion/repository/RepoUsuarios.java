package proyect.programacion.repository;

import org.springframework.data.repository.CrudRepository;
import proyect.programacion.model.Usuario;

public interface RepoUsuarios extends CrudRepository<Usuario,String> {
}
