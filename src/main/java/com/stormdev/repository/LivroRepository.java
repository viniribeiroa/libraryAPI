/**
 * Autor: VINICIUS
 * Data: 12 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.stormdev.model.Autor;
import com.stormdev.model.GeneroLivro;
import com.stormdev.model.Livro;

/**
 * 
 */
public interface LivroRepository extends JpaRepository<Livro, UUID>{
	
	
	//Query method
	//select * from livros where id_autor = id
	List<Livro> findByAutor(Autor autor);
	
	//select *from livro where titulo = titulo
	List<Livro> findByTitulo(String titulo);
	
	//select * from livro and preco
	List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);
	
	List<Livro> findByTituloOrAutor(String titulo, Autor autor);
	
	//select * from livro where data_publicacao between ? and ?
	List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);
	
	List<Livro> findByTituloContaining(String titulo);
	
	/**
	 * usando @Query
	 * JPQL -> referencia as entidades e as propriedades
	 */
	@Query("select l from Livro as l order by l.titulo, l.preco")
	List<Livro> listarTodosOrdenadoPorTituloAndPreco();
	
	@Query("select a from Livro l join l.autor a")
	List<Autor> listarAutoresdosLivros();
	
	@Query("select distinct l.titulo from Livro l")
	List<String> listarNomesDiferentesLivros();
	
	@Query("""
			select l.genero
			from Livro l
			join l.autor a
			where a.nacionalidade = 'brasileira'
			order by l.genero
			""")
	List<String> listarGeneroAutoresBrasileiros();
	
	//named parameters -> paramentros nomeados
	@Query("select l from Livro l where l.genero = :nomeParametro order by :parametroOrdenacao ")
	List<Livro> findGenero(
			@Param("nomeParametro") GeneroLivro generoLivro,
			@Param("parametroOrdenacao") String nomePropriedade);
	
	//positional parameters
	@Query("select l from Livro l where l.genero = ?1 order by ?2 ")
	List<Livro> findGeneroPositionalParameters(
			 GeneroLivro generoLivro,
			 String nomePropriedade);
	
	@Transactional
	@Modifying
	@Query("delete from Livro where genero = ?1 ")
	void deleteByGenero(GeneroLivro generoLivro);
	
	@Transactional
	@Modifying
	@Query("UPDATE Livro l SET l.dataPublicacao = :novaData WHERE l.titulo = :titulo")
	void updateDataPublicacao(@Param("titulo") String titulo, @Param("novaData") LocalDate novaData);

	boolean existsByAutor(Autor autor);

}
