package com.github.luisfelipetochamartins.medical.clini.medicos;

import com.github.luisfelipetochamartins.medical.clini.consulta.Consulta;
import com.github.luisfelipetochamartins.medical.clini.endereco.EnderecoRecord;
import com.github.luisfelipetochamartins.medical.clini.paciente.Paciente;
import com.github.luisfelipetochamartins.medical.clini.paciente.PacienteRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

	private final MedicoRepository repository;
	private final TestEntityManager entityManager;

	@Autowired
	public MedicoRepositoryTest(MedicoRepository repository, TestEntityManager entityManager) {
		this.repository = repository;
		this.entityManager = entityManager;
	}

	@Test
	@DisplayName("Deveria devolver null quando único médico cadastrado não está disponível na data")
	void medicoAleatorioLivreDataCase1() {
		var proximaSegunda = LocalDate.now()
				.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

		var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
		var paciente = cadastrarPaciente("paciente", "paciente@voll.med", "12345600000");
		cadastrarConsulta(medico, paciente, proximaSegunda);

		var medicoLivre = repository.medicoAleatorioLivreData(Especialidade.CARDIOLOGIA, proximaSegunda);
		assertThat(medicoLivre).isNull();
	}

	@Test
	@DisplayName("Deveria devolver um médico quando ele estiver disponível na data")
	void medicoAleatorioLivreDataCase2() {
		var proximaSegunda = LocalDate.now()
				.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

		var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

		var medicoLivre = repository.medicoAleatorioLivreData(Especialidade.CARDIOLOGIA, proximaSegunda);
		assertThat(medicoLivre).isEqualTo(medico);
	}

	private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
		entityManager.persist(new Consulta(null, medico, paciente, data, null));
	}

	private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
		var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
		entityManager.persist(medico);
		return medico;
	}

	private Paciente cadastrarPaciente(String nome, String email, String cpf) {
		var paciente = new Paciente(dadosPaciente(nome, email, cpf));
		entityManager.persist(paciente);
		return paciente;
	}

	private MedicoCadastroRecord dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
		return new MedicoCadastroRecord(
				nome,
				email,
				crm,
				"61999999999",
				especialidade,
				dadosEndereco()
		);
	}

	private PacienteRecord dadosPaciente(String nome, String email, String cpf) {
		return new PacienteRecord(
				nome,
				email,
				"61999999999",
				cpf,
				dadosEndereco()
		);
	}

	private EnderecoRecord dadosEndereco() {
		return new EnderecoRecord(
				"rua xpto",
				"bairro",
				"00000000",
				"Brasilia",
				"DF",
				null,
				null
		);
	}
}