package proyect.programacion.model;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Carro {
    private ArrayList<Pedido> productosEnElCarro;

    public Carro() {
        this.productosEnElCarro=new ArrayList<>();
    }

    public ArrayList<Pedido> getProductosEnElCarro() {
        return productosEnElCarro;
    }

    public Pedido añadirAlCarro(Producto producto){
        //haySuficienteStock
        if (productosEnElCarro.stream().anyMatch(pedido -> pedido.getId().equals(producto.getId()))){
            //return tratarDeAñadirStock(producto);
            if (obtener(producto).getCantidad()<=producto.getStock()){
                añadirUno(producto);
            }

        }
        else {

            productosEnElCarro.add(new Pedido(producto.getId(),producto));
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
        productosEnElCarro.remove(pedido);
        pedido.setCantidad(pedido.getCantidad()+1);
        productosEnElCarro.add(pedido);
        return pedido;
    }
    public Pedido obtener(Producto producto){
        Stream<Pedido> productoEnCarroStream=productosEnElCarro.stream().filter(productoEnCarro1 -> productoEnCarro1.getId().equals(producto.getId()));
        return productoEnCarroStream.findFirst().get();
    }
    public Pedido quitarUno(Producto producto){
        Pedido pedido=obtener(producto);
        productosEnElCarro.remove(pedido);
        pedido.setCantidad(pedido.getCantidad()-1);
        productosEnElCarro.add(pedido);
        return pedido;
    }
    public void eliminarDelCarro(Producto producto){
        Pedido pedido=obtener(producto);
        productosEnElCarro.remove(pedido);
    }
    public void vaciarCarro(){
        productosEnElCarro.clear();
    }
}
