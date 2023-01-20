package br.com.billmetal9.attornatus.peoplemanagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.billmetal9.attornatus.peoplemanagement.dto.AddressDTO;
import br.com.billmetal9.attornatus.peoplemanagement.dto.PersonDTO;
import io.restassured.RestAssured;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : Classe de testes para os endpoints da API para endereços
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation que indica que esta é uma classe para testes com atributo webEnviroment configurado como RAMDOM_PORT
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressControllerTest {

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
	@Order(1)
	// Método que testa  se um endereço é criado para pesssoa correta e se retorna Status CREATED.
	void createAddressThenCheckIsCreated() {
		PersonDTO createPerson = PersonDTO.builder().name("Nome de Teste").birthDate("20/01/2023").build();
		RestAssured.given()
		.when()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(createPerson)
		.post("/api/v1/people")
		.then()
		.statusCode(HttpStatus.CREATED.value());
		int id = 1;
		AddressDTO createAdress = AddressDTO.builder()
				.main(false)
				.address("Avenida Paulista")
				.postalCode("01310-200")
				.number(3400)
				.city("São Paulo").build();
		PersonDTO person = RestAssured.given()
				.pathParam("personId",id)
				.when()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(createAdress)
				.post("/api/v1/address/{personId}")
				.then()
				.statusCode(HttpStatus.CREATED.value())
				.extract().as(PersonDTO.class);
				
				assertThat(person.getId()).isEqualTo(id);
				assertThat(person.getAddressList()).isNotEmpty();
	}
	
	@Test
	@Order(2)
	// Método que testa se uma lista de endereços é retornada e com Status OK.
	void getAddressListThenCheckOk() {
		List<AddressDTO> list = new ArrayList<>(); 
		int id = 1;
		RestAssured.given()
		.pathParam("personId",id)
		.when()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.get("/api/v1/address/{personId}")
		.then()
		.statusCode(HttpStatus.OK.value())
		.assertThat()
		.body("class", isA(list.getClass()));
	}
	
	@Test
	@Order(3)
	// Método que testa se é retornado o endereço principal e com Status OK.
	void getMainAddressThenCheckOk() { 
		int id = 1;
		RestAssured.given()
		.pathParam("personId",id)
		.when()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.get("/api/v1/address/main/{personId}")
		.then()
		.statusCode(HttpStatus.OK.value())
		.assertThat()
		.body("main", is(true));
	}
}
