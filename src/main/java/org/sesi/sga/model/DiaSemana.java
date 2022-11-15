package org.sesi.sga.model;

import java.io.Serializable;
import java.util.Objects;

public class DiaSemana implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nomeDia;
	private Integer sequencia;
	
	public DiaSemana() {
		super();
	}
	
	public DiaSemana(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeDia() {
		return nomeDia;
	}
	public void setNomeDia(String nomeDia) {
		this.nomeDia = nomeDia;
	}
	
	

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
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
		DiaSemana other = (DiaSemana) obj;
		return Objects.equals(id, other.id);
	}
	
}
