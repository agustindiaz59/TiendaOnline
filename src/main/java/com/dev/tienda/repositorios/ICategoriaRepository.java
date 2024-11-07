package com.dev.tienda.repositorios;

import com.dev.tienda.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findByNombre(String nombre);
    Boolean existsByNombre(String nombre);
}
