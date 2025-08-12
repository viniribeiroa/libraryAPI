/**
 * Autor: VINICIUS
 * Data: 12 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.stormdev.model.Usuario;
import com.stormdev.service.UsuarioService;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	
	private final UsuarioService service;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		Usuario usuario = service.obterPorLogin(login);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		
		}
		return User.builder()
				.username(usuario.getLogin())
				.password(usuario.getSenha())
				.roles(usuario.getRoles().toArray(new String[usuario.getRoles().size()]))
				.build();
				
	}

}
