package org.sesi.sga.managedbean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.sesi.sga.model.DataCompromisso;
import org.sesi.sga.service.DataCompromissoService;

@Named
@SessionScoped
public class DataCompromissoBean implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Inject
	private DataCompromisso dataCompromisso;
	private DataCompromissoService service;
	private DataCompromisso dataCompromissoSelecionado;
	private List<DataCompromisso> dataCompromissos;
	
	public DataCompromissoBean() {
		service = new DataCompromissoService();
		dataCompromissoSelecionado = new DataCompromisso();
		try {
			dataCompromissos = service.getAll("DATA");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String salva() {
		try {
			service.save(this.dataCompromisso);
			this.dataCompromissos = service.getAll("DATA");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return this.limpa();
	}
	
	public String limpa() {
		this.dataCompromisso = new DataCompromisso();
		this.dataCompromissoSelecionado = null;

		return "#";
	}
	
	public void remove() {
		try {
			if (service.removeById(dataCompromissoSelecionado.getId())) {
				this.dataCompromissos.remove(dataCompromissoSelecionado);
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
	
	public Boolean getTemDataCompromissoSelecionado() {
		return dataCompromissoSelecionado != null;
	}

	public DataCompromisso getDataCompromisso() {
		return dataCompromisso;
	}

	public void setDataCompromisso(DataCompromisso dataCompromisso) {
		this.dataCompromisso = dataCompromisso;
	}

	public DataCompromisso getDataCompromissoSelecionado() {
		return dataCompromissoSelecionado;
	}

	public void setDataCompromissoSelecionado(DataCompromisso dataCompromissoSelecionado) {
		this.dataCompromissoSelecionado = dataCompromissoSelecionado;
	}

	public List<DataCompromisso> getDataCompromissos() {
		return dataCompromissos;
	}

	public void setDataCompromissos(List<DataCompromisso> dataCompromissos) {
		this.dataCompromissos = dataCompromissos;
	}

}