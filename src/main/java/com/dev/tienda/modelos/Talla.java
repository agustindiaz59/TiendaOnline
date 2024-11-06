package com.dev.tienda.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tallas")
public class Talla {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Para MySQL cambiar a .IDENTITY
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "numero", unique = true)
    @JdbcTypeCode(SqlTypes.FLOAT)
    private Float numero;

    @ManyToMany(mappedBy = "tallas",fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.REFRESH })
    private Set<Producto> productos = new LinkedHashSet<>();



    public Talla(){}

    public Talla(Float numero){
        this.numero = numero;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Talla talla = (Talla) o;
        return Objects.equals(numero, talla.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "Talla{" +
                "id=" + id +
                ", numero=" + numero +
                ", productos=" + productos +
                '}';
    }
}

