/**
 * Autor: VINICIUS
 * Data: 27 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stormdev.controller.dto.ErroResposta;
import com.stormdev.controller.dto.ErrorCampo;
import com.stormdev.exceptions.OperacaoNaoPermitidaException;
import com.stormdev.exceptions.RegistroDuplicadoException;

/**
 * 
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ErroResposta handleMethodSArgumentNotValidException(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getFieldErrors();
		
		List<ErrorCampo> listaErros = fieldErrors
				.stream()
				.map(fe -> new ErrorCampo(fe.getField(), fe.getDefaultMessage()))
				.collect(Collectors.toList());
		
		return new ErroResposta(
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro de Validação",
				listaErros);
	}
	
	
	@ExceptionHandler(RegistroDuplicadoException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErroResposta handleRegistroDuplicadoException(RegistroDuplicadoException e) {
		return ErroResposta.conflito(e.getMessage());
		
	}
	
	@ExceptionHandler(OperacaoNaoPermitidaException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErroResposta handleOperacaoNaoPermitidaException(OperacaoNaoPermitidaException e) {
		return ErroResposta.respostaPadrao(e.getMessage());
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErroResposta handleErrosNaoTratado(RuntimeException e) {
		return new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um Erro Inesperado. Entre em contato com o Administrador", List.of());
	}

}
