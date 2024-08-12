package com.github.luisfelipetochamartins.medical.clini.consulta.validacao;

import com.github.luisfelipetochamartins.medical.clini.consulta.ConsultaRecord;
import com.github.luisfelipetochamartins.medical.clini.medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoAtivo implements ValidadorAgendamentoConsulta {

	private final MedicoRepository repository;

	@Autowired
	public ValidacaoMedicoAtivo(MedicoRepository repository) {
		this.repository = repository;
	}

	@Override
	public void validarHorario(ConsultaRecord record) {
		if (record.idMedico() == null) {
			return;
		}

		var medicoAtivo = repository.findAtivoById(record.idMedico());
		if (!medicoAtivo) {
			throw new RuntimeException("Consulta não pode ser agendada com médico inativo!");
		}
	}
}
