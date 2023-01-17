package br.com.billmetal9.attornatus.peoplemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.billmetal9.attornatus.peoplemanagement.model.Address;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : interface para repositório de endereços do banco de dados
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation para interface de repositório
@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

}
