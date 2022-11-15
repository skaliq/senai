package org.sesi.sga.model;

import java.util.Objects;

public class ExpedienteDiaSemana {
	private Integer idExpediente;
	private Integer idDiaSemana;
	private Expediente expediente;
	private DiaSemana diaSemana;
	
	public ExpedienteDiaSemana() {
		super();
	}

	public ExpedienteDiaSemana(Integer idExpediente,
			Integer idDiaSemana) {
		super();
		this.idExpediente = idExpediente;
		this.idDiaSemana = idDiaSemana;
	}

	public Integer getIdExpediente() {
		return idExpediente;
	}
	public void setIdExpediente(Integer idExpediente) {
		this.idExpediente = idExpediente;
	}
	public Integer getIdDiaSemana() {
		return idDiaSemana;
	}
	public void setIdDiaSemana(Integer idDiaSemana) {
		this.idDiaSemana = idDiaSemana;
	}
	public Expediente getExpediente() {
		return expediente;
	}
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	public DiaSemana getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDiaSemana, idExpediente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExpedienteDiaSemana other = (ExpedienteDiaSemana) obj;
		return Objects.equals(idDiaSemana, other.idDiaSemana) && Objects.equals(idExpediente, other.idExpediente);
	}
	
}
