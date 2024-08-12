package com.github.luisfelipetochamartins.medical.clini.consulta;

import jakarta.validation.constraints.NotNull;

public record ConsultaCancelamentoRecord(@NotNull Integer id,
                                         @NotNull MotivoCancelamento cancelamento) {
}
