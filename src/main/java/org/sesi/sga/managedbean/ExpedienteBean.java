package org.sesi.sga.managedbean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.sesi.sga.model.Expediente;
import org.sesi.sga.service.ExpedienteService;

@Named
@SessionScoped
public class ExpedienteBean implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Inject
	private Expediente expediente;
	private ExpedienteService service;
	private Expediente expedienteSelecionado;
	private List<Expediente> expedientes;
	
	public ExpedienteBean() {
		service = new ExpedienteService();
		expedienteSelecionado = new Expediente();
		try {
			expedientes = service.getAll(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String salva() {
		try {
			service.save(this.expediente);
			this.expedientes = service.getAll(null);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return this.limpa();
	}
	
	public String limpa() {
		this.expediente = new Expediente();
		this.expedienteSelecionado = null;

		return "#";
	}
	
	public void remove() {
		try {
			if (service.removeById(expedienteSelecionado.getId())) {
				this.expedientes.remove(expedienteSelecionado);
			} else {
				addMessage(FacesMessage.SEVERITY_ERROR,
						"Erro ao tentar excluir", 
						"Houve um erro.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addMessage(FacesMessage.Severity severity,
			String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, summary, detail));
	}
	
	public Boolean getTemExpedienteSelecionado() {
		return this.expedienteSelecionado != null;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public Expediente getExpedienteSelecionado() {
		return expedienteSelecionado;
	}

	public void setExpedienteSelecionado(Expediente expedienteSelecionado) {
		this.expedienteSelecionado = expedienteSelecionado;
	}

	public List<Expediente> getExpedientes() {
		return expedientes;
	}

	public void setExpedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
	}
	
}