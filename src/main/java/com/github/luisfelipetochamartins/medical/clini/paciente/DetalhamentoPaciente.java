package com.github.luisfelipetochamartins.medical.clini.paciente;

import com.github.luisfelipetochamartins.medical.clini.endereco.Endereco;
import com.github.luisfelipetochamartins.medical.clini.medicos.Especialidade;

public record DetalhamentoPaciente(Integer id,
                                   String nome,
                                   String email,
                                   String telefone,
                                   Endereco endereco) {

	public DetalhamentoPaciente(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco());
	}
}
