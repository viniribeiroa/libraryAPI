/**
 * Autor: VINICIUS
 * Data: 22 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stormdev.controller.dto.AutorDTO;
import com.stormdev.controller.mappers.AutorMapper;
import com.stormdev.model.Autor;
import com.stormdev.service.AutorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
@Tag(name = "Autores")
@Slf4j
public class AutorController implements GenericController {

	private final AutorService service;
	private final AutorMapper mapper;

	/**
	 * public AutorController(AutorService service) {
	 * 
	 * this.service = service; }
	 **/

	@PostMapping
	@PreAuthorize("hasRole('GERENTE')")
	@Operation(summary = "Salvar", description = "Cadastrar novo autor")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cadastrado com Sucesso"),
		@ApiResponse(responseCode = "422", description = "Erro de validação"),
		@ApiResponse(responseCode = "409", description = "Autor ja Cadastrado")
	})
	public ResponseEntity<Void> salvar(@RequestBody @Valid AutorDTO dto) {

		
		Autor autor = mapper.toEntity(dto);
		
		service.Salvar(autor);
		URI location = gerarHeaderLocation(autor.getId());
		return ResponseEntity.created(location).build();

	}

	@GetMapping("{id}")
	@PreAuthorize("hasAnyRole('OPERADOR','GERENTE')")
	@Operation(summary = "Obter detalhes", description = "Obter os dados do autor pelo ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Autor encontrado"),
		@ApiResponse(responseCode = "404", description = "Autor não encontrado")
	})
	public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {
		var idAutor = UUID.fromString(id);

		return service.obterPorId(idAutor).map(autor -> {
			AutorDTO dto = mapper.toDTO(autor);
			return ResponseEntity.ok(dto);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('GERENTE')")
	@Operation(summary = "Deletar", description = "Deleta um autor existente")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Deletado com Sucesso"),
		@ApiResponse(responseCode = "404", description = "Autor não encontrado"),
		@ApiResponse(responseCode = "400", description = "Autor possui livro cadastrado")
	})
	public ResponseEntity<Void> deletar(@PathVariable("id") String id) {

		var idAutor = UUID.fromString(id);
		Optional<Autor> autorOptional = service.obterPorId(idAutor);

		if (autorOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		service.deletar(autorOptional.get());
		return ResponseEntity.noContent().build();

	}

	@GetMapping
	@PreAuthorize("hasAnyRole('OPERADOR','GERENTE')")
	@Operation(summary = "Pesquisar", description = "Realiza pesquisa de autores por paramentro")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Sucesso")
	})
	public ResponseEntity<List<AutorDTO>> pesquisar(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "nacionalidade", required = false) String nacionalidade) {
		
		log.trace("pesquisa autores");
		
		List<Autor> resultado = service.pesquisaByExample(nome, nacionalidade);
		List<AutorDTO> lista = resultado.stream().map(mapper::toDTO).collect(Collectors.toList());

		return ResponseEntity.ok(lista);
	}

	@PutMapping("{id}")
	@PreAuthorize("hasRole('GERENTE')")
	@Operation(summary = "Atualizar", description = "Atualiza um autor existente")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Atualizado com Sucesso"),
		@ApiResponse(responseCode = "404", description = "Autor não encontrado"),
		@ApiResponse(responseCode = "409", description = "Autor ja Cadastrado")
	})
	public ResponseEntity<Void> atualizar(@PathVariable("id") String id, @RequestBody @Valid AutorDTO dto) {
		
			var idAutor = UUID.fromString(id);
			Optional<Autor> autorOptional = service.obterPorId(idAutor);
			if (autorOptional.isEmpty()) {
				return ResponseEntity.notFound().build();
			}

			var autor = autorOptional.get();
			autor.setNome(dto.nome());
			autor.setNacionalidade(dto.nacionalidade());
			autor.setDatanascimento(dto.dataNascimento());
			service.atualizar(autor);

			return ResponseEntity.noContent().build();
	
	}
}
