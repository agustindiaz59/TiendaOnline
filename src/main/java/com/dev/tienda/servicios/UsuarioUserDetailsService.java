package com.dev.tienda.servicios;

import com.dev.tienda.modelos.Usuario;
import com.dev.tienda.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repositorio;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repositorio.findByNombre(username);

        if(usuario != null){
            UserDetails springUser = User.builder() //UserDetails es el objeto que spring usa para comprobar credenciales, permisos y roles
                    .username(usuario.getNombre())
                    .password(usuario.getContrasenia())
                    .build();
            return springUser;

        }else{
            System.out.println("Usuario no encontrado");
            return null;
        }
    }
}
