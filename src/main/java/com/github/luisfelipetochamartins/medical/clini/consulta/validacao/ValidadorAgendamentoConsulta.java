package com.github.luisfelipetochamartins.medical.clini.consulta.validacao;

import com.github.luisfelipetochamartins.medical.clini.consulta.ConsultaRecord;

public interface ValidadorAgendamentoConsulta {

	void validarHorario(ConsultaRecord record);
}
