/**
 * Autor: VINICIUS
 * Data: 15 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.repository;




import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.stormdev.model.Autor;
import com.stormdev.model.GeneroLivro;
import com.stormdev.model.Livro;




/**
 * 
 */
@SpringBootTest
class LivroRepositoryTest {

	@Autowired
	LivroRepository repository;
	
	@Autowired
	AutorRepository autorRepository;
	
	@Test
	void salvartest() {
		Livro livro = new Livro();
		livro.setIsbn("90887-8432");
		livro.setPreco(BigDecimal.valueOf(135));
		livro.setGenero(GeneroLivro.TERROR);
		livro.setTitulo("pantano");
		livro.setDataPublicacao(LocalDate.of(1982, 12, 23));
		
		
		Autor autor = autorRepository.findById(UUID.fromString("76ec4fe4-e1c1-41de-b258-e5329eb666ed")).orElse(null);
		livro.setAutor(autor);
		
		repository.save(livro);
	}
	
	@Test
	void salvarCascadetest() {
		Livro livro = new Livro();
		livro.setIsbn("90587-8433");
		livro.setPreco(BigDecimal.valueOf(135));
		livro.setGenero(GeneroLivro.CIENCIA);
		livro.setTitulo("codigo sujo");
		livro.setDataPublicacao(LocalDate.of(1987, 11, 05));
		
		Autor autor = new Autor();
		autor.setNome("Matheus");
		autor.setNacionalidade("brasileira");
		autor.setDatanascimento(LocalDate.of(1970, 7, 13));
		
		livro.setAutor(autor);
		
		repository.save(livro);
	}
	
	@Test
	void salvarAutorLivrotest() {
		Livro livro = new Livro();
		livro.setIsbn("91517-8411");
		livro.setPreco(BigDecimal.valueOf(135));
		livro.setGenero(GeneroLivro.FANTASIA);
		livro.setTitulo("historia sem fim");
		livro.setDataPublicacao(LocalDate.of(1967, 9, 25));
		
		Autor autor = new Autor();
		autor.setNome("Yahna");
		autor.setNacionalidade("Ingesa");
		autor.setDatanascimento(LocalDate.of(1932,7, 13));
		
		autorRepository.save(autor);
		
		livro.setAutor(autor);
		
		repository.save(livro);
	}
	
	@Test
	void atualizarAutorLivro() {
		var livroPraAtualiza = repository.findById(UUID.fromString("90997d17-c4ee-4b8e-868f-3a80305215ec")).orElse(null);
		Autor autor = 	autorRepository.findById(UUID.fromString("647f8ce8-afa6-41d4-9d0d-e784ebe7e656")).orElse(null);
		
		livroPraAtualiza.setAutor(autor);
		repository.save(livroPraAtualiza);
		
	}
	
	@Test
	void deletar() {
		UUID id = UUID.fromString("90997d17-c4ee-4b8e-868f-3a80305215ec");
		repository.deleteById(id);
	}
	
	@Test
	@Transactional//para trazer o relacionameto necessario para consulta mesmo LAZY
	void buscarlivrosTest() {
		UUID iUuid = UUID.fromString("90997d17-c4ee-4b8e-868f-3a80305215ec");
		Livro livro = repository.findById(iUuid).orElse(null);
		System.out.println(livro.getTitulo());
		System.out.println(livro.getAutor().getNome());
	}
	
	@Test
	void pesquisaPorTituloTeste() {
		List<Livro> lista = repository.findByTitulo("o deserto");
		lista.forEach(System.out::println);
	}
	
	@Test
	void pequisaPorTituloEPreco() {
		
		var preco = BigDecimal.valueOf(135);
		List<Livro> listaList = repository.findByTituloAndPreco("o deserto", preco);
		listaList.forEach(System.out::println);
	}
	
	@Test
	void pesquisaPorTituloOuAutor() {
		
		var tituloPesquisa = "o deserto";
		Autor autor = 	autorRepository.findById(UUID.fromString("cf4b5939-30a6-4c91-8fdf-b45e888c3bbc")).orElse(null);
		
		List<Livro> lista = repository.findByTituloOrAutor(null, autor);
		lista.forEach(System.out::println);
	}
	
	@Test
	void pesquisaPorDataDePublicacao() {
		List<Livro> listaList = repository.findByDataPublicacaoBetween(LocalDate.of(1900, 1, 1), LocalDate.of(1990, 12, 31));
		listaList.forEach(System.out::println);
	}
	
	@Test
	void pesquisaPorTituloQueContenha() {
		List<Livro> listaList = repository.findByTituloContaining("des");
		listaList.forEach(System.out::println);
	}
	
	@Test
	void listarLivrosComQueryJPQL() {
		var resultado = repository.listarTodosOrdenadoPorTituloAndPreco();
		resultado.forEach(System.out::println);
		
	}
	
	@Test
	void listarAutoresDosLivros() {
		var resultado = repository.listarAutoresdosLivros();
		resultado.forEach(System.out::println);
	}
	
	@Test
	void listarTitulosNaoRepetidosDosLivros() {
		var resultado = repository.listarNomesDiferentesLivros();
		resultado.forEach(System.out::println);
	}
	
	@Test
	void listarLivrosDeAutoresBrasileiros() {
		var resultado = repository.listarGeneroAutoresBrasileiros();
		resultado.forEach(System.out::println);
	}
	
	@Test
	void listarPeloGeneroQueryParamTeste() {
		var resultado = repository.findGenero(GeneroLivro.TERROR, "dataPublicacao");
		resultado.forEach(System.out::println);
	}
	
	@Test
	void listarPeloGeneroPositionalParamTeste() {
		var resultado = repository.findGeneroPositionalParameters(GeneroLivro.TERROR, "dataPublicacao");
		resultado.forEach(System.out::println);
	}
	
	@Test
	void deletePorGeneroTest() {
		repository.deleteByGenero(GeneroLivro.CIENCIA);
	}
	
	@Test
	void updatePublicacaoTest() {
		repository.updateDataPublicacao("Metatron", LocalDate.of(1855, 5, 13));
	}
}
