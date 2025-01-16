package com.dev.tienda.modelos;

import com.dev.tienda.dto.ColorDTO;
import com.dev.tienda.dto.ImagenDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

@Setter
@Getter()
@Entity
@Table(name = "imagenes")
public class Imagen {

    //-------------Campos-----------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "src", unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String src;




    //-------------Relaciones-----------------------
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id")
    private Producto producto;






    //-------------Constructores-----------------------
    public Imagen(String src){
        this.src = src;
    }

    public Imagen(Long id, String src) {
        this.id = id;
        this.src = src;
    }

    public Imagen(){
    }
    public Imagen(ImagenDTO dto){
        src = dto.src();
    }



    //----------------Conversion---------------//
    public ImagenDTO getDTO(){
        return new ImagenDTO(src);
    }

    //------------Metodos object-----------------------
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
