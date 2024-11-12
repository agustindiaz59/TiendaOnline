package com.dev.tienda.repositorios;

import com.dev.tienda.modelos.Talla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITallaRepository extends JpaRepository<Talla, Long> {

    Boolean existsByNumero(Float numero);

    Optional<Talla> findByNumero(Float numero);
}
