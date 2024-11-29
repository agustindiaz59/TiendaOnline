package com.dev.tienda.modelos;

import com.dev.tienda.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * User details se utiliza para que spring security reconozca la entidad como
 * un usuario en la BBDD y en la FilterChain
 */
@Setter
@Getter
@Component
@Entity
@Table(name = "usuarios")
@ToString
public class Usuario implements UserDetails {
	
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

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getPassword() {
		return contrasenia;
	}

	@Override
	public String getUsername() {
		return nombre;
	}
}
