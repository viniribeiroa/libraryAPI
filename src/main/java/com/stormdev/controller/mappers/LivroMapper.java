/**
 * Autor: VINICIUS
 * Data: 30 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.stormdev.controller.dto.CadastroLivroDTO;
import com.stormdev.model.Livro;
import com.stormdev.repository.AutorRepository;

/**
 * 
 */
@Mapper(componentModel = "spring")
public abstract class LivroMapper {

	@Autowired
	AutorRepository repository;
	
	@Mapping(target = "autor", expression = "java(repository.findById(dto.idAutor()).orElse(null))")
	public abstract Livro toEntity(CadastroLivroDTO dto);
}
