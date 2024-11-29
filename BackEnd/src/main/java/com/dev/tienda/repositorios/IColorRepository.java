package com.dev.tienda.repositorios;

import com.dev.tienda.modelos.Color;
import com.dev.tienda.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IColorRepository extends JpaRepository<Color,Long> {


    Boolean existsByNombre(String nombre);
    Optional<Color> findByNombre(String nombre);

}
