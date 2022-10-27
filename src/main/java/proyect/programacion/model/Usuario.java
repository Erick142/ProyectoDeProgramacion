package proyect.programacion.model;
import javax.persistence.*;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Usuario {
    @Id
    private String email;
    private String nombreDeUsuario;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String direccion;
    private String telefono;
    private String contraseña;
    private boolean esAdmin;

    public Usuario(String email, String nombreDeUsuario, String nombre, String apPaterno, String apMaterno, String direccion, String telefono, String contraseña) {
        this.email=email;
        this.nombreDeUsuario=nombreDeUsuario;
        this.nombre=nombre;
        this.apPaterno=apPaterno;
        this.apMaterno=apMaterno;
        this.direccion=direccion;
        this.telefono=telefono;
        this.contraseña=contraseña;
        esAdmin=false;
    }
}




