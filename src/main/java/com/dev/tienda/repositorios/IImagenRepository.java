package com.dev.tienda.repositorios;

import com.dev.tienda.modelos.Imagen;
import com.dev.tienda.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IImagenRepository extends JpaRepository<Imagen, Long> {

    Imagen findBySrc(String src);
    Boolean existsBySrc(String src);

    @Transactional
    @Modifying
    @Query("update Imagen i set i.src = ?1")
    int updateSrcBy(String src);
}
