package com.dev.tienda.servicios;

import com.dev.tienda.modelos.Color;
import com.dev.tienda.repositorios.IColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class ColorService {

    @Autowired
    private IColorRepository repository;

    @Transactional
    public void guardarTodos(Set<Color> colores){
        Optional<Color> aGuardar;

        for (Color c : colores){
            aGuardar = Optional.ofNullable(repository.findByNombre(c.getNombre()));

            System.out.println("-----------------------" + c.toString() + "--------------------------");
            repository.save(aGuardar.orElse(c));
        }
    }
}
