package br.com.billmetal9.attornatus.peoplemanagement.service;

import java.util.List;

import br.com.billmetal9.attornatus.peoplemanagement.dto.PersonDTO;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : interface que determina as funções da Api em relação às pessoas
 * 
 * Autor : Willian T. K.
 * 
 * **/

public interface PersonService {

	// método que cria uma pessoa
	PersonDTO createPerson(PersonDTO personDto);
	
	// método que edita e atualiza uma pessoa
	PersonDTO editPerson(Long id,PersonDTO personDto);
	
	// método que procura uma pessoa pelo Id
	PersonDTO findPersonById(Long id);
	
	// método que lista as pessoas cadastradas
	List<PersonDTO> findAllPersons();
}
