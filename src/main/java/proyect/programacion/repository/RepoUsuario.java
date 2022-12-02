package proyect.programacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Usuario;

import javax.transaction.Transactional;

@Repository
public interface RepoUsuario extends CrudRepository<Usuario,String> {

    void deleteByEmail(String email);

}
