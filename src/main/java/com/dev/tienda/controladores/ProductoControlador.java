package com.dev.tienda.controladores;

import com.dev.tienda.modelos.*;
import com.dev.tienda.modelos.Color;
import com.dev.tienda.repositorios.IColorRepository;
import com.dev.tienda.repositorios.IProductoRepository;

import com.dev.tienda.repositorios.ITallaRepository;
import com.dev.tienda.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.*;
import java.util.List;
import java.util.random.RandomGenerator;

@Controller
public class ProductoControlador {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private IColorRepository colorRepository;
    @Autowired
    private ITallaRepository tallaRepository;

    @Autowired
    private IProductoRepository repository;

    @GetMapping("/crearProductos")
    public String crearProductos(){
        Producto p = new Producto();

        Imagen img2 = new Imagen("remera.jpg");

        Talla t1 = new Talla(2F);
        Color c3 = new Color("azul", "#00FF00");


        p.setNombre("Remera");
        p.setDescripcion("buso para el invierno");
        p.setPrecio(1500F);
        p.getImagenes().add(img2);
        p.getColores().add(c3);
        p.getTallas().add(t1);

        productoService.guardar(p);
        return "forward:/products";
    }

    @GetMapping("/products")
    public String listaDeProductos(Model modelo){
        List<Producto> productos = productoService.traerTodos();
        

        modelo.addAttribute("productos",productos);

        return "products";
    }

    @GetMapping("/vistaproducts/{nombre}")
    public String detalleDeProducto(Model modelo, @PathVariable("nombre") String nombre){

        Producto p = productoService.traer(nombre);


        modelo.addAttribute("producto",p);
        System.out.println(p);
        return "vistaproducts";
    }

}