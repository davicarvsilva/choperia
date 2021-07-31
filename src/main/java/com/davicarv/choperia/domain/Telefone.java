package com.davicarv.choperia.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class Telefone implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = true, length = 2)
	@Size(min=2, max = 2, message = "codigo precisa ter 2 caracteres")
	@Pattern(regexp = "^[0-9]*$")
	@NotBlank
	private String codigoPais;
	
	@Column(nullable = false, length = 2)
	@Size(min=2, max = 2, message = "codigo precisa ter 2 caracteres")
	@Pattern(regexp = "^[0-9]*$")
	@NotBlank
	private String ddd;
	
	@Column(nullable = false, length = 10)
	@Size(min=8, max = 10, message = "codigo precisa ter 10 caracteres")
	@NotBlank
	private String numero; 
	
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Telefone other = (Telefone) obj;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (ddd == null) {
			if (other.ddd != null)
				return false;
		} else if (!ddd.equals(other.ddd))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	
}
