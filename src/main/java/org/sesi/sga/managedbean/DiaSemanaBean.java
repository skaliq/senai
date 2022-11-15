package org.sesi.sga.managedbean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.sesi.sga.model.DiaSemana;
import org.sesi.sga.service.DiaSemanaService;

@Named
@SessionScoped
public class DiaSemanaBean implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Inject
	private DiaSemana diaSemana;
	private DiaSemanaService service;
	private DiaSemana diaSemanaSelecionado;
	private List<DiaSemana> diasSemana;
	
	public DiaSemanaBean() {
		service = new DiaSemanaService();
		diaSemanaSelecionado = new DiaSemana();
		try {
			diasSemana = service.getAll("SEQUENCIA");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String salva() {
		try {
			service.save(this.diaSemana);
			this.diasSemana = service.getAll("SEQUENCIA");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return this.limpa();
	}
	
	public String limpa() {
		this.diaSemana = new DiaSemana();
		this.diaSemanaSelecionado = null;

		return "#";
	}
	
	public void remove() {
		try {
			if (service.removeById(diaSemanaSelecionado.getId())) {
				this.diasSemana.remove(diaSemanaSelecionado);
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
	
	public Boolean getTemDiaSemanaSelecionado() {
		return diaSemanaSelecionado != null;
	}
	
	public void cadastraNovo() {
		this.diaSemanaSelecionado = new DiaSemana();
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}
	public DiaSemana getDiaSemanaSelecionado() {
		return diaSemanaSelecionado;
	}
	public void setDiaSemanaSelecionado(DiaSemana diaSemanaSelecionado) {
		this.diaSemanaSelecionado = diaSemanaSelecionado;
	}
	public List<DiaSemana> getDiasSemana() {
		return diasSemana;
	}
	public void setDiasSemana(List<DiaSemana> diasSemana) {
		this.diasSemana = diasSemana;
	}
}