package com.dev.tienda.controladores;

import com.dev.tienda.dto.ProductoDTO;
import com.dev.tienda.modelos.*;
import com.dev.tienda.modelos.Color;
import com.dev.tienda.servicios.CategoriaService;
import com.dev.tienda.servicios.ColorService;
import com.dev.tienda.servicios.ProductoService;
import com.dev.tienda.servicios.TallaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ProductoControlador {

    /**
     * Sección de atributos
     *
     * Spring se encarga de la inyeccion de dependencias, es decir, decidir que clases instanciar
     * dentro de los atributos
     */
    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private TallaService tallaService;

    @Autowired
    private ColorService colorService;


    /**
     * Ruta utilizada solo para crear productos de prueba
     *
     * @deprecated
     * @return Lista de productos actualizada
     */
    @GetMapping("/crearProductos")
    public String crearProductos(){
        Producto p = new Producto();

        Set<Imagen> imagenes = new LinkedHashSet<>();
        imagenes.add(new Imagen("deportivo.jpg"));

        Set<Color> colores = new LinkedHashSet<>();
        colores.add(new Color("amarillo", "#FF0000"));
        colores.add(new Color("rojo", "#FF0000"));

        Set<Talla> tallas = new LinkedHashSet<>();
        tallas.add(new Talla(1.5F));
        tallas.add(new Talla(2F));

        Set<Categoria> categorias = new LinkedHashSet<>();
        categorias.add(new Categoria("Ropa interior"));

        p.setNombre("Deportivo");
        p.setDescripcion("Conjunto deportivo");
        p.setPrecio(5200F);
        p.setImagenes(imagenes);
        p.setColores(colores);
        p.setTallas(tallas);
        p.setCategorias(categorias);




        productoService.guardar(p);
        return "forward:/products";
    }




    /**
     * Ruta donde se mostraran todos los productos disponibles, además de colores,
     * talla y otros criterios de busqueda
     *
     * @param modelo Interfaz que representa el DOM de HTML
     * @return Listado de productos disponibles
     */
    @GetMapping("/products")
    public String listaDeProductos(Model modelo,
                                   @PageableDefault(page = 0, size = 10) Pageable pagina){
        Page<Producto> productos = productoService.traerTodosConImagenes(pagina);
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
     * Ruta donde se mostará la informacion de un producto en particular
     *
     * @param modelo Interfaz para el modelo del DOM utilizado por las vistas
     * @param nombre Nombre del producto solicitado
     * @return Vista con los datos del producto solicitado
     */
    @GetMapping("/vistaproducts")
    public String detalleDeProducto(Model modelo,
                                    @RequestParam("n") String nombre){

        //Convierto los + en espacios en blanco desde la url
        nombre = nombre.replace('+',' ');
        Producto p = productoService.traerConTodo(nombre);

        modelo.addAttribute("producto",p);

        return "vistaproducts";
    }

    /**
     * Ruta que envia el formulario para crear un producto
     */
    @GetMapping("/guardar")
    public String formularioProducto(Model modelo){
        List<Producto> productos = productoService.traerTodosConImagenes();
        List<Categoria> categorias = categoriaService.traerTodos();
        List<Color> colores = colorService.traerTodos();
        List<Talla> tallas = tallaService.traerTodos();

        modelo.addAttribute("productos",productos);
        modelo.addAttribute("categorias", categorias);
        modelo.addAttribute("colores", colores);
        modelo.addAttribute("tallas", tallas);

        return "nuevoProducto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@Valid ProductoDTO productoDTO, BindingResult bindingResult){

        System.out.println(productoDTO);
        if(bindingResult.hasErrors()){
            System.out.println("Datos ingresados invalidos");
            return "redirect:guardar";
        }

        return "index";
    }


}