package org.sesi.sga.model;

import java.time.LocalDate;
import java.util.Objects;

public class PessoaExpediente {
	private Integer id;
	private LocalDate desde;
	private Pessoa pessoa;
	private Expediente expediente;
	
	public PessoaExpediente() {
		super();
	}
	public PessoaExpediente(Integer id) {
		super();
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getDesde() {
		return desde;
	}
	public void setDesde(LocalDate desde) {
		this.desde = desde;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Expediente getExpediente() {
		return expediente;
	}
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
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
		PessoaExpediente other = (PessoaExpediente) obj;
		return Objects.equals(id, other.id);
	}
	
}
