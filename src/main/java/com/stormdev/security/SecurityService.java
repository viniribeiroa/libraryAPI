/**
 * Autor: VINICIUS
 * Data: 12 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.stormdev.model.Usuario;
import com.stormdev.service.UsuarioService;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Component
@RequiredArgsConstructor
public class SecurityService {
	
	private final UsuarioService service;

	public Usuario obterUsuarioLogado() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof CustomAuthetication customAuth) {
			return customAuth.getUsuario();
		}
		
		return null;
	}
}
