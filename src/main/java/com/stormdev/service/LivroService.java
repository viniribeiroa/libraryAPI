/**
 * Autor: VINICIUS
 * Data: 29 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.stormdev.model.GeneroLivro;
import com.stormdev.model.Livro;
import com.stormdev.repository.LivroRepository;
import com.stormdev.repository.specs.LivroSpecs;
import com.stormdev.validador.LivroValidator;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Service
@RequiredArgsConstructor
public class LivroService {

	private final LivroRepository repository;
	private final LivroValidator validator; 

	/**
	 * @param livro
	 */
	public Livro salvar(Livro livro) {
		validator.validar(livro);
		return repository.save(livro);
	}
	
	public Optional<Livro> obterPorId(UUID id){
		return repository.findById(id);
	}
	
	public void deletar(Livro livro) {
		repository.delete(livro);
	}
	
	//isbn, titulo, nome autor, genero, ano de publicação
	public Page<Livro> pesquisa(
			String isbn,
			String titulo,
			String nomeAutor,
			GeneroLivro genero,
			Integer anoPublicacao,
			Integer pagina,
			Integer tamanhoPagina){
		Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());
		
		if (isbn!= null) {
			specs = specs.and(LivroSpecs.isbnEqual(isbn));
		}if (titulo != null) {
			specs= specs.and(LivroSpecs.tituloLike(titulo));
		}if (genero != null) {
			specs = specs.and(LivroSpecs.generoEqual(genero));
		}if (anoPublicacao != null) {
			specs = specs.and(LivroSpecs.anoPublicacaoEqual(anoPublicacao));
		}if (nomeAutor != null) {
			specs = specs.and(LivroSpecs.nomeAutorLike(nomeAutor));
		}
		
		Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);
		
		return repository.findAll(specs, pageRequest);
	}

	/**
	 * @param livro
	 */
	public void atualizar(Livro livro) {
		if (livro.getId() == null) {
			throw new IllegalArgumentException("Para atualizar, é necessario que o livro esteja salvo na base.");
		}
		validator.validar(livro);
		repository.save(livro);
	}
}
