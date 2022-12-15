package proyect.programacion.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DescuentoTest {

    @Test
    public void nuevoCupon() throws Exception {
        Descuento descuento = new Descuento();
        descuento.setTipo("porcentaje");
        descuento.setTieneMinimo(true);
        descuento.setMinimoCompra(20000);
        descuento.setPorcentaje(25);
        descuento.setCodigo("DESCUENTO25");

        assertNotEquals("DESCUENTO20",descuento.getCodigo());
        assertTrue(descuento.isTieneMinimo());
        assertEquals(20000, descuento.getMinimoCompra());
    }


    @Test
    public void descuentoPorcentajeSinMinimo() throws Exception {
        Descuento descuento = new Descuento();
        descuento.setTipo("porcentaje");
        descuento.setPorcentaje(25);
        double precio = 10000;
        descuento.setTieneMinimo(false);

        double total = descuento.aplicarDescuento(precio,descuento);
        assertEquals(7500, total);
        assertNotEquals(10000,total);
        assertFalse(descuento.isTieneMinimo());
    }

    @Test
    public void descuentoPorcentajeConMinimo() throws Exception {
        Descuento descuento = new Descuento();
        descuento.setTipo("porcentaje");
        descuento.setPorcentaje(8);
        double precio = 40000;
        descuento.setTieneMinimo(true);
        descuento.setMinimoCompra(50000);

        double total = descuento.aplicarDescuento(precio,descuento);

        assertEquals(3200, descuento.calcularMontoDescuento(precio,descuento.getTipo()));
        assertFalse(descuento.cumpleMinimoCompra(precio,descuento));
        assertNotEquals(36800,total);

    }

    @Test
    public void descuentoMontoFijoSinMinimo() {
        Descuento descuento = new Descuento();
        descuento.setTipo("monto");
        descuento.setTieneMinimo(false);
        descuento.setMonto(5000);
        double precio = 10000;
        double total = descuento.aplicarDescuento(precio, descuento);

        assertEquals(5000, total);
        assertFalse(descuento.isTieneMinimo());
        assertNotEquals(10000,total);
    }

    @Test
    public void descuentoMontoFijoConMinimo() {
        Descuento descuento = new Descuento();
        descuento.setTipo("monto");
        descuento.setTieneMinimo(true);
        descuento.setMinimoCompra(20000);
        descuento.setMonto(5000);
        double precio = 19000;
        double total = descuento.aplicarDescuento(precio,descuento);

        assertEquals(19000, total);
        assertNotEquals(14000,total);
        assertTrue(descuento.isTieneMinimo());

    }


}