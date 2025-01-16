package com.dev.tienda.servicios;

import com.dev.tienda.modelos.Color;
import com.dev.tienda.repositorios.IColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ColorService {

    @Autowired
    private IColorRepository repository;

    @Transactional
    public void guardarTodos(Set<Color> colores){

        for (Color c : colores){
            repository.save(repository.findByNombre(c.getNombre()).orElse(c));
        }
    }

    @Transactional
    public Set<Color> contextualizar(Set<Color> colores){
        return colores.stream()
                .map(color ->
                    repository.findByNombre(color.getNombre()).orElse(color)
                )
                .collect(Collectors.toSet());
    }

    public List<Color> traerTodos(){
        return repository.findAll();
    }
}
