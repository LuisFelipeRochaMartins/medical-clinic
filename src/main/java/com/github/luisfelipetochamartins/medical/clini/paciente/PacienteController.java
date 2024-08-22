package com.github.luisfelipetochamartins.medical.clini.paciente;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping(path = "/pacientes")
@SecurityRequirement(name = "beared-key")
public class PacienteController {

	private final PacienteRepository repository;

	@Autowired
	public PacienteController(PacienteRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<DetalhamentoPaciente> getPaciente(@PathVariable Integer id) {
		var paciente = repository.findById(id);

		return paciente.map(value -> ResponseEntity.ok(new DetalhamentoPaciente(value)))
				.orElseGet(() -> ResponseEntity.notFound().build());
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

		if (paciente.isPresent()) {
			paciente.get().updateInfo(record);
			return ResponseEntity.ok(new DetalhamentoPaciente(paciente.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		Optional<Paciente> paciente = repository.findById(id);

		if (paciente.isPresent()) {
			paciente.get().inativar();
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
