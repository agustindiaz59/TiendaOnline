package com.dev.tienda.controladores;

import com.dev.tienda.modelos.*;
import com.dev.tienda.modelos.Color;
import com.dev.tienda.repositorios.ICategoriaRepository;
import com.dev.tienda.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductoControlador {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ICategoriaRepository categoriaRepository;



    @GetMapping("/crearProductos")
    public String crearProductos(){
        Producto p = new Producto();

        Imagen img2 = new Imagen("remera.jpg");
        img2.setId(2L);


        Talla t1 = new Talla(2F);
        t1.setId(1L);
        Talla t2 = new Talla(2F);
        t2.setId(1L);
        Talla t3 = new Talla(2F);
        t3.setId(1L);

        Color c3 = new Color("azul", "#00FF00");
        c3.setId(2L);
        Categoria cat = new Categoria();
        cat.setNombre("Abrigo");
        cat.setId(2L); // TODO Para guardar necesito no tener id, para actualizar hay que tenerlo

        p.setNombre("Remera");
        p.setDescripcion("Remera");
        p.setPrecio(9000F);
        p.getImagenes().add(img2);
        p.getColores().add(c3);
        p.getTallas().add(t1);
        p.getTallas().add(t2);
        p.getTallas().add(t3);
        p.getCategorias().add(cat);

        productoService.guardar(p);
        return "forward:/products";
    }

    @GetMapping("/products")
    public String listaDeProductos(Model modelo){
        List<Producto> productos = productoService.traerTodos();
        List<Categoria> categorias = categoriaRepository.findAll();
        

        modelo.addAttribute("productos",productos);
        modelo.addAttribute("categorias", categorias);
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