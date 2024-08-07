package com.github.luisfelipetochamartins.medical.clini.medicos;

import com.github.luisfelipetochamartins.medical.clini.endereco.Endereco;

public record DetalhamentoMedico(Integer id,
                                 String nome,
                                 String email,
                                 String crm,
								 String telefone,
                                 Especialidade especialidade,
                                 Endereco endereco) {

	public DetalhamentoMedico(Medico medico) {
		this(medico.getId(),
				medico.getNome(),
				medico.getEmail(),
				medico.getCrm(),
				medico.getTelefone(),
				medico.getEspecialidade(),
				medico.getEndereco()
		);
	}

}
