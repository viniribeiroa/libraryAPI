/**
 * Autor: VINICIUS
 * Data: 22 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

/**
 * 
 */
@Schema(name = "Autor")
public record AutorDTO(
		UUID id, 
		@NotBlank(message = "Campo Obrigatorio")
		@Size(min = 5, max = 100, message = "campo acima de 100 caractere")
		@Schema(name = "nome")
		String nome, 
		@NotNull(message = "Campo Obrigatorio")
		@Past(message = "Não pode ser uma data Futura")
		@Schema(name = "Data Nascimento")
		LocalDate dataNascimento, 
		@NotBlank(message = "Campo Obrigatorio")
		@Size(min = 2, max = 50, message = "campo acima de 50 caractere")
		@Schema(name = "Nacionalidade")
		String nacionalidade) {

	
}
