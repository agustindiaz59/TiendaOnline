package com.dev.tienda.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nombre;
	@Column
	private String descripcion;
	@Column
	private Float precio;
	@Column
	private String categoria;
	@Column
	private Float talla;
	@Column
	private String fotoPrincipal;

	@ElementCollection
	@Column(name = "foto")
	@CollectionTable(name = "Producto_fotos", joinColumns = @JoinColumn(name = "owner_id"))
	private Set<String> fotos = new LinkedHashSet<>();

	public void agregarFoto(String src){
		fotos.add(src);
	}
}
