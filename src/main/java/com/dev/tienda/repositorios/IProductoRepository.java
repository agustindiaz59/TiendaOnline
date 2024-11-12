package com.dev.tienda.repositorios;

import com.dev.tienda.modelos.Producto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Long> {
    //TODO Agregar metodos EntityGraph para carga diferida en las demas relaciones

    @EntityGraph(attributePaths = {"imagenes"})
    @Query("SELECT p FROM Producto p")
    List<Producto> findAllWithImages();

    @EntityGraph(attributePaths = "colores")
    @Query("SELECT p FROM Producto p")
    List<Producto> findAllWithColors();

    @EntityGraph(attributePaths = "categorias")
    @Query("SELECT p FROM Producto p")
    List<Producto> findAllWithCategories();

    @EntityGraph(attributePaths = "tallas")
    @Query("SELECT p FROM Producto p")
    List<Producto> findAllWithTallas();

    @EntityGraph(attributePaths = {"imagenes", "colores", "categorias", "tallas"})
    @Query("SELECT p FROM Producto p")
    List<Producto> findAllWithAll();

    Optional<Producto> findByNombre(String nombre);

}
