package proyect.programacion.service;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import proyect.programacion.model.*;
import proyect.programacion.repository.RepoUsuario;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServicioBoletaTest {
    @Autowired
    ServicioUsuario servicioUsuario;
    @Autowired
    ServicioProducto servicioProducto;
    @Autowired
    ServicioBoleta servicioBoleta;
    @Autowired
    ServicioPedido servicioPedido;
    @Autowired
    ServicioProductoDetalle servicioProductoDetalle;

    @BeforeEach
    public void before(){
        if (!servicioUsuario.existe("test@email.com")){
            servicioUsuario.guardar(new Usuario("test@email.com","nombre", "apPaterno",
                    "apMaterno",  "direccion","telefono","password"));
        }

            Usuario usuario=servicioUsuario.encontrarUsuarioPorEmail("test@email.com");
            Producto producto=servicioProducto.guardar(new Producto("Plushie Charmander","pokemon",4,
                    10000,"serie pokemon"));


            servicioPedido.guardar(new Pedido(usuario,true));
            Pedido pedido = servicioPedido.encontrarPedidoPorId(1);
            ProductoDetalle detalle= new ProductoDetalle(pedido,producto,2);
            servicioProductoDetalle.guardar(detalle);
            pedido.setDetalle(servicioProductoDetalle.encontrarPorID(1));
            pedido.setFecha(LocalDate.now().minusDays(1));
            pedido.setDescuento(2000);
            servicioPedido.guardar(pedido);



    }


    @Test
    @DisplayName("generar nueva boleta")
    void nuevaBoleta() {

        Usuario usuario=servicioUsuario.encontrarUsuarioPorEmail("test@email.com");
        Pedido pedido = servicioPedido.encontrarPedidoPorId(1);
        LocalDate fechaActual = LocalDate.now();

        int precio=pedido.getDetalle().getProducto().getPrecio();
        int cantidad=pedido.getDetalle().getCantidad();
        int subtotal= cantidad * precio;

       servicioBoleta.nuevaBoleta(pedido);
       Optional<Boleta> boleta = servicioBoleta.encontrarPorUsuario(usuario);

       assertTrue(servicioBoleta.encontrarPorUsuario(usuario).isPresent());
       assertEquals(boleta.get().getSubtotal() , subtotal);
       assertEquals(pedido.getDescuento(),boleta.get().getDescuento());
       assertNotEquals(boleta.get().getFecha(),fechaActual);
    }

    /*
    @Test
    @DisplayName("reemplazar boleta")
    void reemplazarBoleta() {
        Usuario usuario=servicioUsuario.encontrarUsuarioPorEmail("test@email.com");
        Pedido pedido = servicioPedido.encontrarPedidoPorId(1);

        Boleta boletaOriginal= servicioBoleta.encontrarPorUsuario(usuario).get();

        boletaOriginal.getTotal();
        Boleta boletaNueva=servicioBoleta.nuevaBoleta(pedido,usuario,total,subtotal, descuento,fecha);

    }
     */




    @Test
    @DisplayName("guardar boleta")
    void guardar() {
    }

    @Test
    @DisplayName("ver listado de boletas")
    void verBoletas() {
    }

    @Test
    @DisplayName("encontrar una boleta por ID")
    void encontrarPorID() {
    }


    @Test
    @DisplayName("Encontrar una boleta por ID")
    void encontrarPorUsuario() {
    }



    @Test
    @DisplayName("")
    void imprimir() {
    }
}



/*
@Test
    @DisplayName("generar nueva boleta")
    void nuevaBoleta() {

        Usuario usuario=servicioUsuario.encontrarUsuarioPorEmail("test@email.com");
        Pedido pedido = servicioPedido.encontrarPedidoPorId(1);
        System.out.println(pedido);
        ProductoDetalle detalle=servicioProductoDetalle.encontrarPorID(1);
        LocalDate fechaActual = LocalDate.now();
        pedido.setFecha(LocalDate.now().minusDays(1));
        LocalDate fechaPedido = pedido.getFecha();
        int precio=detalle.getProducto().getPrecio();
        int cantidad=detalle.getCantidad();
        int subtotal= cantidad * precio;
        System.out.println(subtotal);
        pedido.setDescuento(2000);
        int descuento=pedido.getDescuento();
        System.out.println(descuento);
        int total=subtotal-pedido.getDescuento();
        System.out.println(total);

       servicioBoleta.nuevaBoleta(pedido,usuario,total,subtotal,descuento,fechaPedido);
       Optional<Boleta> boleta = servicioBoleta.encontrarPorUsuario(usuario);

       assertTrue(servicioBoleta.encontrarPorUsuario(usuario).isPresent());
       assertEquals(boleta.get().getSubtotal() , subtotal);
       assertEquals(pedido.getDescuento(),boleta.get().getDescuento());
       assertNotEquals(boleta.get().getFecha(),fechaActual);
    }
 */