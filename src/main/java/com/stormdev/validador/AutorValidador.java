/**
 * Autor: VINICIUS
 * Data: 24 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.validador;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.stormdev.exceptions.RegistroDuplicadoException;
import com.stormdev.model.Autor;
import com.stormdev.repository.AutorRepository;

/**
 * 
 */
@Component
public class AutorValidador {

	private AutorRepository repository;

	public AutorValidador(AutorRepository repository) {
		this.repository = repository;
	}
	
	public void validar(Autor autor) {
		if (existeAutorCadastrado(autor)) {
			throw new RegistroDuplicadoException("Autor ja existe no sistema");
		}
	}
	
	private boolean existeAutorCadastrado(Autor autor) {
		Optional<Autor> autorEncontrado = repository.findByNomeAndDatanascimentoAndNacionalidade(
				autor.getNome(), 
				autor.getDatanascimento(), 
				autor.getNacionalidade());
		
		if (autor.getId() == null) {
			return autorEncontrado.isPresent();			
		}
		return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
		
	}
	
	
}
