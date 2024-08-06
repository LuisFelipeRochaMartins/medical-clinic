package com.github.luisfelipetochamartins.medical.clini.paciente;

import com.github.luisfelipetochamartins.medical.clini.endereco.EnderecoRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PacienteRecord(
		@NotBlank
		String nome,

		@NotBlank
		@Email
		String email,

		@NotBlank
		String telefone,

		@NotBlank
		@Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
		String cpf,

		@NotNull
		@Valid
		EnderecoRecord endereco
) {
	public PacienteRecord(Paciente paciente) {
		this(paciente.getNome(),
		     paciente.getEmail(),
				paciente.getTelefone(),
				paciente.getCpf(),
				new EnderecoRecord(paciente.getEndereco())
		);
	}
}
