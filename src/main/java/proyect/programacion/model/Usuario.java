package proyect.programacion.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userName;
    private String email;
    private String password;
    @OneToMany
    private List<Boleta> compras;

    //constructores
    public Usuario(){}
    public Usuario(String userName,String email,String password){
        this.userName=userName;
        this.email=email;
        this.password=password;
    }
    //getters
    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Boleta> getCompras() {
        return compras;
    }
    //setter

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompras(List<Boleta> compras) {
        this.compras = compras;
    }
}


