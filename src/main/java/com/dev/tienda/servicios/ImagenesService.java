package com.dev.tienda.servicios;

import com.dev.tienda.modelos.Imagen;
import com.dev.tienda.repositorios.IImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenesService implements CrudService<Imagen>{

    @Autowired
    private IImagenRepository imagenRepository;


    @Override
    public List<Imagen> traerTodos() {
        return List.of();
    }

    @Override
    public Imagen traer(String nombre) {
        return null;
    }

    @Override
    public void guardar(Imagen entity) {
        //
        Imagen bd = imagenRepository.findBySrc(entity.getSrc());
        Optional<Imagen> aGuardar = Optional.ofNullable(bd);


        imagenRepository.save(aGuardar.orElse(entity));
    }

    @Override
    public void actualizar(Imagen entity) {

    }

    @Override
    public void eliminar(Imagen entity) {

    }

    @Override
    public void eliminar(Long entityID) {

    }
}
