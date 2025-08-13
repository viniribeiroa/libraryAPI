/**
 * Autor: VINICIUS
 * Data: 12 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.stormdev.model.Usuario;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 
 */
@RequiredArgsConstructor
@Getter
public class CustomAuthetication implements Authentication{

	private final Usuario usuario;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return usuario.getLogin();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.usuario
				.getRoles()
				.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return usuario;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return usuario;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
