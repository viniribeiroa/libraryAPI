/**
 * Autor: VINICIUS
 * Data: 29 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.stormdev.model.GeneroLivro;

/**
 * 
 */
public record ResultadoPesquisaLivroDTO(
		UUID id,
		String isbn,
		String titulo,
		LocalDate datapublicacao,
		GeneroLivro genero,
		BigDecimal preco,
		AutorDTO autor
		) {

}
