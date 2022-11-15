package org.sesi.sga.model;

import java.io.Serializable;
import java.util.Objects;

public class Telefone implements Serializable {
	private static final long serialVersionUID = 3956177860580427346L;
	private Integer id;
	private String ddd;
	private String numero;
	private Boolean whats;
	private Boolean ativo;
	private Pessoa pessoa;
	
	public Telefone() {
		super();
	}

	public Telefone(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getWhats() {
		return whats;
	}

	public void setWhats(Boolean whats) {
		this.whats = whats;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}
	
}