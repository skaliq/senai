package org.sesi.sga.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

public class Compromisso implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	private String assunto;
	private DataCompromisso dataCompromisso;
	private Pauta pauta;
	private Projeto projeto;
	
	public Compromisso() {
		super();
	}
	
	public Compromisso(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public DataCompromisso getDataCompromisso() {
		return dataCompromisso;
	}

	public void setDataCompromisso(DataCompromisso dataCompromisso) {
		this.dataCompromisso = dataCompromisso;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
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
		Compromisso other = (Compromisso) obj;
		return Objects.equals(id, other.id);
	}
	
}
