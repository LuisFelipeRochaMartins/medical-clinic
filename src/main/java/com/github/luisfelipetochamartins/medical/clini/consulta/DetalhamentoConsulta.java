package com.github.luisfelipetochamartins.medical.clini.consulta;

import com.github.luisfelipetochamartins.medical.clini.medicos.DetalhamentoMedico;
import com.github.luisfelipetochamartins.medical.clini.paciente.DetalhamentoPaciente;

import java.time.LocalDateTime;

public record DetalhamentoConsulta(Integer id,
                                   DetalhamentoMedico medico,
                                   DetalhamentoPaciente paciente,
                                   LocalDateTime data) {

	public DetalhamentoConsulta(Consulta consulta) {
		this(consulta.getId(),
			    new DetalhamentoMedico(consulta.getMedico()),
			    new DetalhamentoPaciente(consulta.getPaciente()),
				consulta.getData()
		);
	}
}
