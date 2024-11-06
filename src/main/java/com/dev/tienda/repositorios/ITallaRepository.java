package com.dev.tienda.repositorios;

import com.dev.tienda.modelos.Producto;
import com.dev.tienda.modelos.Talla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ITallaRepository extends JpaRepository<Talla, Long> {

    Boolean existsByNumero(Float numero);

    Talla findByNumero(Float numero);
}
