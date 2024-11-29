package com.dev.tienda.controladores;

import com.dev.tienda.modelos.*;
import com.dev.tienda.modelos.Color;
import com.dev.tienda.servicios.CategoriaService;
import com.dev.tienda.servicios.ColorService;
import com.dev.tienda.servicios.ProductoService;
import com.dev.tienda.servicios.TallaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ProductoControlador {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private TallaService tallaService;

    @Autowired
    private ColorService colorService;




    @GetMapping("/crearProductos")
    public String crearProductos(){
        Producto p = new Producto();

        Set<Imagen> imagenes = new LinkedHashSet<>();
        imagenes.add(new Imagen("bralete3.jpg"));
        imagenes.add(new Imagen("bralete2.jpg"));
        imagenes.add(new Imagen("bralete1.jpg"));

        Set<Color> colores = new LinkedHashSet<>();
        colores.add(new Color(1L,"amarillo", "#FF0000"));

        Set<Talla> tallas = new LinkedHashSet<>();
        tallas.add(new Talla(1L,1.5F));
        tallas.add(new Talla(2L,2F));
        tallas.add(new Talla(3L,2.5F));

        Set<Categoria> categorias = new LinkedHashSet<>();
        categorias.add(new Categoria("Ropa interior"));

        p.setNombre("Bralette");
        p.setDescripcion("Brealette interior");
        p.setPrecio(5900F);
        p.setImagenes(imagenes);
        p.setColores(colores);
        p.setTallas(tallas);
        p.setCategorias(categorias);



        //TODO implementar servicios para demas entidades para verificar su existencia
        //cat.setId(1L); // Para guardar necesito no tener id, para actualizar hay que tenerlo
        //t3.setId(1L);
        //t2.setId(1L);
        //t1.setId(1L);
        //img2.setId(1L);
        //c3.setId(1L);


        productoService.guardar(p);
        return "forward:/products";
    }



    //@Transactional//Donde creas o traes los productos necesitas transaccional para que los cambios surtan efecto
    @GetMapping("/products")
    public String listaDeProductos(Model modelo){
        List<Producto> productos = productoService.traerTodosConImagenes();
        List<Categoria> categorias = categoriaService.traerTodos();
        List<Color> colores = colorService.traerTodos();
        List<Talla> tallas = tallaService.traerTodos();

        modelo.addAttribute("productos",productos);
        modelo.addAttribute("categorias", categorias);
        modelo.addAttribute("colores", colores);
        modelo.addAttribute("tallas", tallas);
        return "products";
    }




    /**
     * @param modelo Interfaz para el modelo del DOM utilizado por las vistas
     * @param nombre Nombre del producto solicitado
     * @return Vista con los datos del producto solicitado
     */
    @GetMapping("/vistaproducts")
    public String detalleDeProducto(Model modelo, @RequestParam("n") String nombre){

        nombre = nombre.replace('-',' ');
        Producto p = productoService.traerConTodo(nombre);

        modelo.addAttribute("producto",p);

        return "vistaproducts";
    }

}