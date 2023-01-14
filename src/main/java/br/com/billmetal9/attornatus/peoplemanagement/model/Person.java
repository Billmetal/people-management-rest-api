package br.com.billmetal9.attornatus.peoplemanagement.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : classe que representa uma entidade de pessoa no banco de dados
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
public class Person {

	// Atributos de id, nome, data de Nascimento e lista de endereços
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private LocalDate birthDate;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Address> addressList;
}
