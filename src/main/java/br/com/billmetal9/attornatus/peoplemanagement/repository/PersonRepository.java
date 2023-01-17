package br.com.billmetal9.attornatus.peoplemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.billmetal9.attornatus.peoplemanagement.model.Person;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : interface para repositório de pessoas do banco de dados
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation para interface de repositório
@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

}
