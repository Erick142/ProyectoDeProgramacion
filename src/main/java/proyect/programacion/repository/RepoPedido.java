package proyect.programacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Usuario;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface RepoPedido extends CrudRepository<Pedido,Integer> {
    ArrayList<Pedido> findAllByUsuario(Usuario usuario);

    ArrayList<Pedido> findAllByUsuarioAndComprado(Usuario usuario,boolean comprado);
}
