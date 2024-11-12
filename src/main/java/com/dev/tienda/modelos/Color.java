package com.dev.tienda.modelos;

import com.dev.tienda.repositorios.IColorRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Persistable;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "colores")
public class Color implements Persistable<Long> {

    @Transient
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "html_value", length = 10)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String htmlValue;


    @Column(name = "nombre", length = 100, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String nombre;


    @ManyToMany(mappedBy = "colores", cascade = {CascadeType.MERGE,CascadeType.REFRESH },fetch = FetchType.EAGER)
    private Set<Producto> productos = new LinkedHashSet<Producto>();



    public Color(){}

    public Color(String nombre, String htmlValue){
        this.nombre = nombre;
        this.htmlValue = htmlValue;
    }

    public Color(String nombre){
        this.nombre = nombre;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return Objects.equals(htmlValue, color.htmlValue) && Objects.equals(nombre, color.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(htmlValue, nombre);
    }


}
