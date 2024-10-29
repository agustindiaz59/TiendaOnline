package com.dev.tienda.servicios;

import com.dev.tienda.modelos.Producto;
import com.dev.tienda.repositorios.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements CrudService<Producto>{

    @Autowired
    private IProductoRepository repository;


    @Override
    public List<Producto> traerTodos() {
        return repository.findAll();
    }

    @Override
    public Producto traer(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public void guardar(Producto entity) {
        repository.save(entity);
    }

    @Override
    public void actualizar(Producto entity) {

    }

    @Override
    public void eliminar(Producto entity) {

    }

    @Override
    public void eliminar(Long entityID) {

    }
}
