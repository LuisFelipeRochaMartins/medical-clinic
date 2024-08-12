package com.github.luisfelipetochamartins.medical.clini.consulta.validacao;

import com.github.luisfelipetochamartins.medical.clini.consulta.ConsultaRecord;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacaoHorarioComAntecedencia implements ValidadorAgendamentoConsulta {

	public void validarHorario(ConsultaRecord consultaRecord) {
		var data = consultaRecord.data();
		var now = LocalDateTime.now();

		if (Duration.between(now, data).toMinutes() < 30) {
			throw new RuntimeException("Consulta deve ser agendada com no mínimo de 30 minutos de antecedências");
		}
	}
}
