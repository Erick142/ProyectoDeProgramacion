package proyect.programacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Producto;
import proyect.programacion.model.Usuario;
import proyect.programacion.repository.RepoPedido;
import proyect.programacion.repository.RepoProducto;
import proyect.programacion.repository.RepoUsuario;

import java.util.List;
import java.util.Optional;

@Service
public  class ServicioProducto {
    @Autowired
    private RepoProducto repoProducto;

    public Producto guardar(Producto producto){
        return repoProducto.save(producto);
    }
    public Iterable<Producto> encontrarTodo(){
        return repoProducto.findAll();
    }

    public Optional<Producto> encontrarPorID(Integer id){
        return repoProducto.findById(id);
    }
    public void eliminarPorId(int id){
        repoProducto.deleteById(id);
    }
}