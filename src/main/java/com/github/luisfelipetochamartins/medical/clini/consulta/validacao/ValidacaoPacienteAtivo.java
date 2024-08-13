package com.github.luisfelipetochamartins.medical.clini.consulta.validacao;

import com.github.luisfelipetochamartins.medical.clini.consulta.ConsultaRecord;
import com.github.luisfelipetochamartins.medical.clini.infra.exception.ValidacaoException;
import com.github.luisfelipetochamartins.medical.clini.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteAtivo implements ValidadorAgendamentoConsulta {

	private PacienteRepository repository;

	@Autowired
	public ValidacaoPacienteAtivo(PacienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public void validar(ConsultaRecord record) {
		boolean pacienteAtivo = repository.findAtivoById(record.idPaciente());

		if (!pacienteAtivo) {
			throw new ValidacaoException("Consulta não pode ser agendada para um paciente inativo!");
		}
	}
}
