package com.dev.tienda.servicios;

import com.dev.tienda.modelos.Imagen;
import com.dev.tienda.repositorios.IImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ImagenesService {

    @Autowired
    private IImagenRepository imagenRepository;



    public void guardar(Imagen entity) {
        //
        Imagen bd = imagenRepository.findBySrc(entity.getSrc());
        Optional<Imagen> aGuardar = Optional.ofNullable(bd);


        imagenRepository.save(aGuardar.orElse(entity));
    }

}
