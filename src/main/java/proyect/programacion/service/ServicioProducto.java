package proyect.programacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Producto;
import proyect.programacion.model.Usuario;
import proyect.programacion.repository.RepoPedido;
import proyect.programacion.repository.RepoProducto;
import proyect.programacion.repository.RepoUsuario;

@Service
public abstract class ServicioProducto implements RepoProducto {
    @Autowired
    private RepoProducto repoProducto;

    public Iterable<Producto> findAllProductos() {
        Iterable<Producto> listadoProductos = repoProducto.findAll();
        return listadoProductos;
    }
}