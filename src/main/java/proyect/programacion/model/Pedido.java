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
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDate fecha;
    private boolean comprado;

    private int descuento;

    @OneToOne
    @JoinColumn(name = "PedidoDetalle_id")
    private ProductoDetalle detalle;

    @OneToOne
    @JoinColumn(name = "boleta_id")
    private Boleta boleta = null;

    public Pedido(Usuario usuario, boolean comprado) {
        this.usuario = usuario;
        this.comprado = comprado;
    }
}
