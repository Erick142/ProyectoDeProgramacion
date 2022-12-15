package proyect.programacion.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Descuento {
    private int porcentaje;
    private double monto;
    private boolean tieneMinimo;
    private double minimoCompra;
    private String tipo;
    private String codigo;

    public void setPorcentaje(int i) throws Exception {
        if(i>=0 && i<=100){
            this.porcentaje = i;
        }else{
            throw new Exception("El porcentaje debe estar entre 0 y 100%");
        }
    }

    public double calcularMontoDescuento(double precio,String tipo) {
        if (tipo == "porcentaje"){
            return precio * (porcentaje/(double)100);
        }else return precio - getMonto();
    }


    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean cumpleMinimoCompra(double precio, Descuento descuento){
        if (precio>=descuento.getMinimoCompra()){
            return true;
        }else{
            return false;
        }
    }

    public double aplicarDescuento(double precio, Descuento descuento) {

        double precioFinal = precio;

                if (cumpleMinimoCompra(precio, descuento)){
                    precioFinal = precio - descuento.calcularMontoDescuento(precio,descuento.getTipo());
                }

        return precioFinal;
    }
}
