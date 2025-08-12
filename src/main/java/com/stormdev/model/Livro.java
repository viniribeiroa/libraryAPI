/**
 * Autor: VINICIUS
 * Data: 12 de jul. de 2025
 * Descrição: TODO
 */
package com.stormdev.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

/**
 * 
 */
@Entity
@Table(name = "livro")
@Data
@ToString(exclude = "autor")// não trazer o autor  no toString
@EntityListeners(AuditingEntityListener.class)
public class Livro {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "isbn", length = 20, nullable = false )
	private String isbn;
	
	@Column(name = "titulo", length = 150, nullable = false )
	private String titulo;

	@Column(name = "data_publicacao")
	private LocalDate dataPublicacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "genero", length = 30, nullable = false )
	private GeneroLivro genero;
	
	@Column(name = "preco",precision = 18, scale = 2)
	private BigDecimal preco;
	
	@ManyToOne(
			//cascade = CascadeType.ALL
			fetch = FetchType.LAZY)//carregamento lento so carregara a principal não  os relacionamentos 
	@JoinColumn(name = "id_autor")
	private Autor autor;
	
	@CreatedDate
	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro;
	
	@LastModifiedDate
	@Column(name = "date_atualizacao")
	private LocalDateTime dataAtualizacao;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
}
