package com.github.luisfelipetochamartins.medical.clini.paciente;

import com.github.luisfelipetochamartins.medical.clini.endereco.EnderecoRecord;
import jakarta.validation.Valid;

public record PacienteUpdateRecord(
		Integer id,
		String nome,
		String telefone,
		@Valid EnderecoRecord enderecoRecord
) {
}
