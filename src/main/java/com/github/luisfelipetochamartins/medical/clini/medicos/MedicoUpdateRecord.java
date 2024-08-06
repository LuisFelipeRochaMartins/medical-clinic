package com.github.luisfelipetochamartins.medical.clini.medicos;

import com.github.luisfelipetochamartins.medical.clini.endereco.EnderecoRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record MedicoUpdateRecord (
		@NotNull Integer id,
		String nome,
		String telefone,
		@Valid EnderecoRecord endereco){
}
