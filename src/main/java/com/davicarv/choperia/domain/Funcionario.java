package com.davicarv.choperia.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Funcionario extends Pessoa{
	
	@Column(nullable = false, length = 100)
	@Size(min = 2, max = 100, message = "cargo deve ter entre 2 e 100 caracteres")
	@NotBlank
	private String cargo;
	
	@Column(nullable = false)
	@Positive
	@Max(value = 100000, message = "salário não pode ser maior que R$ 100000")
	private double salario;

	@JsonIgnore
	@ManyToMany
	private List<OrdemServico> ordensServico = new ArrayList<>();
	
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
	
	@Override
	public String toString() {
		return "Funcionario [cargo=" + cargo + ", salario=" + salario + ", ordensServico=" + ordensServico + "]";
	}
}
