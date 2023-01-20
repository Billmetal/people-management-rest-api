package br.com.billmetal9.attornatus.peoplemanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.billmetal9.attornatus.peoplemanagement.dto.AddressDTO;
import br.com.billmetal9.attornatus.peoplemanagement.dto.PersonDTO;
import br.com.billmetal9.attornatus.peoplemanagement.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : classe de controle dos endpoints da API para endereços
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation que indica que esta é uma classe Controle REST
@RestController
// Configura o endpoint principal deste controle
@RequestMapping("/api/v1/address")
public class AddressController {

	// serviço de endereços que será instanciado no construtor da classe
	private final AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@Operation(
	        summary = "Cria um endereço para uma pessoa",
	        description = "Recebe o Id da pessoa buscada e recebe as informações do endereço para criar que são :"
	        		+ "Se é principal, logradouro, CEP , número e cidade. ",	        
	        parameters = {
	        		@Parameter(name = "personId", description = "Id da pessoa que receberá o endereço"),
	        		@Parameter(name = "main", description = "Se este é o endereço principal"),
	        		@Parameter(name = "address", description = "Logradouro do endereço"),
	        		@Parameter(name = "postalCode", description = "CEP do endereço"),
	        		@Parameter(name = "number", description = "Número do endereço"),
	        		@Parameter(name = "city", description = "Cidade do endereço")
	        },
	        method = "POST",
	        tags = {
	                "Criar endereço para uma pessoa"
	                },
	        responses = {
	                @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso."),
	                @ApiResponse(responseCode = "400", description = "Erro ao realizar o Post."),
	                @ApiResponse(responseCode = "404", description = "Nenhuma pessoa encontrada com esse Id.")
	                }
	        )
	// Annotaion que determina um request de POST no endpoint address/ o id da pessoa a ser atribuido o endereço
	@PostMapping("/{personId}")
	// método que recebe o id da pessoa e o RequestBody de AddressDTO contendo as informações de endereço para a pessoa e
	//retorna a pessoa atribuida
	public ResponseEntity<PersonDTO> createAddrressToPerson(@PathVariable Long personId,@RequestBody AddressDTO addressDto) {
		PersonDTO result = addressService.createAddrressToPerson(personId, addressDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@Operation(
	        summary = "Lista todos os endereços de uma pessoa",
	        description = "Recebe o Id da pessoa buscada e se encontrada , seus endereços serão listados.",
	        parameters = {
	        		@Parameter(name = "personId", description = "Id da pessoa para listar endereços.")
	        },
	        method = "GET",
	        tags = {
	                "Listar endereços de uma pessoa"
	                },
	        responses = {
	                @ApiResponse(responseCode = "200", description = "Lista de endereços encontrada com sucesso."),
	                @ApiResponse(responseCode = "400", description = "Erro ao realizar o Get."),
	                @ApiResponse(responseCode = "404", description = "Nenhuma pessoa encontrada com esse Id."),
	                @ApiResponse(responseCode = "404", description = "Pessoa não possui endereço cadastrado.")
	                }
	        )
	// Annotaion que determina um request de GET no endpoint address/ o id da pessoa procurada 
	@GetMapping("/{personId}")
	// método que recebe o id da pessoa procurada e retorna sua lista de endereços
	public ResponseEntity<List<AddressDTO>> findAllAddressByPersonId(@PathVariable Long personId){
		List<AddressDTO> addressList = addressService.findAllAddressByPersonId(personId);
		return ResponseEntity.ok(addressList);
	}
	
	@Operation(
	        summary = "Buscar o endereço principal de uma pessoa",
	        description = "Recebe o Id da pessoa buscada e se encontrada retorna seu endereço principal.",
	        parameters = {
	        		@Parameter(name = "personId", description = "id da pessoa buscada")
	        },
	        method = "GET",
	        tags = {
	                "Buscar endereço principal"
	                },
	        responses = {
	                @ApiResponse(responseCode = "200", description = "Endereço principal encontrado com sucesso."),
	                @ApiResponse(responseCode = "400", description = "Erro ao realizar o Get."),
	                @ApiResponse(responseCode = "404", description = "Nenhuma pessoa encontrada com esse Id."),
	                @ApiResponse(responseCode = "404", description = "Pessoa não possui endereço cadastrado.")
	                }
	        )
	// Annotaion que determina um request de GET no endpoint main/ o id da pessoa procurada 
	@GetMapping("/main/{personId}")
	// método que recebe o id da pessoa procurada e retorna seu endereço principal
	public ResponseEntity<AddressDTO> findPrincipalAddressByPersonId(@PathVariable Long personId){
		AddressDTO main = addressService.findPrincipalAddressByPersonId(personId);
		return ResponseEntity.ok(main);
	}
}
