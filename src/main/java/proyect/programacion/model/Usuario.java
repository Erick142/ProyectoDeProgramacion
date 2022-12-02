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
    private String email;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String direccion;
    private String telefono;
    private String password;

}


