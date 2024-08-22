package com.github.luisfelipetochamartins.medical.clini.medicos;

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
@RequestMapping("/medicos")
@SecurityRequirement(name = "beared-key")
public class MedicoController {

	private final MedicoRepository repository;

	@Autowired
	public MedicoController(MedicoRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<MedicoRecord> getMedico(@PathVariable Integer id) {
		var medico = repository.findById(id);

		return medico.map(value -> ResponseEntity.ok(new MedicoRecord(value))).
				orElseGet(() -> ResponseEntity.notFound().build()
		);
	}

	@GetMapping
	public ResponseEntity<Page<MedicoRecord>> getAllMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable page) {
		var list = repository.findAllByAtivoTrue(page).map(MedicoRecord::new);
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<DetalhamentoMedico> insert(@RequestBody @Valid MedicoRecord medico, UriComponentsBuilder uriBuilder) {
		var doctor = new Medico(medico);
		repository.save(doctor);

		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();

		return ResponseEntity.created(uri).body(new DetalhamentoMedico(doctor));
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DetalhamentoMedico> update(@RequestBody @Valid MedicoUpdateRecord record) {
		Optional<Medico> medico = repository.findById(record.id());

		if (medico.isPresent()) {
			medico.get().updateInfo(record);
			return ResponseEntity.ok(new DetalhamentoMedico(medico.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		Optional<Medico> medico = repository.findById(id);

		if (medico.isPresent()) {
			medico.get().inativar();
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
