/**
 * Autor: VINICIUS
 * Data: 29 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.stormdev.model.Livro;
import com.stormdev.repository.LivroRepository;

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
		// TODO Auto-generated method stub
		return repository.save(livro);
	}
	
	public Optional<Livro> obterPorId(UUID id){
		return repository.findById(id);
	}
	
	public void deletar(Livro livro) {
		repository.delete(livro);
	}
}
