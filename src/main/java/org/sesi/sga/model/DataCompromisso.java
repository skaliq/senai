package org.sesi.sga.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class DataCompromisso implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LocalDate data;
	private Pessoa pessoa;
	
	public DataCompromisso() {
		super();
	}
	
	public DataCompromisso(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
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
		DataCompromisso other = (DataCompromisso) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
