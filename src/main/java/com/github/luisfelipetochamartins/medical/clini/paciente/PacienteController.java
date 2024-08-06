package com.github.luisfelipetochamartins.medical.clini.paciente;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(name = "/pacientes")
public class PacienteController {

	private final PacienteRepository repository;

	@Autowired
	public PacienteController(PacienteRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public Page<PacienteRecord> getAllPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable page) {
		return repository.findAll(page).map(PacienteRecord::new);
	}

	@PostMapping
	public void insert(@RequestBody @Valid PacienteRecord record) {
		repository.save(new Paciente(record));
	}

	@PutMapping
	@Transactional
	public void update(@RequestBody @Valid PacienteUpdateRecord record) {
		Optional<Paciente> paciente = repository.findById(record.id());

		paciente.ifPresent(value -> value.updateInfo(record));
	}

	@DeleteMapping
	@Transactional
	public void update(@PathVariable Integer id) {
		Optional<Paciente> paciente = repository.findById(id);

		paciente.ifPresent(Paciente::inativar);
	}
}
