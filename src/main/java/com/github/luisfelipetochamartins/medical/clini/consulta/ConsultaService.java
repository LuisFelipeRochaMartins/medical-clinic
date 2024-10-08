package com.github.luisfelipetochamartins.medical.clini.consulta;

import com.github.luisfelipetochamartins.medical.clini.consulta.validacao.ValidadorAgendamentoConsulta;
import com.github.luisfelipetochamartins.medical.clini.infra.exception.ValidacaoException;
import com.github.luisfelipetochamartins.medical.clini.medicos.Medico;
import com.github.luisfelipetochamartins.medical.clini.medicos.MedicoRepository;
import com.github.luisfelipetochamartins.medical.clini.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConsultaService {

	private final ConsultaRepository repository;
	private final MedicoRepository medicoRepository;
	private final PacienteRepository pacienteRepository;
	private List<ValidadorAgendamentoConsulta> validadores;

	@Autowired
	public ConsultaService(ConsultaRepository repository, MedicoRepository medicoRepository,
	                       PacienteRepository pacienteRepository, List<ValidadorAgendamentoConsulta> validadores) {
		this.repository = repository;
		this.medicoRepository = medicoRepository;
		this.pacienteRepository = pacienteRepository;
		this.validadores = validadores;
	}

	public Page<DetalhamentoConsulta> getAllConsultas(Pageable page) {
		var pages = repository.findAll(page).map(DetalhamentoConsulta::new);
		return pages;
	}

	public DetalhamentoConsulta agendar(ConsultaRecord record) {
		var paciente = pacienteRepository.findById(record.idPaciente());
		var medico = medicoRepository.findById(record.idMedico());

		if (!paciente.isPresent()) {
			throw new ValidacaoException("Informações do paciente inválidas!");
		}

		if (!medico.isPresent()) {
			repository.save(new Consulta(null, medicoAleatorio(record), paciente.get(), record.data(), null));
		}

		validadores.forEach(v -> v.validar(record));

		var consulta = new Consulta(null, medico.get(), paciente.get(), record.data(), null);

		repository.save(consulta);

		return new DetalhamentoConsulta(consulta);
	}

	public void cancelar(ConsultaCancelamentoRecord record) {
		if (!repository.existsById(record.id())) {
			throw new ValidacaoException("id da consulta informado não existe");
		}

		var consulta = repository.getReferenceById(record.id());
		consulta.cancelar(record.cancelamento());
	}

	private Medico medicoAleatorio(ConsultaRecord record) {
		if (record.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatório quando não informado o médico");
		}

		return medicoRepository.medicoAleatorioLivreData(record.especialidade(), record.data());
	}
}
