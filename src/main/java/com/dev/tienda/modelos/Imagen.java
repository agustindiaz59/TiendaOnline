package com.dev.tienda.modelos;

import com.dev.tienda.dto.ImagenDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.random.RandomGenerator;

@Setter
@Getter
@Entity
@Table(name = "imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "src", unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String src;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "producto_id")
    //private Producto producto;

    @ManyToMany(mappedBy = "imagenes",fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.REFRESH })
    private Set<Producto> productos = new LinkedHashSet<Producto>();

    public Imagen(String src){
        this.src = src;
    }

    public Imagen(){
    }
    public Imagen(ImagenDTO dto){
        src = dto.src();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagen imagen = (Imagen) o;
        return Objects.equals(src, imagen.src);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src);
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "id=" + id +
                ", src='" + src + '\'' +
                '}';
    }
}
