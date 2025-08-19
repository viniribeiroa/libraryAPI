/**
 * Autor: VINICIUS
 * Data: 12 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * 
 */
public record UsuarioDTO(
		@NotBlank(message = "Campo Obrigatorio") 
		String login,
		@NotBlank(message = "Campo Obrigatorio")
		String senha, 
		@Email(message = "Formato de email invalido")
		@NotBlank(message = "Campo Obrigatorio")
		String email,
		List<String> roles) {

}
