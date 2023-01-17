package br.com.billmetal9.attornatus.peoplemanagement.dto;

import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : classe que representa os dados de transferência de endereços
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation do Lombok que cria os Getters e Setters
@Data
//Annotation do Lombok que configura um criador para esta classe
@Builder
//Annotations do Lombok que cria os construtores para esta classe
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

	// Atributos de id,Principal ,Logradouro, CEP, Número e Cidade
	
	private Long id;
	
	@NotEmpty
	private boolean isPrincipal;
		
	@NotEmpty
	private String address;	
	
	@NotEmpty
	@Size(min = 8,max = 9)
	private String postalCode;
	
	@NotEmpty
	@NumberFormat
	private int number;
	
	@NotEmpty
	private String city;
}
