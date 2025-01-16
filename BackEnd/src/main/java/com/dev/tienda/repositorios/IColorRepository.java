package com.dev.tienda.repositorios;

import com.dev.tienda.modelos.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IColorRepository extends JpaRepository<Color,Long> {


    Boolean existsByNombre(String nombre);
    Optional<Color> findByNombre(String nombre);

}
