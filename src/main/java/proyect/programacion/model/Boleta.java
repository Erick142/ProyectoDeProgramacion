package proyect.programacion.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter

@Table(name = "Boletas")
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int total;
    private int subtotal;
    private static final double iva = 0.19;
    private int descuento;
    private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Boleta(Pedido pedido) {
        this.pedido=pedido;
        this.usuario=pedido.getUsuario();
        this.subtotal=pedido.getDetalle().getCantidad()*pedido.getDetalle().getProducto().getPrecio();
        this.descuento=pedido.getDescuento();
        this.total=subtotal-descuento;
        this.fecha=pedido.getFecha();
    }

    public Boleta() {}
}




/*
////agregar todos los parametros en constructor boleta para luego pasar solo uno
//agregar fecha en pedido para luego pasar un solo argumento

 this.pedido=pedido;
        this.usuario=pedido.getUsuario();
        this.total=pedido.getCantidad()*pedido.getProducto().getPrecio();
        this.fecha=pedido.getFecha();
 */