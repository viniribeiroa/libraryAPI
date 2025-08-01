/**
 * Autor: VINICIUS
 * Data: 31 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import com.stormdev.model.GeneroLivro;
import com.stormdev.model.Livro;

/**
 * 
 */
public class LivroSpecs {
	
	public static Specification<Livro> isbnEqual(String isbn){
		return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
	}
	
	public static Specification<Livro> tituloLike(String titulo){
		return (root, query, cb) -> cb.like(cb.upper(root.get("titulo")),"%" + titulo.toUpperCase() + "%");
	}
	
	public static Specification<Livro> generoEqual(GeneroLivro genero){
		return (root, query, cb) -> cb.equal(root.get("genero"), genero);
	}

}
