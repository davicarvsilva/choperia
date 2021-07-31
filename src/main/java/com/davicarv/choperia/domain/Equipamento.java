package com.davicarv.choperia.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Equipamento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, precision = 2)
	@Positive
	@Max(value=100000, message = "valor de comodato não deve ter ser maior que 100.000")
	private double valorComodato;
	
	@Column(nullable = false, precision = 2)
	@Positive
	@Max(value=100000, message = "Valor de venda não pode ser maior que 100.000")
	private double valorVenda;
	
	@Column(nullable = false, length=100)
	@Size(min = 8, max = 100, message = "descrição deve ter entre 8 e 100 caracteres")
	@NotBlank
	private String descricao;
	
	@Column(nullable = false, length = 10)
	@Size(min = 6, message = "codigo interno deve ter pelo menos 6 caracteres")
	@NotBlank
	private String codigoInterno;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private StatusEquipamentoEnum status;
	
	@JsonIgnore
	@ManyToMany
	private List<OrdemServico> ordensServico = new ArrayList<>();

	@Override
	public String toString() {
		return "Equipamento [id=" + id + ", valorComodato=" + valorComodato + ", valorVenda=" + valorVenda
				+ ", descricao=" + descricao + ", codigoInterno=" + codigoInterno + ", status=" + status
				+ ", ordensServico=" + ordensServico + "]";
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getValorComodato() {
		return valorComodato;
	}
	public void setValorComodato(double valorComodato) {
		this.valorComodato = valorComodato;
	}
	public double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCodigoInterno() {
		return codigoInterno;
	}
	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}
	public StatusEquipamentoEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEquipamentoEnum status) {
		this.status = status;
	}
	
	public List<OrdemServico> getOrdensServico() {
		return ordensServico;
	}
	public void setOrdensServico(List<OrdemServico> ordensServico) {
		this.ordensServico = ordensServico;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Equipamento other = (Equipamento) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
