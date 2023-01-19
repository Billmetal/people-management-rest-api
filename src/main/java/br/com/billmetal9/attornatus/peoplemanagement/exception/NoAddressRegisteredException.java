package br.com.billmetal9.attornatus.peoplemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : classe de Exception para nenhum endereço registrado para pessoa
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation que indica a resposta de retorno Não encontrado
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoAddressRegisteredException extends RuntimeException {

	private static final long serialVersionUID = 2881141596907705468L;
	
	public NoAddressRegisteredException(Long personId) {
		super("Nenhum endereço registrado para a pessoa de id : " + personId,null,false,false);
	}
}
