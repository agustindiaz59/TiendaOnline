package com.dev.tienda.controladores;

import com.dev.tienda.dto.UsuarioDTO;
import com.dev.tienda.modelos.Usuario;
import com.dev.tienda.repositorios.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioControlador {

    @Autowired
    private IUsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;


    @GetMapping("/login")
    public String login(@ModelAttribute("usuario") Usuario usuario){
        return "login";
    }

    //Seccion para registro de nuevos usuarios

    @GetMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") UsuarioDTO usuario){
        return "registro";
    }

    @PostMapping("/registrar")
    public String registro(UsuarioDTO usuario){

        if(!repository.existsByNombre(usuario.nombre())){
            Usuario nuevo = new Usuario();
            nuevo.setNombre(usuario.nombre());
            nuevo.setContrasenia(encoder.encode(usuario.contrasenia()));

            repository.save(nuevo);
            return "redirect:index";
        }
        else {
            return "redirect:registro"; //Reemplazar por pagina de error
        }
    }

}
