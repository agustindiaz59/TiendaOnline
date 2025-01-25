package com.dev.tienda.controladores;

import com.dev.tienda.repositorios.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller("/")
public class InicioControlador{

	// TODO Actualizar el frontend desde github
	// TODO Crear esquemas SQL para flyway
	// TODO Agregar soporte para carrito de compras
	// TODO Cargar datos de prueba para la base de datos
	// TODO Habilitar la busqueda por nombre de producto
	// TODO Actualizar thymeleaf en nuevas plantillas

	@Autowired
	private IProductoRepository repo;

	@GetMapping("/")
	public String root() {
		return "redirect:/index";
	}

	@GetMapping("/index")
	public String Inicio() {

		return "index (2)";
	}

}
