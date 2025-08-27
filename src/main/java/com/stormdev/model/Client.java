/**
 * Autor: VINICIUS
 * Data: 26 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 
 */
@Entity
@Table
@Data
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "client_id")
	private String clientId;
	
	@Column(name = "client_secret")
	private String clientSecret;
	
	@Column(name = "redirect_uri")
	private String redirectURI;
	
	@Column(name = "scope")
	private String scope;
}
