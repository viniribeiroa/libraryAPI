/**
 * Autor: VINICIUS
 * Data: 31 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import com.stormdev.model.GeneroLivro;
import com.stormdev.model.Livro;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

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
	
	public static Specification<Livro> anoPublicacaoEqual(Integer anoPublicacao){
		return (root, query, cb) -> 
		cb.equal(cb.function("to_char", String.class, root.get("dataPublicacao"), cb.literal("YYYY")), anoPublicacao.toString());
	}
	
	public static Specification<Livro> nomeAutorLike(String nome){
		return (root, query, cb) -> {
			Join<Object, Object> joinAutor = root.join("autor", JoinType.INNER);
			return cb.like(cb.upper(joinAutor.get("nome")), "%" + nome.toUpperCase() + "%");
			
//			return cb.like(cb.upper(root.get("autor").get("nome")), "%" + nome.toUpperCase() + "%"); //consulta direta sem fazer o join mais simples
		};
	}

}
