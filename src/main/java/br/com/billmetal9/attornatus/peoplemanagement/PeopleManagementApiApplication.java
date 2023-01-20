package br.com.billmetal9.attornatus.peoplemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : classe principal da aplicação
 * 
 * Autor : Willian T. K.
 * 
 * **/

//Annotation indicando que a classe deve ser usada para iniciar o Spring Boot
@SpringBootApplication
public class PeopleManagementApiApplication {

	// Método principal da aplicação
	public static void main(String[] args) {
		SpringApplication.run(PeopleManagementApiApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/v1/").allowedOrigins("*");
			}
		};
	}

}
