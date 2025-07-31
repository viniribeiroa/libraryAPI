/**
 * Autor: VINICIUS
 * Data: 19 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stormdev.service.TransacaoService;

/**
 * 
 */
@SpringBootTest
public class TransacoesTest {

	@Autowired
	TransacaoService transacaoService;

	/**
	 * commit -> confirmar as alteracoes RollBack -> desfazer as alteracoes
	 */
	@Test
	void transacaoSimples() {
		transacaoService.execultar();
	}

	@Test
	void transacaoEstadoManaged() {
		transacaoService.atualizacaoSemAtualizar();
	}

}
