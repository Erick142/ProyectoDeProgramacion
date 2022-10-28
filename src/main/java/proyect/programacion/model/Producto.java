package proyect.programacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private int stock;
    private int precio;
    private String categoria;

    public Producto(String nombre, int cantidad, String descripcion,int stock,int precio,String categoria) {
        this.nombre=nombre;
        this.descripcion=descripcion;
        this. stock=stock;
        this.precio=precio;
        this.categoria=categoria;
    }
}
