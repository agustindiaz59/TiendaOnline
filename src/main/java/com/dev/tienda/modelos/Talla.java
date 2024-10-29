package com.dev.tienda.modelos;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "tallas")
public class Talla {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Para MySQL cambiar a .IDENTITY
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "numero")
    @JdbcTypeCode(SqlTypes.FLOAT)
    private Float numero;

    public Float getNumero() {
        return numero;
    }

    public void setNumero(Float numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
