/**
 * Autor: VINICIUS
 * Data: 29 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.stormdev.model.GeneroLivro;
import com.stormdev.model.Livro;
import com.stormdev.repository.LivroRepository;
import com.stormdev.repository.specs.LivroSpecs;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Service
@RequiredArgsConstructor
public class LivroService {

	private  final LivroRepository repository;

	/**
	 * @param livro
	 */
	public Livro salvar(Livro livro) {
		
		return repository.save(livro);
	}
	
	public Optional<Livro> obterPorId(UUID id){
		return repository.findById(id);
	}
	
	public void deletar(Livro livro) {
		repository.delete(livro);
	}
	
	//isbn, titulo, nome autor, genero, ano de publicação
	public List<Livro> pesquisa(
			String isbn,
			String titulo,
			String nomeAutor,
			GeneroLivro genero,
			Integer anoPublicacao){
		Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());
		
		if (isbn!= null) {
			specs = specs.and(LivroSpecs.isbnEqual(isbn));
		}if (titulo != null) {
			specs= specs.and(LivroSpecs.tituloLike(titulo));
		}if (genero != null) {
			specs = specs.and(LivroSpecs.generoEqual(genero));
		}if (anoPublicacao != null) {
			specs = specs.and(LivroSpecs.anoPublicacaoEqual(anoPublicacao));
		}
		
		return repository.findAll(specs);
	}
}
