/**
 * Autor: VINICIUS
 * Data: 26 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stormdev.model.Client;
import com.stormdev.service.ClientService;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

	private final ClientService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('GERENTE')")
	public void salvar(@RequestBody Client client){
		service.salvar(client);
	}
}
