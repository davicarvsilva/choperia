package com.davicarv.choperia.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, length = 100)
	@Size(min = 10, max = 100, message = "rua deve ter entre 10 e 100 caracteres")
	@NotBlank
	private String rua;
	
	@Size(min = 1, max = 10000, message = "número deve ter entre 1 e 10000 caracteres")
	@NotBlank
	@Column(nullable = true, length=10000)
	@Pattern(regexp = "^[0-9]*$")
	private String numero;
	
	@Column(nullable = false ,length = 100)
	@NotBlank
	@Size(min = 5, max = 100, message = "bairro deve ter entre 5 e 100 caracteres")
	private String bairro;
	
	@Column(nullable = false, length = 100)
	@NotBlank
	@Size(min = 5, max = 100, message = "cidade deve ter entre 5 e 100 caracteres")
	private String cidade;
	
	@Column(nullable = false, length = 2)
	@NotBlank
	@Size(min = 2, max = 2)
	private String estado;
	
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
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
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		return true;
	}
	
	
	
}
