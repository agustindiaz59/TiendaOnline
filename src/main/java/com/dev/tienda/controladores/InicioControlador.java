package com.dev.tienda.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller("/")
public class InicioControlador {

	@GetMapping("/")
	public String root() {
		return "redirect:/index";
	}
	@GetMapping("/index")
	public String Inicio() {
		return "index";
	}


}
