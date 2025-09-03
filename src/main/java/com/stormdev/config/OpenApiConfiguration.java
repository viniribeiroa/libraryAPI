/**
 * Autor: VINICIUS
 * Data: 3 de set. de 2025
 * Descrição: TODO
 */
package com.stormdev.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * 
 */
@Configuration
@OpenAPIDefinition(
		info = @Info(
				title = "Library API",
				version = "v1",
				contact = @Contact(
						name = "Vinicius Ribeiro",
						email = "vinicius.ribeiro@libraryapi.com",
						url = "libraryapi.com"
						)
				)
		)
public class OpenApiConfiguration {

}
