/**
 * Autor: VINICIUS
 * Data: 12 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stormdev.model.Autor;

/**
 * 
 */
public interface AutorRepository extends JpaRepository<Autor, UUID>{

	List<Autor> findByNome(String nome);
	
	List<Autor> findByNacionalidade(String nacionalidade);
	
	List<Autor> findByNomeAndNacionalidade(String nome, String nacionalidade);
	
	Optional<Autor> findByNomeAndDatanascimentoAndNacionalidade(
			String nome,
			LocalDate dataNascimento,
			String nacionalidade);
}
