/**
 * Autor: VINICIUS
 * Data: 12 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stormdev.controller.dto.UsuarioDTO;
import com.stormdev.controller.mappers.UsuarioMapper;
import com.stormdev.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 
 */
@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService service;
	private final UsuarioMapper mapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody @Valid UsuarioDTO dto) {
		var usuario = mapper.toEntity(dto);
		service.salvar(usuario);
	}
}
