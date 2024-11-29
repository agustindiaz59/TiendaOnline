package com.dev.tienda.servicios;

import com.dev.tienda.modelos.Categoria;
import com.dev.tienda.repositorios.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository repository;


    public List<Categoria> traerTodos(){
        return repository.findAll();
    }
}
