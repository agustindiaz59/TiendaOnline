package com.dev.tienda.modelos;

import com.dev.tienda.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.lang.reflect.UnlockSignature;
import org.springframework.stereotype.Component;

@Component
@Entity
@ToString
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nombre;
	@Column
	private String contrasenia;

	public Usuario(){

	}

	public Usuario(UsuarioDTO dto){
		this.nombre = dto.nombre();
		this.contrasenia = dto.contrasenia();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
}
