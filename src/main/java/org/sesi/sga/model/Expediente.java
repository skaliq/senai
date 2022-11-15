package org.sesi.sga.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

public class Expediente implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LocalTime horaEntrada;
	private LocalTime horaSaida;
	private LocalTime horaInicioIntervalo;
	private LocalTime horaFimIntervalo;
	
	public Expediente() {
		super();
	}

	public Expediente(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public LocalTime getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(LocalTime horaSaida) {
		this.horaSaida = horaSaida;
	}
	public LocalTime getHoraInicioIntervalo() {
		return horaInicioIntervalo;
	}
	public void setHoraInicioIntervalo(LocalTime horaInicioIntervalo) {
		this.horaInicioIntervalo = horaInicioIntervalo;
	}
	public LocalTime getHoraFimIntervalo() {
		return horaFimIntervalo;
	}
	public void setHoraFimIntervalo(LocalTime horaFimIntervalo) {
		this.horaFimIntervalo = horaFimIntervalo;
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
		Expediente other = (Expediente) obj;
		return Objects.equals(id, other.id);
	}
	
}