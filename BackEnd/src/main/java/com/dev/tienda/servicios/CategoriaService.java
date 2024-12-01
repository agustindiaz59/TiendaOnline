package com.dev.tienda.servicios;

import com.dev.tienda.modelos.Categoria;
import com.dev.tienda.repositorios.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository repository;


    public List<Categoria> traerTodos(){
        return repository.findAll();
    }

    public Set<Categoria> contextualizar(Set<Categoria> categorias){
        return categorias.stream()
                .map(categoria ->
                        repository.findByNombre(categoria.getNombre()).orElse(categoria)
                )
                .collect(Collectors.toSet());
    }
}
