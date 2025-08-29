/**
 * Autor: VINICIUS
 * Data: 6 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.stormdev.security.CustomUserDetailsService;
import com.stormdev.security.LoginSocialSuccessHandler;
import com.stormdev.service.UsuarioService;

/**
 * 
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, LoginSocialSuccessHandler successHandler) throws Exception{
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.formLogin(configurer ->{
					configurer.loginPage("/login");
				})
//				.formLogin(Customizer.withDefaults())
//				.httpBasic(Customizer.withDefaults())
				.authorizeHttpRequests(authorize -> {
					authorize.requestMatchers("/login").permitAll();
					authorize.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll();
					//authorize.requestMatchers(HttpMethod.POST, "/autores/**").hasRole("ADMIN");
					//authorize.requestMatchers(HttpMethod.DELETE, "/autores/**").hasRole("ADMIN");
					//authorize.requestMatchers(HttpMethod.PUT, "/autores/**").hasRole("ADMIN");
					//authorize.requestMatchers(HttpMethod.GET, "/autores/**").hasAnyRole("USER", "ADMIN");
					authorize.anyRequest().authenticated();
				})
				.oauth2Login(oauth2 -> {
					oauth2
					.loginPage("/login")
					.successHandler(successHandler);
				})
				.oauth2ResourceServer(oauth2RS -> oauth2RS.jwt(Customizer.withDefaults()))
				.build();
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(10);
//	}
//	
////	@Bean
//	public UserDetailsService userDetailsService(UsuarioService usuarioService) {
//		
//		
////		UserDetails user1 = User.builder()
////				.username("usuario")
////				.password(encoder.encode("1234"))
////				.roles("USER")
////				.build();
////		
////		UserDetails user2 = User.builder()
////				.username("admin")
////				.password(encoder.encode("1234"))
////				.roles("ADMIN")
////				.build();
////		
////		return new InMemoryUserDetailsManager(user1, user2);
//		return new CustomUserDetailsService(usuarioService);
//	}
	
	@Bean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults(""); //pode ser usado para adicionar prefixo as roles
	}
	
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		var authoritiesConverter = new JwtGrantedAuthoritiesConverter();
		authoritiesConverter.setAuthorityPrefix(""); // pode ser usado para adicionar prefixo personalizado JWT Token
		
		var converter = new JwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
		
		return converter;
	}
}
