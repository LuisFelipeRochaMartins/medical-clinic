package com.github.luisfelipetochamartins.medical.clini.medicos;

import com.github.luisfelipetochamartins.medical.clini.endereco.EnderecoRecord;

public record MedicoCadastroRecord(String nome,
                                   String email,
                                   String crm,
								   String telefone,
                                   Especialidade especialidade,
                                   EnderecoRecord enderecoRecord) {
}
