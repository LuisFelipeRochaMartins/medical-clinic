package com.github.luisfelipetochamartins.medical.clini.medicos;

import com.github.luisfelipetochamartins.medical.clini.endereco.EnderecoRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoRecord(
		Integer id,

		@NotBlank
		String nome,

		@NotBlank
		@Email
		String email,

		@NotBlank
		String telefone,

		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String crm,

		@NotNull
		Especialidade especialidade,

		@NotNull
		@Valid
		EnderecoRecord endereco
) {
	public MedicoRecord(Medico medico) {
		this(medico.getId(),
			 medico.getNome(),
			 medico.getEmail(),
			 medico.getTelefone(),
			 medico.getCrm(),
			 medico.getEspecialidade(),
			new EnderecoRecord(medico.getEndereco())
		);
	}
}
