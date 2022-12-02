package proyect.programacion.model;

import javax.persistence.*;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Producto producto;
    @ManyToOne
    private Usuario usuario;
    @Lob
    private String comentario;


}
