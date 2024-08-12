package com.github.luisfelipetochamartins.medical.clini.consulta;

import com.github.luisfelipetochamartins.medical.clini.medicos.Medico;
import com.github.luisfelipetochamartins.medical.clini.paciente.Paciente;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "consultas")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	private LocalDateTime data;

	@Column(name = "motivo_cancelamento")
	@Enumerated(EnumType.STRING)
	private MotivoCancelamento cancelamento;

	public Consulta() {}

	public Consulta(Integer id, Medico medico, Paciente paciente, LocalDateTime horario, MotivoCancelamento cancelamento) {
		this.id = id;
		this.medico = medico;
		this.paciente = paciente;
		this.data = horario;
		this.cancelamento = cancelamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime horario) {
		this.data = horario;
	}

	public void cancelar(MotivoCancelamento cancelamento) {
		this.cancelamento = cancelamento;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("id = ").append(id);
		sb.append(", medico = ").append(medico);
		sb.append(", paciente =").append(paciente);
		sb.append(", horario = ").append(data);
		return sb.toString();
	}

}

