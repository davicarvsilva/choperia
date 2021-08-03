package com.davicarv.choperia.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Cliente extends Pessoa {
	@Column(nullable = true, length = 100)
	@Size(max = 100, message = "documentação não pode ter mais de 100 caracteres")
	@NotBlank
	private String documentacao;
	
	@Column(nullable = false, unique = true)
	@Email
	@NotBlank
	@Size(min = 2, max = 50, message = "Email deve ter entre 2 e 50 caracteres")
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<OrdemServico> ordensServico = new ArrayList<>();

	public String getDocumentacao() {
		return documentacao;
	}

	public void setDocumentacao(String documentacao) {
		this.documentacao = documentacao;
	}

	public List<OrdemServico> getOrdensServico() {
		return ordensServico;
	}

	public void setOrdensServico(List<OrdemServico> ordensServico) {
		this.ordensServico = ordensServico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
