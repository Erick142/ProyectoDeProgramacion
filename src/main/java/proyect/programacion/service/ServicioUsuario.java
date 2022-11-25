package proyect.programacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Usuario;
import proyect.programacion.repository.RepoPedido;
import proyect.programacion.repository.RepoUsuario;

@Service
public class ServicioUsuario {
    @Autowired
    private RepoUsuario repoUsuario;

    public Iterable<Usuario> findAllUsuarios() {
        Iterable<Usuario> listadoUsuarios = repoUsuario.findAll();
        return listadoUsuarios;
    }
}