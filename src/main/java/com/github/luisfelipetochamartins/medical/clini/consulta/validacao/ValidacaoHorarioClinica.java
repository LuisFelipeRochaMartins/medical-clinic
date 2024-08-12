package com.github.luisfelipetochamartins.medical.clini.consulta.validacao;

import com.github.luisfelipetochamartins.medical.clini.consulta.ConsultaRecord;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacaoHorarioClinica implements ValidadorAgendamentoConsulta {

	public void validarHorario(ConsultaRecord consultaRecord) {
		var data = consultaRecord.data();
		if (data.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			throw new RuntimeException("Consulta fora dos dias de funcionamento da clínica!");
		}

		if (data.getHour() < 7 || data.getHour() > 17) {
			throw new RuntimeException("Consulta está fora de horario de funcionamento da clínica!");
		}
	}
}
