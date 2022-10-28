package proyect.programacion.repository;

import org.springframework.data.repository.CrudRepository;
import proyect.programacion.model.Compra;
import proyect.programacion.model.Usuario;

import java.util.List;

public interface RepoCompra extends CrudRepository<Compra,Integer> {
    List<Compra> findAllByUsuario(Usuario usuario);
}
