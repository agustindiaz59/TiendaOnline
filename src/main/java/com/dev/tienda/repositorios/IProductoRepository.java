package com.dev.tienda.repositorios;

import com.dev.tienda.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Long> {

    @Transactional
    default Producto updateOrInsert(Producto p){
        return save(p);
    }

    Producto findByNombre(String nombre);

}
