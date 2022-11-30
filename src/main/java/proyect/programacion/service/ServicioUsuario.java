package proyect.programacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyect.programacion.model.Pedido;
import proyect.programacion.model.Usuario;
import proyect.programacion.repository.RepoPedido;
import proyect.programacion.repository.RepoUsuario;

@Service
public class ServicioUsuario {
    @Autowired
    private RepoUsuario repoUsuario;

    public Iterable<Usuario> findAllUsuarios() {
        Iterable<Usuario> listadoUsuarios = repoUsuario.findAll();
        return listadoUsuarios;
    }
    public Usuario encontrarUsuarioPorEmail(String email){
        return  repoUsuario.findById(email).get();
    }
    public Usuario guardar(Usuario usuario){
        return repoUsuario.save(usuario);
    }
    public boolean existe(String email){
        return repoUsuario.existsById(email);
    }
    public boolean validar(Usuario usuario){
        try{
            if (repoUsuario.existsById(usuario.getEmail())){
                Usuario usuarioEnBD=repoUsuario.findById(usuario.getEmail()).get();
                String password,email;
                password=usuario.getPassword();
                email=usuario.getEmail();
                if (email.equals(usuarioEnBD.getEmail())){
                    if (password.equals(usuarioEnBD.getPassword())){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}