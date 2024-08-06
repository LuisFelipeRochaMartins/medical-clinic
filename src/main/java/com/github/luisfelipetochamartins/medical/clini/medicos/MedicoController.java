package com.github.luisfelipetochamartins.medical.clini.medicos;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	private final MedicoRepository repository;

	@Autowired
	public MedicoController(MedicoRepository repository) {
		this.repository = repository;
	}
	@GetMapping
	public Page<MedicoRecord> getAllMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable page) {
		return repository.findAllByAtivoTrue(page).map(MedicoRecord::new);
	}

	@PostMapping
	public void insert(@RequestBody @Valid MedicoRecord medico) {
		repository.save(new Medico(medico));
	}

	@PutMapping
	@Transactional
	public void update(@RequestBody @Valid MedicoUpdateRecord record) {
		Optional<Medico> medico = repository.findById(record.id());

		medico.ifPresent(m -> m.updateInfo(record));
	}

	@DeleteMapping(path = "{id}")
	@Transactional
	public void delete(@PathVariable Integer id) {
		Optional<Medico> medico = repository.findById(id);

		if (medico.isPresent()) {
			medico.get().inativar();
		}
	}
}
