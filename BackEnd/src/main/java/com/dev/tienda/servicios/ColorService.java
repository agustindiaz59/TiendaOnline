package com.dev.tienda.servicios;

import com.dev.tienda.modelos.Color;
import com.dev.tienda.repositorios.IColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ColorService {

    @Autowired
    private IColorRepository repository;

    @Transactional
    public void guardarTodos(Set<Color> colores){

        for (Color c : colores){
            //TODO Hacer que los find retornen Optional<T>
            repository.save(repository.findByNombre(c.getNombre()).orElse(c));
        }
    }

    public List<Color> traerTodos(){
        return repository.findAll();
    }
}
