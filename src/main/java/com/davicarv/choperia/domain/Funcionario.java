package com.davicarv.choperia.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Funcionario extends Pessoa {

	@Column(nullable = false, length = 100)
	@Size(min = 2, max = 100, message = "cargo deve ter entre 2 e 100 caracteres")
	@NotBlank
	private String cargo;

	@Column(nullable = false)
	@Positive
	@Max(value = 100000, message = "salário não pode ser maior que R$ 100000")
	private double salario;
	
	@Column(nullable = false, unique = true)
	@Email
	@NotBlank
	@Size(min = 2, max = 50, message = "Email deve ter entre 2 e 50 caracteres")
	private String email;

	@JsonIgnore
	@ManyToMany
	private List<OrdemServico> ordensServico = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@Size(min = 1, message = "Funcionário deve ter no mínimo 1 permissão")
	private List<Permissao> permissoes = new ArrayList<>();
	
	@Max(value = 30, message = "senha precisa ter no máximo 30 caracteres")
	@NotBlank
	private String senha;
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public List<OrdemServico> getOrdensServico() {
		return ordensServico;
	}

	public void setOrdensServico(List<OrdemServico> ordensServico) {
		this.ordensServico = ordensServico;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
