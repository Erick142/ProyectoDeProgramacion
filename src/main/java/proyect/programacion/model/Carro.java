package proyect.programacion.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.stream.Stream;
@Entity
public class Carro {
    @Id
    @OneToOne
    private Usuario usuario;
    @OneToMany
    private ArrayList<Pedido> pedidos;

    public Carro() {
        this.pedidos=new ArrayList<>();
    }

    public ArrayList<Pedido> getProductosEnElCarro() {
        return pedidos;
    }

    public Pedido añadirAlCarro(Producto producto){
        //haySuficienteStock
        if (pedidos.stream().anyMatch(pedido -> pedido.getId().equals(producto.getId()))){
            //return tratarDeAñadirStock(producto);
            if (obtener(producto).getCantidad()<=producto.getStock()){
                añadirUno(producto);
            }

        }
        else {

            pedidos.add(new Pedido(producto.getId(),producto));
        }
        return obtener(producto);
    }
    /*
    public boolean estaEnElCarro(Producto producto){
        return productosEnElCarro.stream().anyMatch(productoEnCarro -> productoEnCarro.getId().equals(producto.getId()));
    }

     */
    /*
    public boolean haySuficienteStock(Producto producto){
        Pedido pedido=obtener(producto);
        return (pedido.getCantidad()<=producto.getStock())?true:false;
    }

     */
    public Pedido tratarDeAñadirStock(Producto producto){
        if (obtener(producto).getCantidad()<=producto.getStock()){
            añadirUno(producto);
        }
        return obtener(producto);
    }

    public Pedido añadirUno(Producto producto){
        Pedido pedido=obtener(producto);
        pedidos.remove(pedido);
        pedido.setCantidad(pedido.getCantidad()+1);
        pedidos.add(pedido);
        return pedido;
    }
    public Pedido obtener(Producto producto){
        Stream<Pedido> productoEnCarroStream=pedidos.stream().filter(productoEnCarro1 -> productoEnCarro1.getId().equals(producto.getId()));
        return productoEnCarroStream.findFirst().get();
    }
    public Pedido quitarUno(Producto producto){
        Pedido pedido=obtener(producto);
        pedidos.remove(pedido);
        pedido.setCantidad(pedido.getCantidad()-1);
        pedidos.add(pedido);
        return pedido;
    }
    public void eliminarDelCarro(Producto producto){
        Pedido pedido=obtener(producto);
        pedidos.remove(pedido);
    }
    public void vaciarCarro(){
        pedidos.clear();
    }
}
