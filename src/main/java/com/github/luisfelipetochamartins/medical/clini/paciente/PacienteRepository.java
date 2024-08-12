package com.github.luisfelipetochamartins.medical.clini.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

	@Query("""
			SELECT p.ativo
			  FROM Paciente p 
			 WHERE p.id = :id
			""")
	Boolean findAtivoById(Integer id);
}
