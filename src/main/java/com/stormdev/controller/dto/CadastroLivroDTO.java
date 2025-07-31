/**
 * Autor: VINICIUS
 * Data: 29 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.validator.constraints.ISBN;

import com.stormdev.model.GeneroLivro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

/**
 * 
 */
public record CadastroLivroDTO(
		@ISBN
		@NotBlank(message = "Campo Obrigatorio")
		String isbn,
		@NotBlank(message = "Campo Obrigatorio")
		String titulo,
		@NotNull(message = "Campo Obrigatorio")
		@Past(message = "Não pode ser uma data futura")
		LocalDate datapublicacao,
		GeneroLivro genero,
		BigDecimal preco,
		@NotNull(message = "Campo Obrigatorio")
		UUID idAutor
		) {

}
