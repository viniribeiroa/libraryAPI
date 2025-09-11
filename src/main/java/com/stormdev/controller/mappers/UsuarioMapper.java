/**
 * Autor: VINICIUS
 * Data: 12 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller.mappers;

import org.mapstruct.Mapper;

import com.stormdev.controller.dto.UsuarioDTO;
import com.stormdev.model.Usuario;

/**
 * 
 */
@Mapper(componentModel = "spring") 
public interface UsuarioMapper {

	Usuario toEntity(UsuarioDTO dto);
}
