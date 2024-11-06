package com.dev.tienda.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "precio")
    @JdbcTypeCode(SqlTypes.FLOAT)
    private Float precio;

    @Column(name = "descripcion")
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String descripcion;

    @Column(name = "nombre")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String nombre;



    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "productos_tallas",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "tallas_id"))
    private Set<Talla> tallas = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "productos_colores",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "colores_id")
    )
    private Set<Color> colores = new LinkedHashSet<>();

    //@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name = "productos_imagenes",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "imagenes_id")
    )
    private Set<Imagen> imagenes = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "productos_categorias",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categorias = new LinkedHashSet<Categoria>();



    public Producto(){}



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id) && Objects.equals(precio, producto.precio) && Objects.equals(descripcion, producto.descripcion) && Objects.equals(nombre, producto.nombre) && Objects.equals(imagenes, producto.imagenes) && Objects.equals(categorias, producto.categorias) && Objects.equals(colores, producto.colores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, precio, descripcion, nombre, imagenes, categorias, colores);
    }


}
