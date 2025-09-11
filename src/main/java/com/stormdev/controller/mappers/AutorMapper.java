/**
 * Autor: VINICIUS
 * Data: 30 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller.mappers;

import org.mapstruct.Mapper;

import com.stormdev.controller.dto.AutorDTO;
import com.stormdev.model.Autor;

/**
 * 
 */
@Mapper(componentModel = "spring") 
public interface AutorMapper {

	Autor toEntity(AutorDTO dto);
	
	AutorDTO toDTO(Autor autor);
		
	
}
