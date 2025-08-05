/**
 * Autor: VINICIUS
 * Data: 4 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.validador;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.stormdev.exceptions.RegistroDuplicadoException;
import com.stormdev.model.Livro;
import com.stormdev.repository.LivroRepository;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Component
@RequiredArgsConstructor
public class LivroValidator {

	private final LivroRepository repository;
	
	public void validar(Livro livro) {
		if(existeLivroComIsbn(livro)) {
			throw new RegistroDuplicadoException("Registro Duplicado ISBN");
		}
	}
	
	private boolean existeLivroComIsbn(Livro livro) {
		Optional<Livro> livroEncontrado = repository.findByIsbn(livro.getIsbn());
		
		if (livro.getId() == null) {
			return livroEncontrado.isPresent();
		}
		return livroEncontrado
				.map(Livro::getId)
				.stream()
				.anyMatch(id -> !id.equals(livro.getId()));
	}
}
