package com.dev.tienda.servicios;

import com.dev.tienda.modelos.Imagen;
import com.dev.tienda.repositorios.IImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ImagenesService {

    @Autowired
    private IImagenRepository repository;


    @Transactional
    public void guardar(Imagen entity) {
        //
        Optional<Imagen> aGuardar = repository.findBySrc(entity.getSrc());


        repository.save(aGuardar.orElse(entity));
    }

    public Set<Imagen> contextualizar(Set<Imagen> imagenes){
        return imagenes.stream()
                .map( imagen ->
                        repository.findBySrc(imagen.getSrc()).orElse(imagen)
                )
                .collect(Collectors.toSet());
    }

}
