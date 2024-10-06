package com.dev.tienda.controladores;

import com.dev.tienda.repositorios.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class ProductoControlador {
    @Autowired
    private ProductoRepository repository;

//    @GetMapping("/producto")
//    public String getProducto(@RequestParam("id") Long id){
//
//        return "";
//    }
    @GetMapping("/products")
    public String listaDeProductos(Model modelo){

        return "products";
    }
//    @GetMapping("")
//    public String c(){
//
//        return "";
//    }
//    @GetMapping("")
//    public String c(){
//
//        return "";
//    }
//    @GetMapping("")
//    public String c(){
//
//        return "";
//    }
}