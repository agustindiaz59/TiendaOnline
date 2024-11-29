package com.dev.tienda.controladores;

import com.dev.tienda.repositorios.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller("/")
public class InicioControlador{

	@Autowired
	private IProductoRepository repo;

	@GetMapping("/")
	public String root() {
		return "redirect:/index";
	}

	@GetMapping("/index")
	public String Inicio() {

		return "index";
	}

}
