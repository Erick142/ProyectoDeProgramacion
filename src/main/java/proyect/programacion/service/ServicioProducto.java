package proyect.programacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect.programacion.model.Producto;
import proyect.programacion.repository.RepoProducto;
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

    public Producto encontrarPorNombre(String nombre){return repoProducto.findByNombre(nombre);}

}