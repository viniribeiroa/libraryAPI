/**
 * Autor: VINICIUS
 * Data: 29 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stormdev.controller.dto.CadastroLivroDTO;
import com.stormdev.controller.dto.ResultadoPesquisaLivroDTO;
import com.stormdev.controller.mappers.LivroMapper;
import com.stormdev.model.GeneroLivro;
import com.stormdev.model.Livro;
import com.stormdev.service.LivroService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



/**
 * 
 */
@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController implements GenericController{
	
	private final LivroService service;
	private final LivroMapper mapper;
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto){
		
			Livro livro = mapper.toEntity(dto);
			service.salvar(livro);
			var url = gerarHeaderLocation(livro.getId());			
			return ResponseEntity.created(url).build();		
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable("id")String id){
		return service.obterPorId(UUID.fromString(id))
				.map(livro -> {
					var dto = mapper.toDTO(livro);
					return ResponseEntity.ok(dto);
				}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deletar(@PathVariable("id")String id){
		return service.obterPorId(UUID.fromString(id))
				.map(livro -> {
					service.deletar(livro);
					return ResponseEntity.noContent().build();
				}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public ResponseEntity<Page<ResultadoPesquisaLivroDTO>> pesquisa(
			@RequestParam(value = "isbn", required = false)
			String isbn, 
			@RequestParam(value = "titulo", required = false)
			String titulo,
			@RequestParam(value = "nome-autor", required = false)
			String nomeAutor,
			@RequestParam(value = "genero", required = false)
			GeneroLivro genero, 
			@RequestParam(value = "ano-publicacao", required = false)
			Integer anopublicacao,
			@RequestParam(value = "pagina", defaultValue = "0")
			Integer pagina,
			@RequestParam(value = "tamanho-pagina", defaultValue = "10")
			Integer tamanhoPagina
			){
		
		var paginaResultado = service.pesquisa(isbn, titulo, nomeAutor, genero, anopublicacao, pagina, tamanhoPagina);
		Page<ResultadoPesquisaLivroDTO> resultado = paginaResultado.map(mapper::toDTO);
		
				
		return ResponseEntity.ok(resultado);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> atualizar(@PathVariable("id") String id, @RequestBody @Valid CadastroLivroDTO dto){
		return service.obterPorId(UUID.fromString(id))
				.map(livro -> {
					Livro entidadeAux = mapper.toEntity(dto);
					livro.setDataPublicacao(entidadeAux.getDataPublicacao());
					livro.setIsbn(entidadeAux.getIsbn());
					livro.setPreco(entidadeAux.getPreco());
					livro.setGenero(entidadeAux.getGenero());
					livro.setTitulo(entidadeAux.getTitulo());
					livro.setAutor(entidadeAux.getAutor());
					
					service.atualizar(livro);
					return ResponseEntity.noContent().build();
				}).orElseGet(() -> ResponseEntity.notFound().build());
	}

}
