package com.github.luisfelipetochamartins.medical.clini.consulta.validacao;

import com.github.luisfelipetochamartins.medical.clini.consulta.ConsultaRecord;
import com.github.luisfelipetochamartins.medical.clini.consulta.ConsultaRepository;
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
	public void validarHorario(ConsultaRecord record) {
		boolean medicoOcupadoNoHorario = repository.existsByMedicoIdAndData(record.idMedico(), record.data());

		if (medicoOcupadoNoHorario) {
			throw new RuntimeException("Médico já possui outra consulta agendada no horário!");
		}
	}
}
