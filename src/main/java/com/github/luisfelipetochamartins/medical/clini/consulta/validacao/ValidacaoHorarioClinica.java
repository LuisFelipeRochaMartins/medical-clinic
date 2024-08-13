package com.github.luisfelipetochamartins.medical.clini.consulta.validacao;

import com.github.luisfelipetochamartins.medical.clini.consulta.ConsultaRecord;
import com.github.luisfelipetochamartins.medical.clini.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacaoHorarioClinica implements ValidadorAgendamentoConsulta {

	public void validar(ConsultaRecord consultaRecord) {
		var data = consultaRecord.data();
		if (data.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			throw new ValidacaoException("Consulta fora dos dias de funcionamento da clínica!");
		}

		if (data.getHour() < 7 || data.getHour() > 17) {
			throw new ValidacaoException("Consulta está fora de horario de funcionamento da clínica!");
		}
	}
}
