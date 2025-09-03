/**
 * Autor: VINICIUS
 * Data: 3 de set. de 2025
 * Descrição: TODO
 */
package com.stormdev.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.stormdev.model.Usuario;
import com.stormdev.service.UsuarioService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Component
@RequiredArgsConstructor
public class JwtCustomAuthenticationFilter extends OncePerRequestFilter{

	private final UsuarioService usuarioService;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (deveConverter(authentication)) {
			String login = authentication.getName();
			Usuario usuario = usuarioService.obterPorLogin(login);
			if (usuario != null) {
				authentication = new CustomAuthetication(usuario);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
	private boolean deveConverter(Authentication authentication) {
		return authentication != null && authentication instanceof JwtAuthenticationToken;
	}
}
