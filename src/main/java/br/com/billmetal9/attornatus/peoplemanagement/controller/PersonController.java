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
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : classe de controle dos endpoints da API para pessoas
 * 
 * Autor : Willian T. K.
 * 
 * **/


@OpenAPIDefinition(
        info = @Info(
                title = "Gerenciamento de Pessoas",
                description = "REST Api para gerenciamento de pessoas e seus endereços",
                version = "1.0.0",
                contact = @Contact(name = "Willian T. K.", url = "https://github.com/Billmetal", email = "billmetal9@gmail.com")
                ),
        servers = @Server(
                url = "URL DO SEU SERVER",
                description = "Localização do servidor"
//                variables = {
//                        @ServerVariable(name = "serverUrl", defaultValue = "localhost"),
//                        @ServerVariable(name = "serverHttpPort", defaultValue = "8080")
//                	}
                )
			)
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
	
	@Operation(
	        summary = "Cria uma pessoa e salva no banco de dados",
	        description = "Recebe as informações de nome e data de nascimento para criar uma pessoa.",
	        method = "POST",
    		parameters = {
	        		@Parameter(name = "name",description = "Nome da pessoa"),
	        		@Parameter(name = "birthDate",description = "Data de Nascimento da pessoa (formato dd/MM/yyyy)")
	        },
	        tags = {
	                "Criar uma pessoa"
	                },
	        responses = {
	                @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso."),
	                @ApiResponse(responseCode = "400", description = "Erro ao realizar o Post.")
	                }
	        )
	// Annotaion que determina um request de POST no endpoint people e espera receber um RequestBody
	@PostMapping()
	// método que recebe o RequestBody de PersonDTO contendo name e birthDate da pessoa que será criada e
	//retorna a pessoa criada
	public ResponseEntity<PersonDTO> createPerson(@RequestBody @Valid PersonDTO personDto){
		PersonDTO result = personService.createPerson(personDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@Operation(
	        summary = "Lista todas as pessoas salvas no banco de dados",
	        description = "Busca por todas as pessoas salvas no banco de dados.",
	        method = "GET",
	        tags = {
	                "Listar todas pessoas"
	                },
	        responses = {
	                @ApiResponse(responseCode = "200", description = "Listagem de pessoas recebida."),
	                @ApiResponse(responseCode = "400", description = "Erro ao realizar o Get.")
	                }
	        )
	// Annotaion que determina um request de GET no endpoint people 
	@GetMapping()
	// método que retorna a lista de pessoas salvas no banco de dados
	public ResponseEntity<List<PersonDTO>> findAllPersons(){
		List<PersonDTO> result = personService.findAllPersons();
		return ResponseEntity.ok(result);
	}
	
	@Operation(
	        summary = "Busca uma pessoa pelo Id",
	        description = "Recebe o Id da pessoa a ser buscada no banco de dados e retorna a pessoa encontrada se existir.",
	        parameters = {
	        		@Parameter(name = "personId", description = "Id da pessoa buscada")
	        },
	        method = "GET",
	        tags = {
	                "Buscar uma pessoa"
	                },
	        responses = {
	                @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso."),
	                @ApiResponse(responseCode = "400", description = "Erro ao realizar o Get."),
	                @ApiResponse(responseCode = "404", description = "Nenhuma pessoa encontrada com esse Id.")
	                }
	        )
	// Annotaion que determina um request de GET no endpoint people/ o id da pessoa procurada 
	@GetMapping("/{personId}")
	// método que recebe o id da pessoa procurada e retorna a pessoa se existir
	public ResponseEntity<PersonDTO> findPersonById(@PathVariable Long personId){
		PersonDTO result = personService.findPersonById(personId); 
		return ResponseEntity.ok(result);
	}
	
	@Operation(
	        summary = "Edita uma pessoa pelo Id",
	        description = "Recebe o Id da pessoa a ser editada e recebe as informações de Nome e Data de "
	        		+ "Nascimento para ser alterado na pessoa.",
	        parameters = {
	        		@Parameter(name = "personId", description = "Id da pessoa que será editada"),
	        		@Parameter(name = "name", description = "Novo Nome para a pessoa"),
	        		@Parameter(name = "birthDate", description = "Nova Data de Nascimento para a pessoa (formato dd/MM/yyyy)")
	        },
	        method = "PUT",
	        tags = {
	                "Editar uma pessoa"
	                },
	        responses = {
	                @ApiResponse(responseCode = "200", description = "Pessoa editada com sucesso."),
	                @ApiResponse(responseCode = "400", description = "Erro ao realizar o Put."),
	                @ApiResponse(responseCode = "404", description = "Nenhuma pessoa encontrada com esse Id.")
	                }
	        )
	// Annotaion que determina um request de PUT no endpoint people/ o id da pessoa a ser editada
	@PutMapping("/{personId}")
	// método que recebe o id da pessoa a ser editada e o RequestBody de PersonDTO contendo name e birthDate para editar na pessoa e
	//retorna a pessoa editada
	public ResponseEntity<PersonDTO> editPerson(@PathVariable Long personId,@RequestBody @Valid PersonDTO personDto){
		PersonDTO result = personService.editPerson(personId, personDto);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
