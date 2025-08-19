/**
 * Autor: VINICIUS
 * Data: 18 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.stormdev.model.Usuario;
import com.stormdev.service.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Component
@RequiredArgsConstructor
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private final UsuarioService usuarioService;

	@Override
		public void onAuthenticationSuccess(
				HttpServletRequest request,
				HttpServletResponse response,
				Authentication authentication) throws ServletException, IOException {
		
			OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
			OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();
			
			String email = oAuth2User.getAttribute("email");
			
			Usuario usuario = usuarioService.obterPorEmail(email);
			
			authentication = new CustomAuthetication(usuario);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			super.onAuthenticationSuccess(request, response, authentication);
		}
}
