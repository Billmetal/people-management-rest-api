package br.com.billmetal9.attornatus.peoplemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : classe de Exception para pessoa não encontrada
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation que indica a resposta de retorno Não encontrado
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {
	
	
	private static final long serialVersionUID = 4504049816722856101L;

	public PersonNotFoundException(Long id) {
		super("Nenhuma Pessoa encontrada com o id : " + id,null,false,false);
	}
}
