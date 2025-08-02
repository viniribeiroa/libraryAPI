/**
 * Autor: VINICIUS
 * Data: 29 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("{id}")
	public ResponseEntity<Object> deletar(@PathVariable("id")String id){
		return service.obterPorId(UUID.fromString(id))
				.map(livro -> {
					service.deletar(livro);
					return ResponseEntity.noContent().build();
				}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public ResponseEntity<List<ResultadoPesquisaLivroDTO>> pesquisa(
			@RequestParam(value = "isbn", required = false)
			String isbn, 
			@RequestParam(value = "titulo", required = false)
			String titulo,
			@RequestParam(value = "nome-autor", required = false)
			String nomeAutor,
			@RequestParam(value = "genero", required = false)
			GeneroLivro genero, 
			@RequestParam(value = "ano-publicacao", required = false)
			Integer anopublicacao
			){
		
		var resultado = service.pesquisa(isbn, titulo, nomeAutor, genero, anopublicacao);
		var lista = resultado.stream().map(mapper::toDTO).collect(Collectors.toList());
		
		return ResponseEntity.ok(lista);
	}

}
