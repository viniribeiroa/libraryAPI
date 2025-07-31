/**
 * Autor: VINICIUS
 * Data: 29 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stormdev.controller.dto.CadastroLivroDTO;
import com.stormdev.controller.dto.ErroResposta;
import com.stormdev.controller.mappers.LivroMapper;
import com.stormdev.exceptions.RegistroDuplicadoException;
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
	public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto){
		try {
			Livro livro = mapper.toEntity(dto);
			service.salvar(livro);
			var url = gerarHeaderLocation(livro.getId());			
			return ResponseEntity.created(url).build();
			
		} catch (RegistroDuplicadoException e) {
			var erroDTO = ErroResposta.conflito(e.getMessage());
			return ResponseEntity.status(erroDTO.status()).body(erroDTO);
		}
	}

}
