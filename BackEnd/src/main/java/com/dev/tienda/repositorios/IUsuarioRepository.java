package com.dev.tienda.repositorios;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.tienda.modelos.Usuario;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String username);
    Boolean existsByNombre(String nombre);
}
