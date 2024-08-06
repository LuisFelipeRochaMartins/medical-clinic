package com.github.luisfelipetochamartins.medical.clini.medicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

	Page<Medico> findAllByAtivoTrue(Pageable page);
}
