/**
 * Autor: VINICIUS
 * Data: 22 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

/**
 * 
 */
public record AutorDTO(
		UUID id, 
		@NotBlank(message = "Campo Obrigatorio")
		@Size(min = 5, max = 100, message = "campo acima de 100 caractere")
		String nome, 
		@NotNull(message = "Campo Obrigatorio")
		@Past(message = "Não pode ser uma data Futura")
		LocalDate dataNascimento, 
		@NotBlank(message = "Campo Obrigatorio")
		@Size(min = 2, max = 50, message = "campo acima de 50 caractere")
		String nacionalidade) {

	
}
