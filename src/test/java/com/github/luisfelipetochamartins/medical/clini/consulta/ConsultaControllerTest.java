package com.github.luisfelipetochamartins.medical.clini.consulta;

import com.github.luisfelipetochamartins.medical.clini.medicos.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

	private final MockMvc mvc;
	private final JacksonTester<ConsultaRecord> tester;
	private final JacksonTester<DetalhamentoConsulta> testerDetalhamento;

	@MockBean
	private final ConsultaService service;

	@Autowired
	public ConsultaControllerTest(MockMvc mvc, JacksonTester<ConsultaRecord> tester,
	                              JacksonTester<DetalhamentoConsulta> testerDetalhamento, ConsultaService service) {
		this.mvc = mvc;
		this.tester = tester;
		this.testerDetalhamento = testerDetalhamento;
		this.service = service;
	}

	@Test
	@DisplayName("Deveria devolver código HTTP 400 quando informações estão faltantes ou inválidas")
	@WithMockUser
	void agendarConsultaCenario1() throws Exception {
		var response = mvc.perform(post("/consultas"))
							.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	@DisplayName("Deveria devolver código HTTP 200 quando informações estão válidas")
	@WithMockUser
	void agendarConsultaCenario2() throws Exception {
		var data = LocalDateTime.now().plusHours(1);

		var retorno = new DetalhamentoConsulta(null, 7, 1, data);
		when(service.agendar(any())).thenReturn(retorno);

		var response = mvc
				.perform(
						post("/consultas")
								.contentType(MediaType.APPLICATION_JSON)
								.content(tester.write(
											new ConsultaRecord(7, 1, data, Especialidade.CARDIOLOGIA)
										).getJson()
								)
				)
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		var responseJson = testerDetalhamento.write(
				retorno
		).getJson();

		assertThat(response.getContentAsString()).isEqualTo(responseJson);
	}
}