package com.dev.tienda.controladores;

import com.dev.tienda.modelos.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dev.tienda.repositorios.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Controller("/")
public class InicioControlador {
	
	@Autowired
	private UsuarioRepository repositorio;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserDetailsService service;

	@GetMapping("/")
	public String root() {
		return "redirect:/index";
	}
	@GetMapping("/index")
	public String Inicio() {
		return "index";
	}

	//@GetMapping("/products")
	//public String productos(Model modelo) {
	//	List<Producto> productos = new ArrayList<>();
//
	//	for (int i = 0; i < 5; i++){
	//		Producto p = new Producto();
	//		p.setFotoPrincipal("assets/imagen/foto1.jpeg");
	//		productos.add(p);
	//	}
//
	//	modelo.addAttribute("imagenes",productos);
	//	return "products";
	//}

}
