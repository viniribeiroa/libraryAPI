/**
 * Autor: VINICIUS
 * Data: 26 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stormdev.model.Client;

/**
 * 
 */
public interface ClientRepository extends JpaRepository<Client, UUID>{

	
	Client findByClientId(String clientId);

}
