package br.com.billmetal9.attornatus.peoplemanagement.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.billmetal9.attornatus.peoplemanagement.dto.PersonDTO;
import io.restassured.RestAssured;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : Classe de testes para os endpoints da API para pessoas
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation que indica que esta é uma classe para testes com atributo webEnviroment configurado como RAMDOM_PORT
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

	// Annotation que indica que esta variável receberá o número da porta
	@LocalServerPort
	private int randomPort;
	
	// Annotation que indica que este método será executado antes dos testes
	@BeforeEach
	// Método que configura em qual porta será usada pela biblioteca de teste 
	public void setUpTest() {
		RestAssured.port = randomPort;
	}

	/**@Test determinam os métodos de test **/
	
	@Test
	// Método que testa se uma pessoa é criada e se retorna Status CREATED e informações corretas.
	void createPersonThenCheckIsCreated() {
		PersonDTO createPerson = PersonDTO.builder().name("Nome de Teste").birthDate("19/01/2023").build();
		RestAssured.given()
		.when()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(createPerson)
		.post("/api/v1/people")
		.then()
		.statusCode(HttpStatus.CREATED.value())
		.body("name",Matchers.equalTo("Nome de Teste"))
		.body("birthDate",Matchers.equalTo("19-01-2023"));
	}
	
	@Test
	// Método que testa se uma lista de pessoas é retornada e com Status OK.
	void getPersonListThenCheckOk() {
		List<PersonDTO> list = new ArrayList<>(); 
		RestAssured.given()
		.when()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.get("/api/v1/people")
		.then()
		.statusCode(HttpStatus.OK.value())
		.assertThat()
		.body("class", isA(list.getClass()));
	}
	
	@Test
	// Método que testa se uma pessoa é retornada pelo Id e com Status OK.
	void getPersonByIdThenCheckOk() {
		int id = 1;
		RestAssured.given()
		.pathParam("personId",id)
		.when()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.get("/api/v1/people/{personId}")
		.then()
		.statusCode(HttpStatus.OK.value())
		.body("id", is(id));
	}
	
	@Test
	// Método que testa se uma pessoa é encontrada e verifica se os dados foram alterados com Status OK.
	void editPersonThenCheckOK() {
		int id = 1;
		PersonDTO editPerson = PersonDTO.builder().name("Nome de Alteração Teste").birthDate("20/01/2023").build();
		RestAssured.given()
		.pathParam("personId",id)
		.when()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(editPerson)
		.put("/api/v1/people/{personId}")
		.then()
		.statusCode(HttpStatus.OK.value())
		.body("name",Matchers.equalTo("Nome de Alteração Teste"))
		.body("birthDate",Matchers.equalTo("20-01-2023"));
	}
	
	@Test
	// Método que testa Exception de pessoa não encontrada pelo Id e com Status NOT_FOUND.
	void exceptionNoPersonThenCheckNotFound() {
		int id = 5;
		RestAssured.given()
		.pathParam("personId",id)
		.when()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.get("/api/v1/people/{personId}")
		.then()
		.statusCode(HttpStatus.NOT_FOUND.value())
		.assertThat()
		.body("error",Matchers.equalTo("Not Found"));
	}
}
