/**
 * Autor: VINICIUS
 * Data: 29 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.service;

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
}
