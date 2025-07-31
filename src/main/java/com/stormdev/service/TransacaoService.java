/**
 * Autor: VINICIUS
 * Data: 19 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stormdev.model.Autor;
import com.stormdev.model.GeneroLivro;
import com.stormdev.model.Livro;
import com.stormdev.repository.AutorRepository;
import com.stormdev.repository.LivroRepository;



/**
 * 
 */
@Service
public class TransacaoService {

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LivroRepository livroRepository;
	
	@Transactional
	public  void atualizacaoSemAtualizar() {
		var livro = livroRepository.findById(UUID.fromString("90997d17-c4ee-4b8e-868f-3a80305215ec")).orElse(null);
		
		livro.setDataPublicacao(LocalDate.of(2008, 10, 22));
		
	}

	@Transactional
	public void execultar() {
		// salva o autor
		Autor autor = new Autor();
		autor.setNome("martin");
		autor.setNacionalidade("Americano");
		autor.setDatanascimento(LocalDate.of(1982, 7, 13));

		autorRepository.save(autor);

		// salva o livro
		Livro livro = new Livro();
		livro.setIsbn("90007-8221");
		livro.setPreco(BigDecimal.valueOf(135));
		livro.setGenero(GeneroLivro.CIENCIA);
		livro.setTitulo("SQL for dumbs");
		livro.setDataPublicacao(LocalDate.of(1997, 9, 25));

		livro.setAutor(autor);

		livroRepository.save(livro);

		if (autor.getNome().equals("Jose")) {

			throw new RuntimeException("Rollback!!!");

		}
	}

}
