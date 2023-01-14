package br.com.billmetal9.attornatus.peoplemanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : classe que representa uma entidade de endereços no banco de dados
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation para classe de entidade no banco de dados
@Entity
// Annotation do Lombok que cria os Getters e Setters
@Data
//Annotation do Lombok que configura um criador para esta classe
@Builder
//Annotations do Lombok que cria os construtores para esta classe
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	// Atributos de id, Logradouro, CEP, Número e Cidade
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(nullable = false)
	private String address;	
	
	@Column(nullable = false)
	private String postalCode;
	
	@Column(nullable = false)
	private int number;
	
	@Column(nullable = false)
	private String city;
}
