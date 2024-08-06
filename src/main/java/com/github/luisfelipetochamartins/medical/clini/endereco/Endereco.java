package com.github.luisfelipetochamartins.medical.clini.endereco;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

	private String logradouro;
	private String bairro;
	private String cep;
	private String numero;
	private String complemento;
	private String cidade;
	private String uf;

	public Endereco() {}

	public Endereco(String logradouro, String bairro, String cep, String numero, String complemento, String cidade, String uf) {
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.cidade = cidade;
		this.uf = uf;
	}

	public Endereco(EnderecoRecord record) {
		this.logradouro = record.logradouro();
		this.bairro = record.bairro();
		this.cep = record.cep();
		this.numero = record.numero();
		this.complemento = record.complemento();
		this.cidade = record.cidade();
		this.uf = record.uf();
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("logradouro =").append(logradouro);
		sb.append(", bairro =").append(bairro);
		sb.append(", cep =").append(cep);
		sb.append(", numero =").append(numero);
		sb.append(", complemento =").append(complemento);
		sb.append(", cidade =").append(cidade);
		sb.append(", uf =").append(uf);
		return sb.toString();
	}

	public void updateInfo(EnderecoRecord record) {
		if (record.logradouro() != null) {
			this.logradouro = record.logradouro();
		}

		if (record.bairro() != null) {
			this.bairro = record.bairro();
		}

		if (record.cep() != null) {
			this.cep = record.cep();
		}
		if (record.uf() != null) {
			this.uf = record.uf();
		}

		if (record.cidade() != null) {
			this.cidade = record.cidade();
		}

		if (record.numero() != null) {
			this.numero = record.numero();
		}

		if (record.complemento() != null) {
			this.complemento = record.complemento();
		}
	}
}
