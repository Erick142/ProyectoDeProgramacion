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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int cantidad;
    @JoinColumn(name = "producto_id")
    @OneToOne
    private Producto producto;


    public Pedido( Producto producto, int cantidad) {
        this.producto=producto;
        this.cantidad=cantidad;

    }
}
