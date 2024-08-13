package com.github.luisfelipetochamartins.medical.clini.medicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

	Page<Medico> findAllByAtivoTrue(Pageable page);

	@Query("""
			SELECT m
			  FROM Medico m 
			 WHERE m.ativo = true
			   AND m.especialidade = :especialidade
			   AND m.id not in(
			       SELECT c.medico.id from Consulta c 
			        WHERE c.data = :data
			   )
			 ORDER BY random()
			 LIMIT 1
			""")
	Medico medicoAleatorioLivreData(Especialidade especialidade, LocalDateTime data);

	@Query("""
			SELECT m.ativo
			  FROM Medico m 
			 WHERE m.id = :id
			""")
	Boolean findAtivoById(Integer id);
}
