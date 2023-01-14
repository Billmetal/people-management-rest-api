package br.com.billmetal9.attornatus.peoplemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Nome da Aplicação : Gerenciamento de Pessoas
 * 
 * Descrição da classe : classe de configuração do Swagger
 * 
 * Autor : Willian T. K.
 * 
 * **/

// Annotation para classe de configuração
@Configuration
// Annotation de ativação do Swagger
@EnableSwagger2
public class SwaggerConfig {
	
	// atributos para classe de contato Contact
	private final String MY_NAME = "Willian T. K.";
	private final String MY_URL = "https://github.com/Billmetal";
	private final String MY_EMAIL = "billmetal9@gmail.com";
	
	// Annotation que indica que o método deve ser registrado como um bean no contexto do Spring
	@Bean
	// Método que retorna um componente de configuração do Swagger
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.billmetal9.attornatus.peoplemanagement"))
				.build()
				.apiInfo(metaData());
	}

	// Método que retorna informações sobre a Api para o Swagger
	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Gerenciamento de Pessoas")
				.description("REST API para o gerenciamento de pessoas")
				.version("1.0.0")
				.contact(new Contact(MY_NAME, MY_URL, MY_EMAIL))
				.license("Apache License")
				.licenseUrl("https://www.apache.org/licenses/")
				.build();
	}
}
