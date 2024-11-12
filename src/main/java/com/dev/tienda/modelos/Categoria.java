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
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "nombre", length = 150, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String nombre;

    @ManyToMany(mappedBy = "categorias", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Producto> productos = new LinkedHashSet<>();

    public Categoria(){}

    public Categoria(String nombre){
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria categoria)) return false;
        return Objects.equals(id, categoria.id) && Objects.equals(nombre, categoria.nombre) && Objects.equals(productos, categoria.productos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
