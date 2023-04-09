package br.com.billmetal9.attornatus.peoplemanagement.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : classe que representa os dados necessários para criação de pessoas
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
public class CreatePersonDTO {

	@NotEmpty
	@Size(min = 2,max = 100)
	private String name;
	
	@NotEmpty
	@Size(min = 8,max = 10)
	private String birthDate;
}
