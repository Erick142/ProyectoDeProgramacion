package proyect.programacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyect.programacion.model.Boleta;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Usuario;
import java.util.Optional;

@Repository
public interface RepoBoleta extends CrudRepository<Boleta,Integer> {
    Optional<Boleta> findByUsuario(Usuario usuario);

    Iterable<Boleta> findAll();

    Optional<Boleta> findById(Integer id);
    void deleteBoletaById(int id);

    Boleta findBoletaByPedido(Pedido pedido);

}
