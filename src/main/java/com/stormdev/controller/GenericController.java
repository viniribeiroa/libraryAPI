/**
 * Autor: VINICIUS
 * Data: 30 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * 
 */
public interface GenericController {

	default URI gerarHeaderLocation(UUID id) {
		return ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id) 
				.toUri();
	}
}
