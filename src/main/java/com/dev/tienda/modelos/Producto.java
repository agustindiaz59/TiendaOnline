package com.dev.tienda.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.awt.*;
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



    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(name = "productos_tallas",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "tallas_id"))
    private Set<Talla> tallas = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(
            name = "productos_colores",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "colores_id")
    )
    private Set<Color> colores = new LinkedHashSet<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private Set<Imagen> imagenes = new LinkedHashSet<>();

    @ManyToMany( cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(name = "productos_categorias",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categorias = new LinkedHashSet<>();



    public Producto(){}



    //TODO Agregar metodos para llenar las colecciones
    // y estalecer las relaciones entre elementos

    //Metodos de imagenes
    public void agregarImagen(Imagen img){
        img.setProducto(this);
        imagenes.add(img);
    }

    public void agregarImagen(String src){
        Imagen img = new Imagen(src);
        img.setProducto(this);
        imagenes.add(img);
    }


    //Metodos de colores
    public void agregarColor(Color color){
        colores.add(color);
    }

    public void agregarColor(String nombre){
        Color color = new Color(nombre);
        colores.add(color);
    }

    public void agregarColor(String nombre, String htmlValue){
        Color color = new Color(nombre,htmlValue);
        colores.add(color);
    }


    //Metodos de categoria
    public void agregarCategoria(Categoria categoria){
        categorias.add(categoria);
    }

    public void agregarCategoria(String nombreCategoria){
        Categoria categoria = new Categoria(nombreCategoria);
        categorias.add(categoria);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id) && Objects.equals(precio, producto.precio) && Objects.equals(descripcion, producto.descripcion) && Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, precio, descripcion, nombre);
    }


}
