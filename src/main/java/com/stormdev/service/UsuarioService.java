/**
 * Autor: VINICIUS
 * Data: 12 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stormdev.model.Usuario;
import com.stormdev.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repository;
	private final PasswordEncoder encoder;
	
	public void salvar(Usuario usuario) {
		var senha = usuario.getSenha();
		usuario.setSenha(encoder.encode(senha));
		repository.save(usuario);
	}
	
	public Usuario obterPorLogin(String login) {
		return repository.findByLogin(login);
	}
}
