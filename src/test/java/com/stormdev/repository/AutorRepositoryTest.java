/**
 * Autor: VINICIUS
 * Data: 14 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stormdev.model.Autor;
import com.stormdev.model.GeneroLivro;
import com.stormdev.model.Livro;





/**
 * 
 */
@SpringBootTest
public class AutorRepositoryTest {
	
	@Autowired
	AutorRepository repository;
	
	@Autowired
	LivroRepository livroRepository;
	
	@Test
	void salvarTest() {
		
		Autor autor = new Autor();
		autor.setNome("Jose");
		autor.setNacionalidade("brasileira");
		autor.setDatanascimento(LocalDate.of(1950, 1, 23));
		
		var autorSalvo = repository.save(autor);
		System.out.println("Aultor Salvo: " + autorSalvo);
		
	}
	
	@Test
	void atualizarTest() {
		var id = UUID.fromString("24754600-9c39-4977-84e8-d2e41ff375e4");
		Optional<Autor> possivelAutor = repository.findById(id);
		
		if(possivelAutor.isPresent()) {
			
			Autor autorEncontrado = possivelAutor.get();
			System.out.println(autorEncontrado);
			
			autorEncontrado.setDatanascimento(LocalDate.of(1960, 06, 18));
			
			repository.save(autorEncontrado);
			
			
		}
		
		
	}
	
	@Test
	void listarTest() {
		
		List<Autor> lista = repository.findAll();
		lista.forEach(System.out::println);
	}
	
	@Test
	void countTest() {
		System.out.println("contagem de autores:  " + repository.count());
	}
	
	@Test
	void deleteByIdTeste() {
		var id = UUID.fromString("24754600-9c39-4977-84e8-d2e41ff375e4");
		repository.deleteById(id);
	}
	
	@Test
	void deleteByObjetoTeste() {
		var id = UUID.fromString("24754600-9c39-4977-84e8-d2e41ff375e4");
		var jose = repository.findById(id).get();
		repository.delete(jose);
	}
	
	@Test
	void salvarAutorcomLivrosTeste() {
		Autor autor = new Autor();
		autor.setNome("Antonio");
		autor.setNacionalidade("brasileira");
		autor.setDatanascimento(LocalDate.of(1919, 11, 20));
		
		Livro livro = new Livro();
		livro.setIsbn("91123-87851");
		livro.setPreco(BigDecimal.valueOf(135));
		livro.setGenero(GeneroLivro.FANTASIA);
		livro.setTitulo("Metatron");
		livro.setDataPublicacao(LocalDate.of(1947, 9, 25));
		livro.setAutor(autor);
		
		Livro livro2 = new Livro();
		livro2.setIsbn("40023-566851");
		livro2.setPreco(BigDecimal.valueOf(135));
		livro2.setGenero(GeneroLivro.FANTASIA);
		livro2.setTitulo("o deserto");
		livro2.setDataPublicacao(LocalDate.of(1967, 4, 3));
		livro2.setAutor(autor);
		
		autor.setLivros(new ArrayList<>());
		autor.getLivros().add(livro);
		autor.getLivros().add(livro2);
		
		repository.save(autor);
		
		livroRepository.saveAll(autor.getLivros());
	}
	
	@Test
	void listarLivrosAutorTest() {
		var id = UUID.fromString("cf4b5939-30a6-4c91-8fdf-b45e888c3bbc");
		var autor = repository.findById(id).get();
		
		List<Livro> livrosLista = livroRepository.findByAutor(autor);
		autor.setLivros(livrosLista);
		
		autor.getLivros().forEach(System.out::println);
	}

}
