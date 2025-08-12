/**
 * Autor: VINICIUS
 * Data: 12 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller.dto;

import java.util.List;

/**
 * 
 */
public record UsuarioDTO(String login, String senha, List<String> roles) {

}
