package proyect.programacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.*;
import proyect.programacion.model.*;
import proyect.programacion.service.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class Test1 {
    @Autowired
    private ServicioUsuario servicioUsuario;
    @Autowired
    private ServicioPedido servicioPedido;
    @Autowired
    private ServicioProductoDetalle servicioProductoDetalle;
    @Autowired
    private ServicioProducto servicioProducto;

    @BeforeEach
    public void before(){
        if (!servicioUsuario.existe("ejemplo@123")){
            Usuario user=servicioUsuario.guardar(new Usuario("erick","w","w","ejemplo@123","s","1","123"));
            servicioPedido.guardar(new Pedido(user,false));
            servicioProducto.guardar(new Producto("consola","una consola",10,20000,"consolas"));
            servicioProducto.guardar(new Producto("bulbasaur","pokemon",4,10000,"serie pokemon"));
        }
    }

    @Test
    @DisplayName("añadir producto a pedido")
    public void test1(){
        Producto producto=servicioProducto.encontrarPorID(1).get();
        Producto producto2=servicioProducto.encontrarPorID(2).get();
        Usuario user=servicioUsuario.encontrarUsuarioPorEmail("ejemplo@123");
        ProductoDetalle detalle=servicioPedido.agregar(new ProductoDetalle(servicioPedido.encontrarPedidoActual(user),producto,3));
        assertEquals(servicioPedido.obtenerContenido(servicioPedido.encontrarPedidoActual(user)).size(),1);
        assertTrue(servicioPedido.obtenerContenido(servicioPedido.encontrarPedidoActual(user)).get(0).getCantidad()==3);
        detalle.setCantidad(4);
        servicioPedido.agregar(detalle);
        assertEquals(servicioPedido.obtenerContenido(servicioPedido.encontrarPedidoActual(user)).size(),1);
        assertTrue(servicioPedido.obtenerContenido(servicioPedido.encontrarPedidoActual(user)).get(0).getCantidad()==4);
        ProductoDetalle detalle2=servicioPedido.agregar(new ProductoDetalle(servicioPedido.encontrarPedidoActual(user),producto2,2));
        servicioPedido.agregar(detalle2);
        assertEquals(servicioPedido.obtenerContenido(servicioPedido.encontrarPedidoActual(user)).size(),2);

    }
}
