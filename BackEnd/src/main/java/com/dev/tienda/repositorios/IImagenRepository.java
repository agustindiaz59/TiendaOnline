package com.dev.tienda.repositorios;

import com.dev.tienda.modelos.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IImagenRepository extends JpaRepository<Imagen, Long> {

    Optional<Imagen> findBySrc(String src);
    Boolean existsBySrc(String src);
}
