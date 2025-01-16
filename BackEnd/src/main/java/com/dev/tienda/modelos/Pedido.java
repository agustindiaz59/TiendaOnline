package com.dev.tienda.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "numero_pedido", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long numeroPedido;

    @Column(name = "precio_total")
    @JdbcTypeCode(SqlTypes.FLOAT)
    private Float precioTotal = 0F;

    @Setter
    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "PEDIDOS_productos",
            joinColumns = @JoinColumn(name = "numero_pedido"),
            inverseJoinColumns = @JoinColumn(name = "productos_id"))
    private Set<Producto> productos = new LinkedHashSet<>();

    public Pedido(){}

    public Pedido(Long numeroPedido, Set<Producto> productos) {
        this.numeroPedido = numeroPedido;
        this.productos = productos;

        productos.stream()
                .forEach(p -> precioTotal += p.getPrecio());
    }

    public Pedido(Long numeroPedido, Set<Producto> productos, Float descuento) {
        this.numeroPedido = numeroPedido;
        this.productos = productos;

        productos.stream()
                .forEach(p -> precioTotal += p.getPrecio());

        this.precioTotal -= descuento;
    }

    public Float getPrecioTotal() {
        return precioTotal;
    }

    private void setPrecioTotal(Float precioTotal) {
        this.precioTotal = precioTotal;
    }

}
