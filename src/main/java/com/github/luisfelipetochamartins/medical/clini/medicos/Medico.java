package com.github.luisfelipetochamartins.medical.clini.medicos;


import com.github.luisfelipetochamartins.medical.clini.endereco.Endereco;
import jakarta.persistence.*;

@Entity(name = "Medico")
@Table(name = "medicos")
public class Medico {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	private String nome;
	private String email;
	private String crm;
	private String telefone;
	private Boolean ativo = true;

	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;

	@Embedded
	private Endereco endereco;

	public Medico() {}

	public Medico(Integer id, String nome, String email, String telefone,
	              String crm, Especialidade especialidade, Endereco endereco) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.crm = crm;
		this.especialidade = especialidade;
		this.endereco = endereco;
	}

	public Medico(MedicoRecord record) {
		this.id = record.id();
		this.nome = record.nome();
		this.email = record.email();
		this.crm = record.crm();
		this.telefone = record.telefone();
		this.especialidade = record.especialidade();
		this.endereco = new Endereco(record.endereco());
	}

	public Medico(MedicoCadastroRecord record) {
		this.nome = record.nome();
		this.email = record.email();
		this.crm = record.crm();
		this.especialidade = record.especialidade();
		this.telefone = record.telefone();
		this.endereco = new Endereco(record.enderecoRecord());
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
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

	public void updateInfo(MedicoUpdateRecord record) {
		if (record.nome() != null) {
			this.nome = record.nome();
		}

		if (record.telefone() != null) {
			this.telefone = record.telefone();
		}

		if (record.endereco() != null) {
			this.endereco.updateInfo(record.endereco());
		}
	}

	public void inativar() {
		this.ativo = false;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("id = ").append(id);
		sb.append(", name = ").append(nome);
		sb.append(", email = ").append(email);
		sb.append(", crm = ").append(crm);
		sb.append(", especialidade = ");
		return sb.toString();
	}


}
