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
	
	// Annotaion que determina um request de POST no endpoint address/ o id da pessoa a ser atribuido o endereço
	@PostMapping("/{personId}")
	// método que recebe o id da pessoa e o RequestBody de AddressDTO contendo as informações de endereço para a pessoa e
	//retorna a pessoa atribuida
	public ResponseEntity<PersonDTO> createAddrressToPerson(@PathVariable Long personId,@RequestBody AddressDTO addressDto) {
		PersonDTO result = addressService.createAddrressToPerson(personId, addressDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	// Annotaion que determina um request de GET no endpoint address/ o id da pessoa procurada 
	@GetMapping("/{personId}")
	// método que recebe o id da pessoa procurada e retorna sua lista de endereços
	public ResponseEntity<List<AddressDTO>> findAllAddressByPersonId(@PathVariable Long personId){
		List<AddressDTO> addressList = addressService.findAllAddressByPersonId(personId);
		return ResponseEntity.ok(addressList);
	}
	
	// Annotaion que determina um request de GET no endpoint main/ o id da pessoa procurada 
	@GetMapping("/main/{personId}")
	// método que recebe o id da pessoa procurada e retorna seu endereço principal
	public ResponseEntity<AddressDTO> findPrincipalAddressByPersonId(@PathVariable Long personId){
		AddressDTO main = addressService.findPrincipalAddressByPersonId(personId);
		return ResponseEntity.ok(main);
	}
}
