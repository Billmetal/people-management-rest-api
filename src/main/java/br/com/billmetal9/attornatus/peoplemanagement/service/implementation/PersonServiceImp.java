package br.com.billmetal9.attornatus.peoplemanagement.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.billmetal9.attornatus.peoplemanagement.dto.PersonDTO;
import br.com.billmetal9.attornatus.peoplemanagement.exception.PersonNotFoundException;
import br.com.billmetal9.attornatus.peoplemanagement.mapper.DataMapper;
import br.com.billmetal9.attornatus.peoplemanagement.model.Person;
import br.com.billmetal9.attornatus.peoplemanagement.repository.PersonRepository;
import br.com.billmetal9.attornatus.peoplemanagement.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : classe que implementa as funções de serviço de pessoas
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation que indica que esta é uma classe de Serviço
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServiceImp implements PersonService {
	
	// Referência ao repositório de pessoas
	private final PersonRepository personRepository;
	
	// Referência ao mapeamento de dados
	private final DataMapper dataMapper = DataMapper.INSTANCE;

	// método que cria uma pessoa
	@Override
	@Transactional
	public PersonDTO createPerson(PersonDTO personDto) {
		Person person = dataMapper.toPersonModel(personDto);
		Person personSaved = personRepository.save(person);
		return dataMapper.toPersonDTO(personSaved);
	}

	// método que edita e atualiza uma pessoa
	@Override
	@Transactional
	public PersonDTO editPerson(Long id, @Valid PersonDTO personDto) {
		Person person = verifyIfPersonExists(id);
		Person personToSave = dataMapper.toPersonModel(personDto);
		person.setName(personToSave.getName());
		person.setBirthDate(personToSave.getBirthDate());
		Person savedPerson = personRepository.save(person);
		return dataMapper.toPersonDTO(savedPerson);
	}
	
	// método que verifica se uma pesssoa existe pelo id e retorna a pessoa se existir
	private Person verifyIfPersonExists(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
	} 

	// método que procura uma pessoa pelo Id
	@Override
	@Transactional(readOnly = true)
	public PersonDTO findPersonById(Long id) {
		Person person = verifyIfPersonExists(id);
		return dataMapper.toPersonDTO(person);
	}

	// método que lista as pessoas cadastradas
	@Override
	@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
	public List<PersonDTO> findAllPersons() {
		List<Person> personList = personRepository.findAll();
		return personList.stream().map(dataMapper::toPersonDTO).collect(Collectors.toList());
	}

	
}
