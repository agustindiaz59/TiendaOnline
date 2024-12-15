package com.dev.tienda.repositorios;

import com.dev.tienda.modelos.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Long> {

    @EntityGraph(attributePaths = {"imagenes", "colores", "categorias", "tallas"})
    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    Optional<Producto> findWithAll(@Param("nombre") String nombre);


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

    //TODO Limpiar metodos inecesarios

    //---------------Metodos paginables-----------------//
    @EntityGraph(attributePaths = {"imagenes"})
    @Query("SELECT p FROM Producto p")
    Page<Producto> findAllWithImages(Pageable page);

    @EntityGraph(attributePaths = "colores")
    @Query("SELECT p FROM Producto p")
    Page<Producto> findAllWithColors(Pageable page);

    @EntityGraph(attributePaths = "categorias")
    @Query("SELECT p FROM Producto p")
    Page<Producto> findAllWithCategories(Pageable page);

    @EntityGraph(attributePaths = "tallas")
    @Query("SELECT p FROM Producto p")
    Page<Producto> findAllWithTallas(Pageable page);

    @EntityGraph(attributePaths = {"imagenes", "colores", "categorias", "tallas"})
    @Query("SELECT p FROM Producto p")
    Page<Producto> findAllWithAll(Pageable page);

    Optional<Producto> findByNombre(String nombre);
}
