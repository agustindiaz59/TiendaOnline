package com.dev.tienda.modelos;

import com.dev.tienda.dto.ColorDTO;
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
@Table(name = "colores")
public class Color {


    //----------------Campos--------------------//
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




    //-------------Relaciones--------------------------//
    @ManyToMany(mappedBy = "colores", cascade = {CascadeType.MERGE,CascadeType.REFRESH },fetch = FetchType.EAGER)
    private Set<Producto> productos = new LinkedHashSet<Producto>();






    //----------------Constructores-------------------//

    public Color(){}

    public Color(String nombre, String htmlValue){
        this.nombre = nombre;
        this.htmlValue = htmlValue;
    }

    public Color(Long id, String nombre, String htmlValue) {
        this.id = id;
        this.nombre = nombre;
        this.htmlValue = htmlValue;
    }

    public Color(String nombre){
        this.nombre = nombre;
    }

    public Color(ColorDTO colorDTO) {
        this.nombre = colorDTO.nombre();
        this.htmlValue = colorDTO.htmlValue();
    }

    //----------------Conversion---------------//
    public ColorDTO getDTO(){
        return new ColorDTO(nombre,htmlValue);
    }

    //----------------Metodos--------------------//
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
