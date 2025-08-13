/**
 * Autor: VINICIUS
 * Data: 13 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.stormdev.model.Usuario;
import com.stormdev.service.UsuarioService;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider{

	private final UsuarioService usuarioService;
	private final PasswordEncoder encoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String login = authentication.getName();
		String senhaDigitadaString = authentication.getCredentials().toString();
		
		Usuario usuarioEncontrado = usuarioService.obterPorLogin(login);
		
		if (usuarioEncontrado == null) {
			throw getErroUsuarioNaoEncontrado();
		}
		
		String senhaCriptografada = usuarioEncontrado.getSenha();
		
		boolean senhasBatem = encoder.matches(senhaDigitadaString, senhaCriptografada);
		if (senhasBatem) {
			return new CustomAuthetication(usuarioEncontrado);
		}
		throw getErroUsuarioNaoEncontrado(); 
	}

	/**
	 * @return
	 */
	private UsernameNotFoundException getErroUsuarioNaoEncontrado() {
		return new UsernameNotFoundException("Senha e/ou usuario incorretos");
	}

	@Override
	public boolean supports(Class<?> authentication) {
	
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

}
