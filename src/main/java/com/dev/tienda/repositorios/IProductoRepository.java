package com.dev.tienda.repositorios;

import com.dev.tienda.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductoRepository extends JpaRepository<Producto,Long> {
    Producto findByNombre(String nombre);
}
