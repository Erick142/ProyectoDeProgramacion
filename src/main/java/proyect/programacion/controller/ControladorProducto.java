package proyect.programacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyect.programacion.model.Producto;
import proyect.programacion.repository.RepoProducto;
import java.util.List;

@Controller
public class ControladorProducto {
    @Autowired
    private RepoProducto repoProducto;

    @GetMapping("/productos")
    public List<Producto> listadoproductos(){
        return (List<Producto>) repoProducto.findAll();
    }


    @GetMapping("/listaproductos")
    public String productos(Model model){
        model.addAttribute("listaProductos",repoProducto.findAll());
        return "GestionProductos";
    }


    @PostMapping("/nuevoProducto")
    public String crear(@RequestParam(name = "nombre") String nombre,
                        @RequestParam(name = "descripcion") String descripcion,
                        @RequestParam(name = "stock") int stock,
                        @RequestParam(name = "precio") int precio,
                        @RequestParam(name = "categoria") String categoria){
        repoProducto.save(new Producto(nombre,descripcion,stock,precio,categoria));
        return "GestionProductos";
    }

    @PostMapping("/editarproducto/{id}")
    public String editar(Model model,
                         @PathVariable(value = "id") int id,
                         @RequestParam(name = "nombre", required = true) String nombre,
                         @RequestParam(name = "descripcion", required = true) String descripcion,
                         @RequestParam(name = "stock",    required = true) int stock,
                         @RequestParam(name = "precio",  required = true) int precio,
                         @RequestParam(name = "categoria",  required = true) String categoria){

        model.addAttribute("productoAeditar",repoProducto.findById(id));
        Producto producto=repoProducto.findById(id).get();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setStock(stock);
        producto.setPrecio(precio);
        producto.setCategoria(categoria);
        repoProducto.save(producto);

        return "GestionProductos";
    }

    @GetMapping("/borrarproducto/{id}")
    public String borrar(@PathVariable(value = "id") int id) {
        repoProducto.deleteById(id);
        return "GestionProductos";
    }


}
