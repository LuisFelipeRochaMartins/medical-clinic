package com.github.luisfelipetochamartins.medical.clini.consulta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

	Boolean existsByMedicoIdAndData(Integer medicoId, LocalDateTime data);


	boolean existsByPacienteIdAndDataBetween(Integer integer, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

	Page<Consulta> findAll(Pageable page);
}
