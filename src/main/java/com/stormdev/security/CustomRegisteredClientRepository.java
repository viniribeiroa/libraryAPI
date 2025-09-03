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
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
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
	private final TokenSettings tokenSettings;
	private final ClientSettings clientSettings;

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
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.clientSettings(clientSettings)
				.tokenSettings(tokenSettings)
				.build();
	}

}
