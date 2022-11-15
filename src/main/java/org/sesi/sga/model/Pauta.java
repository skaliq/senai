package org.sesi.sga.model;

import java.io.Serializable;
import java.util.Objects;

public class Pauta implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String assunto;
	
	public Pauta() {
		super();
	}
	
	public Pauta(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
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
		Pauta other = (Pauta) obj;
		return Objects.equals(id, other.id);
	}
	
}
