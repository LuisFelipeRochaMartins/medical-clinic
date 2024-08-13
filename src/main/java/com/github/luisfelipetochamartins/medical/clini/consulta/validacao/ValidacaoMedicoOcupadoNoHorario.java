package com.github.luisfelipetochamartins.medical.clini.consulta.validacao;

import com.github.luisfelipetochamartins.medical.clini.consulta.ConsultaRecord;
import com.github.luisfelipetochamartins.medical.clini.consulta.ConsultaRepository;
import com.github.luisfelipetochamartins.medical.clini.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoOcupadoNoHorario implements ValidadorAgendamentoConsulta {

	private ConsultaRepository repository;

	@Autowired
	public ValidacaoMedicoOcupadoNoHorario(ConsultaRepository repository) {
		this.repository = repository;
	}

	@Override
	public void validar(ConsultaRecord record) {
		boolean medicoOcupadoNoHorario = repository.existsByMedicoIdAndData(record.idMedico(), record.data());

		if (medicoOcupadoNoHorario) {
			throw new ValidacaoException("Médico já possui outra consulta agendada no horário!");
		}
	}
}
