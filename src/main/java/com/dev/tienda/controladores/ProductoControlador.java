package com.dev.tienda.controladores;

import com.dev.tienda.modelos.*;
import com.dev.tienda.repositorios.IProductoRepository;

import com.dev.tienda.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductoControlador {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private IProductoRepository repository;

    @GetMapping("/crearProductos")
    public String crearProductos(){
        Imagen m = new Imagen();
        m.setSrc("/assets/imagen/foto1.jpeg");

        Color c = new Color();
        c.setNombre("rojo");
        c.setHtmlValue("#FF0000");

        Talla t = new Talla();
        t.setNumero(100.0F);

        Categoria cat = new Categoria();
        cat.setNombre("Ropa interior");


        Producto a = new Producto();
        a.setNombre("corpiño");
        a.setDescripcion(" loremloremloremloremlorem loremloremloremlorem");
        a.setPrecio(1500.0F);
        a.getImagenes().add(m);
        a.getColores().add(c);
        a.getTallas().add(t);
        a.getCategorias().add(cat);


        cat.getProductos().add(a);

        repository.save(a);
        return "index";
    }

    @GetMapping("/products")
    public String listaDeProductos(Model modelo){

        modelo.addAttribute("productos",productoService.traerTodos());

        return "products";
    }

    @GetMapping("/vistaproducts/{id}")
    public String detalleDeProducto(Model modelo, @PathVariable("id") Long id){

        Producto p = repository.findById(id).orElse(null);

        modelo.addAttribute("producto",p);
        System.out.println(p);
        return "vistaproducts";
    }

}