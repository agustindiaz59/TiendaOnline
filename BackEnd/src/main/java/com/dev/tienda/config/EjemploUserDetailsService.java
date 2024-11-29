package com.dev.tienda.config;

import com.dev.tienda.modelos.Usuario;
import com.dev.tienda.repositorios.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Optional;

//@Service
public class EjemploUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository repositorio;


    /**
     Este metodo utiliza spring para buscar el usuario en la base de datos o repositorio
     Se usa en conjunto a DaoAuthenticationProvider
     @see org.springframework.security.authentication.dao.DaoAuthenticationProvider

     @param username el nombre de usuario, email o campo por el cual se buscara coincidencia
     @return UserDetails objeto que representa un usuario, con los datos de la BBDD
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = repositorio.findByNombre(username);

        if(usuario.isPresent()){
            UserDetails springUser = User.builder() //UserDetails es el objeto que spring usa para comprobar credenciales, permisos y roles
                    .username(usuario.get().getNombre())
                    .password(usuario.get().getContrasenia())
                    .build();
            return springUser;

        }else{
            System.out.println("Usuario no encontrado");
            return null;
        }
    }
}
