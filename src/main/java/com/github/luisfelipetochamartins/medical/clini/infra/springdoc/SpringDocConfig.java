package com.github.luisfelipetochamartins.medical.clini.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components()
				.addSecuritySchemes("bearer-key",
				new SecurityScheme().type(SecurityScheme.Type.HTTP)
							.scheme("bearer").bearerFormat("JWT")))
				.info(new Info()
						.title("Aplicação para Sistema de Gestão Médica")
						.description("API Rest da aplicação Voll.med, contendo as funcionalidades de" +
								" CRUD de médicos e de pacientes, além de agendamento e cancelamento de consultas")
						.contact(new Contact()
								.name("Luís Felipe Rocha Martins")
								.email("luisfelipetochamartins@gmail.com")));
	}

}
