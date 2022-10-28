package proyect.programacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany
    private List<Pedido> pedidos;
    private LocalDate fecha;
    @ManyToOne
    private Usuario usuario;
    private int precioTotal;

    public Compra(List<Pedido> pedidos, Usuario usuario,int precioTotal) {
        this.pedidos = pedidos;
        this.usuario = usuario;
        this.precioTotal=precioTotal;
    }


}
