package com.github.luisfelipetochamartins.medical.clini.consulta;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/consultas")
@SecurityRequirement(name = "beared-key")
public class ConsultaController {

	private final ConsultaService service;

	@Autowired
	public ConsultaController(ConsultaService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Page<DetalhamentoConsulta>> getAllConsultas(@PageableDefault Pageable page) {
		var list = service.getAllConsultas(page);

		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<DetalhamentoConsulta> agendarConsulta(@RequestBody @Valid ConsultaRecord record) {
		var consulta = service.agendar(record);

		return ResponseEntity.ok(consulta);
	}

	@DeleteMapping
	@Transactional
	public ResponseEntity cancelar(@RequestBody @Valid ConsultaCancelamentoRecord record) {
		service.cancelar(record);

		return ResponseEntity.noContent().build();
	}
}
