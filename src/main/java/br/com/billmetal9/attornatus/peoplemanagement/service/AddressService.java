package br.com.billmetal9.attornatus.peoplemanagement.service;

import java.util.List;

import br.com.billmetal9.attornatus.peoplemanagement.dto.AddressDTO;
import br.com.billmetal9.attornatus.peoplemanagement.dto.PersonDTO;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : interface que determina as funções da Api em relação aos endereços
 * 
 * Autor : Willian T. K.
 * 
 * **/

public interface AddressService {

	// Cria um endereço para uma pessoa pelo Id da pessoa
	PersonDTO createAddrressToPerson(Long personId,AddressDTO addressDto);
	
	// Lista todos os endereços de uma pessoa pelo Id da pessoa
	List<AddressDTO> findAllAddressByPersonId(Long personId);
	
	// informa qual é o endereço principal de uma pessoa pelo Id da pessoa
	AddressDTO findPrincipalAddressByPersonId(Long personId);
}
