/**
 * Autor: VINICIUS
 * Data: 28 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.security;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

import com.stormdev.service.ClientService;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Component
@RequiredArgsConstructor
public class CustomRegisteredClientRepository implements RegisteredClientRepository{
	
	private final ClientService clientService;

	@Override
	public void save(RegisteredClient registeredClient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RegisteredClient findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegisteredClient findByClientId(String clientId) {
		
		var client = clientService.obterPorClientId(clientId);
		
		if(client == null) {
			return null;
		}
		return RegisteredClient
				.withId(client.getId().toString())
				.clientId(client.getClientId())
				.clientSecret(client.getClientSecret())
				.redirectUri(client.getRedirectURI())
				.scope(client.getScope())
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.build();
	}

}
