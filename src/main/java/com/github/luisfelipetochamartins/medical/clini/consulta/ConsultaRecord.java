package com.github.luisfelipetochamartins.medical.clini.consulta;

import com.github.luisfelipetochamartins.medical.clini.medicos.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRecord(Integer idMedico,
                             @NotNull Integer idPaciente,
                             @NotNull @Future LocalDateTime data,
                             Especialidade especialidade) {
}
