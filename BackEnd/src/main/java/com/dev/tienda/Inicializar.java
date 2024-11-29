package com.dev.tienda;

import com.dev.tienda.modelos.*;
import com.dev.tienda.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Set;

public class Inicializar {//implements CommandLineRunner {

    @Autowired
    private ProductoService productoService;


    //@Override
    public void run(String... args) throws Exception {
        Talla t1 = new Talla(1.5F);
        Talla t2 = new Talla(2F);
        Talla t3 = new Talla(2.5F);
        Talla t4 = new Talla(3F);

        Color c1 = new Color("amarillo", "#FF0000");
        Color c2 = new Color("negro", "#000000");

        Categoria cat1 = new Categoria("Abrigo");

        Imagen img1 = new Imagen("bralete3.jpg");

        Producto p = new Producto();

        Set<Imagen> imagenes = new LinkedHashSet<>();

        Set<Color> colores = new LinkedHashSet<>();

        Set<Talla> tallas = new LinkedHashSet<>();
        tallas.add(new Talla(1.5F));

        Set<Categoria> categorias = new LinkedHashSet<>();
        categorias.add(new Categoria("Abrigo"));

        p.setNombre("Buso de algodón");
        p.setDescripcion("Buso de algodón");
        p.setPrecio(9500F);
        p.setImagenes(imagenes);
        p.setColores(colores);
        p.setTallas(tallas);
        p.setCategorias(categorias);

    }
}
