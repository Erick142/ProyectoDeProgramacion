package proyect.programacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyect.programacion.model.Usuario;
import proyect.programacion.repository.RepoUsuario;

import java.util.List;

@Service
public class ServicioUsuario {
    @Autowired
    private RepoUsuario repoUsuario;

    public boolean validarInicioDeSesion(String email,String contraseña){
        Usuario usuario=repoUsuario.findById(email).get();
        return usuario.getContraseña().equals(contraseña);
    }
    public boolean existe(String email){
        return repoUsuario.findById(email).isPresent();
    }
    public void registrar(String email,
                                String nombreDeUsuario,
                                String nombre,
                                String apPaterno,
                                String apMaterno,
                                String direccion,
                                String telefono,
                                String contraseña){

        Usuario usuario=new Usuario(email,nombreDeUsuario,nombre,apPaterno,apMaterno,direccion,telefono,contraseña);
        repoUsuario.save(usuario);
    }
    public void eliminar(String email){
        repoUsuario.delete(repoUsuario.findById(email).get());
    }
    public Iterable<Usuario> encontrarTodos(){
        return repoUsuario.findAll();
    }

}
