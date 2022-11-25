package proyect.programacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int cantidad;

    @JoinColumn(name = "producto_id")
    @ManyToOne
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Pedido(Usuario usuario, Producto producto, int cantidad) {
        this.usuario=usuario;
        this.producto=producto;
        this.cantidad=cantidad;

    }

    public Pedido(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }
}
