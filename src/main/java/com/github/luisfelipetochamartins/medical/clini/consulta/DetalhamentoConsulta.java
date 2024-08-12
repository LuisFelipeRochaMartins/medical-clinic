package com.github.luisfelipetochamartins.medical.clini.consulta;

import java.time.LocalDateTime;

public record DetalhamentoConsulta(Integer id,
                                   Integer idMedico,
                                   Integer idPaciente,
                                   LocalDateTime data) {

	public DetalhamentoConsulta(Consulta consulta) {
		this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
	}
}
