/**
 * Autor: VINICIUS
 * Data: 22 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.stormdev.exceptions.OperacaoNaoPermitidaException;
import com.stormdev.model.Autor;
import com.stormdev.model.Usuario;
import com.stormdev.repository.AutorRepository;
import com.stormdev.repository.LivroRepository;
import com.stormdev.security.SecurityService;
import com.stormdev.validador.AutorValidador;

import lombok.RequiredArgsConstructor;


/**
 * 
 */
@Service
@RequiredArgsConstructor
public class AutorService {

	private final AutorRepository autorRepository;
	private final AutorValidador validador;
	private final LivroRepository livroRepository;
	private final SecurityService securityService;
	/**public AutorService(AutorRepository autorRepository, AutorValidador validador, LivroRepository livroRepository) {
		this.autorRepository = autorRepository;
		this.validador = validador;
		this.livroRepository= livroRepository;
	}**/

	public Autor Salvar(Autor autor) {
		validador.validar(autor);
		Usuario usuario = securityService.obterUsuarioLogado();
		autor.setUsuario(usuario);
		return autorRepository.save(autor);
	}
	
	public void atualizar(Autor autor) {
		if (autor.getId() == null) {
			throw new IllegalArgumentException("Para atualiza é necessario que o autor estaja salvo na base");
		}
		validador.validar(autor);
		autorRepository.save(autor);
	}
	
	public Optional<Autor> obterPorId(UUID id){
		return autorRepository.findById(id);
	}
	
	public void deletar(Autor autor) {
		if (possuiLivro(autor)) {
			throw new OperacaoNaoPermitidaException("Autor possui livros cadastrados");
		}
		autorRepository.delete(autor);
	}
	
	public List<Autor> pesquisa(String nome, String nacionalidade){
		
		if(nome != null && nacionalidade != null) {
			return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
		}
		if (nome != null) {
			return autorRepository.findByNome(nome);
		}
		if (nacionalidade != null) {
			return autorRepository.findByNacionalidade(nacionalidade);
		}
		return autorRepository.findAll();
	}
	
	public List<Autor> pesquisaByExample(String nome, String nacionalidade){
		var autor = new Autor();
		autor.setNome(nome);
		autor.setNacionalidade(nacionalidade);
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreNullValues()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example<Autor> autorExample = Example.of(autor, matcher);
		
		return autorRepository.findAll(autorExample);
	}
	
	public boolean possuiLivro(Autor autor) { 
		return livroRepository.existsByAutor(autor);
	}
}
