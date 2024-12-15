package com.dev.tienda.modelos;

import com.dev.tienda.dto.CategoriaDTO;
import com.dev.tienda.dto.ColorDTO;
import com.dev.tienda.dto.ImagenDTO;
import com.dev.tienda.dto.ProductoDTO;
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

    //-------------Campos-----------------------
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

    @Column
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer stock;



    //-------------Relaciones-----------------------
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(name = "productos_tallas",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "tallas_id"))
    private Set<Talla> tallas = new LinkedHashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(
            name = "productos_colores",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "colores_id")
    )
    private Set<Color> colores = new LinkedHashSet<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private Set<Imagen> imagenes = new LinkedHashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(name = "productos_categorias",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categorias = new LinkedHashSet<>();



    //-------------Constructores-----------------------
    public Producto(){}

    public Producto(ProductoDTO dto){
        nombre = dto.nombre();
        descripcion = dto.descripcion();
        precio = dto.precio();

        for(ImagenDTO imgDTO : dto.imagenes()){
            imagenes.add(new Imagen(imgDTO));
        }

        for(ColorDTO colorDTO : dto.colores()){
            colores.add(new Color(colorDTO));
        }

        for(CategoriaDTO categoriaDTO : dto.categorias()){
            categorias.add(new Categoria(categoriaDTO));
        }

    }



    //-------------Metodos-----------------------

    //Metodos de stock
    public Boolean enStock(){
        return stock > 0;
    }

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

    //--------------------Getters y Setters--------------


    public void setImagenes(Set<Imagen> imagenes) {
        for(Imagen img : imagenes){
            img.setProducto(this);
        }
        this.imagenes = imagenes;
    }

    //-------------Equals y hashCode-----------------------
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
