package com.github.luisfelipetochamartins.medical.clini.consulta.validacao;

import com.github.luisfelipetochamartins.medical.clini.consulta.ConsultaRecord;
import com.github.luisfelipetochamartins.medical.clini.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacaoHorarioComAntecedencia implements ValidadorAgendamentoConsulta {

	public void validar(ConsultaRecord consultaRecord) {
		var data = consultaRecord.data();
		var now = LocalDateTime.now();

		if (Duration.between(now, data).toMinutes() < 30) {
			throw new ValidacaoException("Consulta deve ser agendada com no mínimo de 30 minutos de antecedências");
		}
	}
}
