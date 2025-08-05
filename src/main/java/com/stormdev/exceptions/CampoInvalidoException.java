/**
 * Autor: VINICIUS
 * Data: 4 de ago. de 2025
 * Descrição: TODO
 */
package com.stormdev.exceptions;

import lombok.Getter;

/**
 * 
 */
public class CampoInvalidoException extends RuntimeException{
	
	@Getter
	private String campo;
	
	public CampoInvalidoException(String campo, String mensagem) {
		super(mensagem);
		this.campo = campo;
	}

}
