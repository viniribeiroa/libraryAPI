/**
 * Autor: VINICIUS
 * Data: 6 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 */
@Controller
public class LoginViewController {
	
	@GetMapping("/login")
	public String paginaLogin() {
		return "login";
	}

}
