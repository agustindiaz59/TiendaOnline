package com.dev.tienda.servicios;

import com.dev.tienda.modelos.Imagen;
import com.dev.tienda.repositorios.IImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class ImagenesService {

    @Autowired
    private IImagenRepository imagenRepository;


    @Transactional
    public void guardar(Imagen entity) {
        //
        Optional<Imagen> aGuardar = imagenRepository.findBySrc(entity.getSrc());


        imagenRepository.save(aGuardar.orElse(entity));
    }

}
