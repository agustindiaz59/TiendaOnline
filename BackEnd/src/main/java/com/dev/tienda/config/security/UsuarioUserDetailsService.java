package com.dev.tienda.config.security;

import com.dev.tienda.repositorios.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository repositorio;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return repositorio.findByNombre(username).get();

        //if(usuario != null){ //no es necesario ya que usuario implementa UserDetails
        //    UserDetails springUser = User.builder() //UserDetails es el objeto que spring usa para comprobar credenciales, permisos y roles
        //            .username(usuario.getNombre())
        //            .password(usuario.getContrasenia())
        //            .build();
        //    return springUser;
//
        //}else{
        //    System.out.println("Usuario no encontrado");
        //    return null;
        //}
    }
}
