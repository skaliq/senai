package org.sesi.sga.managedbean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.sesi.sga.model.Telefone;
import org.sesi.sga.service.TelefoneService;

@Named
@SessionScoped
public class TelefoneBean implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Inject private Telefone telefone;
	private TelefoneService service;
	private Telefone telefoneSelecionado;
	private List<Telefone> telefones;
	
	public TelefoneBean() {
		service = new TelefoneService();
		telefoneSelecionado = new Telefone();
		try {
			telefones = service.getAll(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String salva() {
		try {
			service.save(this.telefone);
			this.telefones = service.getAll(null);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return this.limpa();
	}
	
	public String limpa() {
		this.telefone = new Telefone();
		this.telefoneSelecionado = null;

		return "#";
	}
	
	public void remove() {
		try {
			if (service.removeById(telefoneSelecionado.getId())) {
				this.telefones.remove(telefoneSelecionado);
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
	
	public Boolean getTemTelefoneSelecionado() {
		return telefoneSelecionado != null;
	}
	
	public void cadastraNovo() {
		this.telefoneSelecionado = new Telefone();
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Telefone getTelefoneSelecionado() {
		return telefoneSelecionado;
	}

	public void setTelefoneSelecionado(Telefone telefoneSelecionado) {
		this.telefoneSelecionado = telefoneSelecionado;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

}