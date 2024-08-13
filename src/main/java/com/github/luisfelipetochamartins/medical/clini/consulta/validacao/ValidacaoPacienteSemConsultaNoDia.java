package com.github.luisfelipetochamartins.medical.clini.consulta.validacao;

import com.github.luisfelipetochamartins.medical.clini.consulta.ConsultaRecord;
import com.github.luisfelipetochamartins.medical.clini.consulta.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidacaoPacienteSemConsultaNoDia implements ValidadorAgendamentoConsulta {

	private ConsultaRepository repository;

	@Autowired
	public ValidacaoPacienteSemConsultaNoDia(ConsultaRepository repository) {
		this.repository = repository;
	}

	@Override
	public void validar(ConsultaRecord record) {
		LocalDateTime primeiroHorario = record.data().withHour(7);
		LocalDateTime ultimoHorario = record.data().withHour(7);

		boolean pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(record.idPaciente(), primeiroHorario, ultimoHorario);
	}
}
