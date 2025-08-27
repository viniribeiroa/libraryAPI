/**
 * Autor: VINICIUS
 * Data: 26 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stormdev.model.Client;
import com.stormdev.repository.ClientRepository;

import lombok.RequiredArgsConstructor;


/**
 * 
 */
@Service
@RequiredArgsConstructor
public class ClientService {
	
	private final ClientRepository repository;
	private final PasswordEncoder encoder;
	
	public Client salvar(Client client) {
		var senhaCriptografada = encoder.encode(client.getClientSecret());
		client.setClientSecret(senhaCriptografada);
		return repository.save(client);
	}
	
	public Client obterPorClientId(String clientId) {
		return repository.findById(clientId);
	}

}
