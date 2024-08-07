package com.github.luisfelipetochamartins.medical.clini.paciente;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping(name = "/pacientes")
public class PacienteController {

	private final PacienteRepository repository;

	@Autowired
	public PacienteController(PacienteRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<DetalhamentoPaciente> getPaciente(@PathVariable Integer id) {
		var paciente = repository.getReferenceById(id);

		return ResponseEntity.ok(new DetalhamentoPaciente(paciente));
	}

	@GetMapping
	public ResponseEntity<Page<PacienteRecord>> getAllPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable page) {
		var pacientes = repository.findAll(page).map(PacienteRecord::new);

		return ResponseEntity.ok(pacientes);
	}

	@PostMapping
	public ResponseEntity<DetalhamentoPaciente> insert(@RequestBody @Valid PacienteRecord record, UriComponentsBuilder uri) {
		var paciente = new Paciente(record);
		repository.save(paciente);

		var url = uri.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

		return ResponseEntity.created(url).body(new DetalhamentoPaciente(paciente));
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DetalhamentoPaciente> update(@RequestBody @Valid PacienteUpdateRecord record) {
		Optional<Paciente> paciente = repository.findById(record.id());

		paciente.ifPresent(value -> value.updateInfo(record));

		return ResponseEntity.ok(new DetalhamentoPaciente(paciente.get()));
	}

	@DeleteMapping
	@Transactional
	public ResponseEntity delete(@PathVariable Integer id) {
		Optional<Paciente> paciente = repository.findById(id);

		paciente.ifPresent(Paciente::inativar);

		return ResponseEntity.noContent().build();
	}
}
