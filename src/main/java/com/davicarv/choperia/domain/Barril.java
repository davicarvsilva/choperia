package com.davicarv.choperia.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class Barril implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@Positive
	@Min(value = 10, message = "tamanho não pode ser menor que 10")
	@Max(value = 50, message = "tamanho não pode ser maior que 50")
	private Integer tamanho;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private MarcaBarrilEnum marca;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private Calendar fabricacao;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private Calendar validade;

	@Column(nullable = false)
	@Positive
	@Min(value = 10, message = "preço não pode ser menor que R$ 10,00")
	@Max(value = 3000, message = "preço não pode ser maior que R$ 3000")
	private double preco;

	@JsonIgnore
	@ManyToOne
	private OrdemServico ordemServico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public MarcaBarrilEnum getMarca() {
		return marca;
	}

	public void setMarca(MarcaBarrilEnum marca) {
		this.marca = marca;
	}

	public Calendar getFabricacao() {
		return fabricacao;
	}

	public void setFabricacao(Calendar fabricacao) {
		this.fabricacao = fabricacao;
	}

	public Calendar getValidade() {
		return validade;
	}

	public void setValidade(Calendar validade) {
		this.validade = validade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	@Override
	public String toString() {
		return "Barril [id=" + id + ", tamanho=" + tamanho + ", marca=" + marca + ", fabricacao=" + fabricacao
				+ ", validade=" + validade + ", preco=" + preco + ", ordemServico=" + ordemServico + "]";
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
		Barril other = (Barril) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
