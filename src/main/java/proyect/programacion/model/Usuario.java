package proyect.programacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String email;
    private String direccion;
    private String telefono;

    public Usuario(String nombre, String apPaterno, String apMaterno, String email, String direccion,String telefono) {
    this.nombre=nombre;
        this.apPaterno=apPaterno;
        this.apMaterno=apMaterno;
        this.email=email;
        this.direccion=direccion;
        this.telefono=telefono;

    }
}


