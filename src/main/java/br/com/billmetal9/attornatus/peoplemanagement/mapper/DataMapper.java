package br.com.billmetal9.attornatus.peoplemanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.billmetal9.attornatus.peoplemanagement.dto.AddressDTO;
import br.com.billmetal9.attornatus.peoplemanagement.dto.CreatePersonDTO;
import br.com.billmetal9.attornatus.peoplemanagement.dto.PersonDTO;
import br.com.billmetal9.attornatus.peoplemanagement.model.Address;
import br.com.billmetal9.attornatus.peoplemanagement.model.Person;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : interface de mapeamento de dados de transferência de pessoas e endereços
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation que indica qual o tipo de mapeamento será feito pelo Mapstruct
@Mapper(componentModel = "spring")
public interface DataMapper {
	
	DataMapper INSTANCE = Mappers.getMapper(DataMapper.class);
	
	// Annotation que mapeia e determina o formato de data
	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd/MM/yyyy")
	// método que recebe um PersonDTO e retorna um Person
	Person toPersonModel(CreatePersonDTO createPersonDto);
	
	// Annotation que mapeia e determina o formato de data
	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd/MM/yyyy")
	// método que recebe um PersonDTO e retorna um Person
	Person toPersonModel(PersonDTO personDto);
	
	// Annotation que mapeia e determina o formato de data
	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
	// método que recebe um Person e retorna um PersonDTO
	PersonDTO toPersonDTO(Person person);
	
	// método que recebe um AddressDTO e retorna um Address
	Address toAddressModel(AddressDTO addressDto);
	
	// método que recebe um Address e retorna um AddressDTO
	AddressDTO toAddressDTO(Address address);
}
