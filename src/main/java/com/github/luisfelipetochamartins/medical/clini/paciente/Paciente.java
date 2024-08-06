package com.github.luisfelipetochamartins.medical.clini.paciente;

import com.github.luisfelipetochamartins.medical.clini.endereco.Endereco;
import jakarta.persistence.*;

@Entity
@Table(name = "pacientes")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	private Boolean ativo = true;

	@Embedded
	private Endereco endereco;

	public Paciente() {}

	public Paciente(Integer id, String nome, String email, String cpf, String telefone, Endereco endereco) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Paciente(PacienteRecord record) {
		this.nome = record.nome();
		this.email = record.email();
		this.telefone = record.telefone();
		this.cpf = record.cpf();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void updateInfo(PacienteUpdateRecord record) {
		if (record.nome() != null) {
			this.nome = record.nome();
		}

		if (record.telefone() != null) {
			this.telefone = record.telefone();
		}

		if (record.enderecoRecord() != null) {
			this.endereco.updateInfo(record.enderecoRecord());
		}
	}

	public void inativar() {
		this.ativo = false;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("id =").append(id);
		sb.append(", nome = ").append(nome);
		sb.append(", email =").append(email);
		sb.append(", cpf =").append(cpf);
		sb.append(", telefone =").append(telefone);
		sb.append(", endereco =").append(endereco);
		return sb.toString();
	}
}
