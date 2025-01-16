package com.dev.tienda.servicios;

import com.dev.tienda.dto.ProductoDTO;
import com.dev.tienda.modelos.*;
import com.dev.tienda.repositorios.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Servicio
 */
@Service
public class ProductoService {

    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private ImagenesService imagenService;
    @Autowired
    private TallaService tallaService;


    //---------------Contructores---------------//
    public ProductoService(){

    }


    //---------------Metodos con retorno unico---------------//
    @Transactional
    public void guardar(Producto entity) {
        productoRepository.save(contextualizar(entity));
    }

    public Producto traerConTodo(String nombre){
        return productoRepository.findWithAll(nombre).orElse(null);
    }

    public Producto traer(String nombre){
        return productoRepository.findByNombre(nombre).orElse(null);
    }


    private Producto contextualizar(@NonNull Producto producto){
        Producto contexto = productoRepository.findByNombre(producto.getNombre()).orElse(producto);

        contexto.setColores(colorService.contextualizar(producto.getColores()));
        contexto.setTallas(tallaService.contextualizar(producto.getTallas()));
        contexto.setCategorias(categoriaService.contextualizar(producto.getCategorias()));
        contexto.setImagenes(imagenService.contextualizar(producto.getImagenes()));

        return contexto;
    }

    //---------------Metodos con listas---------------//
    public List<Producto> traerTodos() {
        return productoRepository.findAll();
    }

    public List<Producto> traerTodosConImagenes(){
        return productoRepository.findAllWithImages();
    }


    //---------------Metodos Paginables---------------//

    public Page<Producto> traerTodosConImagenes(Pageable pageable){
        return productoRepository.findAllWithImages(pageable);
    }
}
