/**
 * Autor: VINICIUS
 * Data: 12 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stormdev.model.Usuario;

/**
 * 
 */
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{

	Usuario findByLogin(String login);
}
