package org.sesi.sga.managedbean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.sesi.sga.model.Compromisso;
import org.sesi.sga.service.CompromissoService;

@Named
@SessionScoped
public class CompromissoBean implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Inject
	private Compromisso compromisso;
	private CompromissoService service;
	private Compromisso compromissoSelecionado;
	private List<Compromisso> compromissos;
	
	public CompromissoBean() {
		service = new CompromissoService();
		compromissoSelecionado = new Compromisso();
		try {
			compromissos = service.getAll("HORA_INICIO");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String salva() {
		try {
			service.save(this.compromisso);
			this.compromissos = service.getAll("HORA_INICIO");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return this.limpa();
	}
	
	public String limpa() {
		this.compromisso = new Compromisso();
		this.compromissoSelecionado = null;

		return "#";
	}
	
	public void remove() {
		try {
			if (service.removeById(compromissoSelecionado.getId())) {
				this.compromissos.remove(compromissoSelecionado);
			} else {
				addMessage(FacesMessage.SEVERITY_ERROR,
						"Erro ao tentar excluir", 
						"Há sete dias da semana cadastrados."
						+ " Não é permitido excluir ou adicionar"
						+ " nenhum registro.");
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
	
	public Boolean getTemCompromissoSelecionado() {
		return compromissoSelecionado != null;
	}

	public Compromisso getCompromisso() {
		return compromisso;
	}

	public void setCompromisso(Compromisso compromisso) {
		this.compromisso = compromisso;
	}

	public Compromisso getCompromissoSelecionado() {
		return compromissoSelecionado;
	}

	public void setCompromissoSelecionado(Compromisso compromissoSelecionado) {
		this.compromissoSelecionado = compromissoSelecionado;
	}

	public List<Compromisso> getCompromissos() {
		return compromissos;
	}

	public void setCompromissos(List<Compromisso> compromissos) {
		this.compromissos = compromissos;
	}
	
}