package com.dev.tienda.servicios;

import com.dev.tienda.modelos.*;
import com.dev.tienda.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private ColorService colorService;


    public List<Producto> traerTodos() {
        return productoRepository.findAll();
    }

    public Producto traer(String nombre){
        return productoRepository.findByNombre(nombre).orElse(null);
    }

    //TODO Relacionar los metodos de servicio para verificar la existencia de los
    // elementos en el contexto de persistencia

    @Transactional
    public void guardar(Producto entity) {
        //Verifica si ya existe un producto por su nombre
        Optional<Producto> aGuardar = productoRepository.findByNombre(entity.getNombre());

        if(aGuardar.isPresent()){
           aGuardar.get().setNombre(entity.getNombre());
           aGuardar.get().setPrecio(entity.getPrecio());
           aGuardar.get().setDescripcion(entity.getDescripcion());
           aGuardar.get().setColores(entity.getColores());
           aGuardar.get().setTallas(entity.getTallas());
           aGuardar.get().setImagenes(entity.getImagenes());
           aGuardar.get().setCategorias(entity.getCategorias());

        }

        //Si ya existe lo actualiza, si no lo guarda como uno nuevo (upsert)
        productoRepository.save(aGuardar.orElse(entity));
    }

}
