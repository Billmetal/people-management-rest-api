package br.com.billmetal9.attornatus.peoplemanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.billmetal9.attornatus.peoplemanagement.dto.PersonDTO;
import br.com.billmetal9.attornatus.peoplemanagement.service.PersonService;
import jakarta.validation.Valid;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : classe de controle dos endpoints da API para pessoas
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation que indica que esta é uma classe Controle REST
@RestController
// Configura o endpoint principal deste controle
@RequestMapping("/api/v1/people")
public class PersonController {
	
	// serviço de pessoas que será instanciado no construtor da classe
	private final PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	// Annotaion que determina um request de POST no endpoint people e espera receber um RequestBody
	@PostMapping()
	// método que recebe o RequestBody de PersonDTO contendo name e birthDate da pessoa que será criada e
	//retorna a pessoa criada
	public ResponseEntity<PersonDTO> createPerson(@RequestBody @Valid PersonDTO personDto){
		PersonDTO result = personService.createPerson(personDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	// Annotaion que determina um request de GET no endpoint people 
	@GetMapping()
	// método que retorna a lista de pessoas salvas no banco de dados
	public ResponseEntity<List<PersonDTO>> findAllPersons(){
		List<PersonDTO> result = personService.findAllPersons();
		return ResponseEntity.ok(result);
	}
	
	// Annotaion que determina um request de GET no endpoint people/ o id da pessoa procurada 
	@GetMapping("/{personId}")
	// método que recebe o id da pessoa procurada e retorna a pessoa se existir
	public ResponseEntity<PersonDTO> findPersonById(@PathVariable Long personId){
		PersonDTO result = personService.findPersonById(personId); 
		return ResponseEntity.ok(result);
	}
	
	// Annotaion que determina um request de PUT no endpoint people/ o id da pessoa a ser editada
	@PutMapping("/{personId}")
	// método que recebe o id da pessoa a ser editada e o RequestBody de PersonDTO contendo name e birthDate para editar na pessoa e
	//retorna a pessoa editada
	public ResponseEntity<PersonDTO> editPerson(@PathVariable Long personId,@RequestBody @Valid PersonDTO personDto){
		PersonDTO result = personService.editPerson(personId, personDto);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
