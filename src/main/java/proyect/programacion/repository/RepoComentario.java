package proyect.programacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyect.programacion.model.Comentario;

@Repository
public interface RepoComentario extends CrudRepository<Comentario,Integer> {
}
