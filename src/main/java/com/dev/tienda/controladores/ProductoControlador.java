package com.dev.tienda.controladores;

import com.dev.tienda.modelos.*;
import com.dev.tienda.modelos.Color;
import com.dev.tienda.repositorios.ICategoriaRepository;
import com.dev.tienda.repositorios.IProductoRepository;
import com.dev.tienda.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

@Controller
public class ProductoControlador {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Autowired
    private IProductoRepository productoRepository;


    @GetMapping("/crearProductos")
    public String crearProductos(){
        Producto p = new Producto();

        Imagen img2 = new Imagen("remera.jpg");
        img2.setProducto(p);

        Talla t1 = new Talla(2F);
        Talla t2 = new Talla(2F);
        Talla t3 = new Talla(2F);
        Color c3 = new Color("azul", "#00FF00");
        Categoria cat = new Categoria();

        cat.setNombre("Abrigo");

        //TODO implementar servicios para demas entidades para verificar su existencia
        //cat.setId(1L); // Para guardar necesito no tener id, para actualizar hay que tenerlo
        //t3.setId(1L);
        //t2.setId(1L);
        //t1.setId(1L);
        //img2.setId(1L);
        //c3.setId(1L);


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



    //@Transactional//Donde creas o traes los productos necesitas transaccional para que los cambios surtan efecto
    @GetMapping("/products")
    public String listaDeProductos(Model modelo){
        List<Producto> productos = productoRepository.findAllWithImages();
        List<Categoria> categorias = categoriaRepository.findAll();

        modelo.addAttribute("productos",productos);
        modelo.addAttribute("categorias", categorias);
        return "products";
    }




    /**
     *
     * @param modelo Interfaz para el modelo del DOM utilizado por las vistas
     * @param nombre Nombre del producto solicitado
     * @return Vista con los datos del producto solicitado
     */
    @GetMapping("/vistaproducts")
    public String detalleDeProducto(Model modelo, @RequestParam("n") String nombre){

        Producto p = productoService.traer(nombre);

        modelo.addAttribute("producto",p);

        return "vistaproducts";
    }

}