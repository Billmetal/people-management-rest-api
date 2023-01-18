package br.com.billmetal9.attornatus.peoplemanagement.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.billmetal9.attornatus.peoplemanagement.dto.AddressDTO;
import br.com.billmetal9.attornatus.peoplemanagement.dto.PersonDTO;
import br.com.billmetal9.attornatus.peoplemanagement.exception.NoAddressRegisteredException;
import br.com.billmetal9.attornatus.peoplemanagement.exception.PersonNotFoundException;
import br.com.billmetal9.attornatus.peoplemanagement.mapper.DataMapper;
import br.com.billmetal9.attornatus.peoplemanagement.model.Address;
import br.com.billmetal9.attornatus.peoplemanagement.model.Person;
import br.com.billmetal9.attornatus.peoplemanagement.repository.PersonRepository;
import br.com.billmetal9.attornatus.peoplemanagement.service.AddressService;
import lombok.AllArgsConstructor;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : classe que implementa as funções de serviço de endereços
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation que indica que esta é uma classe de Serviço
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressServiceImp implements AddressService{
	
	// Referência ao repositório de endereços
	//private final AddressRepository addressRepository;
	
	// Referência ao repositório de pessoas
	private final PersonRepository personRepository;
		
	// Referência ao mapeamento de dados
	private final DataMapper dataMapper = DataMapper.INSTANCE;
	
	// Cria um endereço para uma pessoa pelo Id da pessoa
	@Override
	@Transactional
	public PersonDTO createAddrressToPerson(Long personId, AddressDTO addressDto) {
		Person person = verifyIfPersonExists(personId);
		Address address = dataMapper.toAddressModel(addressDto);
		if(person.getAddressList().size() == 0) {
			address.setMain(true);
		}
		if(address.isMain() && person.getAddressList().size() > 0) {			
			person.getAddressList().stream().forEach(obj -> obj.setMain(false));							
		}
		person.getAddressList().add(address);
		Person personSaved = personRepository.save(person);
		return dataMapper.toPersonDTO(personSaved);
	}

	// Lista todos os endereços de uma pessoa pelo Id da pessoa
	@Override
	@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
	public List<AddressDTO> findAllAddressByPersonId(Long personId) {
		Person person = verifyIfPersonExists(personId);
		if(person.getAddressList().isEmpty()) {
			throw new NoAddressRegisteredException(personId);
		}
		PersonDTO personDto = dataMapper.toPersonDTO(person);
		return personDto.getAddressList();
	}

	// informa qual é o endereço principal de uma pessoa pelo Id da pessoa
	@Override
	@Transactional(readOnly = true)
	public AddressDTO findPrincipalAddressByPersonId(Long personId) {
		Person person = verifyIfPersonExists(personId);
		if(person.getAddressList().isEmpty()) {
			throw new NoAddressRegisteredException(personId);
		}
		PersonDTO personDto = dataMapper.toPersonDTO(person);
		return personDto.getAddressList().stream().filter(address -> address.isMain()).findFirst().get();
	}
	
	// método que verifica se uma pesssoa existe pelo id e retorna a pessoa se existir
	private Person verifyIfPersonExists(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
	}

}
