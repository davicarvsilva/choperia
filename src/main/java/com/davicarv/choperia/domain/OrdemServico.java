package com.davicarv.choperia.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class OrdemServico implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = true)
	private boolean assepsia;
	
	@Column(nullable = true, length = 200)
	@Size(min = 10, max = 200)
	@NotBlank
	private String descricao;
	
	@Column(nullable = false)
	private Calendar dataCriacao;
	
	@Column(nullable = false)
	private Calendar dataEntrega;
	
	@Column(nullable = false)
	private Calendar dataDevolucao;
	
	@Column(nullable = false)
	private double acrescimo;
	
	@Column(nullable = false)
	private double desconto;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusOrdemServico status;
	
	@ManyToOne
	@NotNull
	private Cliente cliente;
	
	@Size(min = 1)
	@ManyToMany(mappedBy ="ordensServico")
	private List<Funcionario> functionarios = new ArrayList<>();
	
	@ManyToMany(mappedBy = "ordensServico")
	private List<Equipamento> equipamentos = new ArrayList<>();
	
	@OneToMany(mappedBy = "ordemServico")
	private List<Barril> barris = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isAssepsia() {
		return assepsia;
	}
	public void setAssepsia(boolean assepsia) {
		this.assepsia = assepsia;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Calendar getdataCriacao(){
		return dataCriacao;
	}
	public Calendar getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Calendar dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public Calendar getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Calendar dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public double getAcrescimo() {
		return acrescimo;
	}
	public void setAcrescimo(double acrescimo) {
		this.acrescimo = acrescimo;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public StatusOrdemServico getStatus() {
		return status;
	}
	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Funcionario> getFunctionarios() {
		return functionarios;
	}
	public void setFunctionarios(List<Funcionario> functionarios) {
		this.functionarios = functionarios;
	}
	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}
	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}
	public List<Barril> getBarris() {
		return barris;
	}
	public void setBarris(List<Barril> barris) {
		this.barris = barris;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
