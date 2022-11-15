package org.sesi.sga.managedbean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.sesi.sga.model.Unidade;
import org.sesi.sga.service.UnidadeService;

@Named
@SessionScoped
public class UnidadeBean implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Inject
	private Unidade unidade;
	private UnidadeService service;
	private Unidade unidadeSelecionada;
	private List<Unidade> unidades;
	
	public UnidadeBean() {
		service = new UnidadeService();
		unidadeSelecionada = new Unidade();
		try {
			unidades = service.getAll("NOME");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String salva() {
		try {
			service.save(this.unidade);
			this.unidades = service.getAll("NOME");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return this.limpa();
	}
	
	public String limpa() {
		this.unidade = new Unidade();
		this.unidadeSelecionada = null;

		return "#";
	}
	
	public void remove() {
		try {
			if (service.removeById(unidadeSelecionada.getId())) {
				this.unidades.remove(unidadeSelecionada);
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
	
	public Boolean getTemDiaSemanaSelecionado() {
		return unidadeSelecionada != null;
	}
	
	public void cadastraNovo() {
		this.unidadeSelecionada = new Unidade();
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Unidade getUnidadeSelecionada() {
		return unidadeSelecionada;
	}

	public void setUnidadeSelecionada(Unidade unidadeSelecionada) {
		this.unidadeSelecionada = unidadeSelecionada;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}

	
}