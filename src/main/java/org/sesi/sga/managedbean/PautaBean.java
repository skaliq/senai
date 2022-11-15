package org.sesi.sga.managedbean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.sesi.sga.model.Pauta;
import org.sesi.sga.service.PautaService;

@Named
@SessionScoped
public class PautaBean implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Inject
	private Pauta pauta;
	private PautaService service;
	private Pauta pautaSelecionada;
	private List<Pauta> pautas;
	
	public PautaBean() {
		service = new PautaService();
		pautaSelecionada = new Pauta();
		try {
			pautas = service.getAll("ASSUNTO");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String salva() {
		try {
			service.save(this.pauta);
			this.pautas = service.getAll("ASSUNTO");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return this.limpa();
	}
	
	public String limpa() {
		this.pauta = new Pauta();
		this.pautaSelecionada = null;

		return "#";
	}
	
	public void remove() {
		try {
			if (service.removeById(pautaSelecionada.getId())) {
				this.pautas.remove(pautaSelecionada);
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
	
	public Boolean getTemPautaSelecionada() {
		return pautaSelecionada != null;
	}
	
	public void cadastraNovo() {
		this.pautaSelecionada = new Pauta();
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public Pauta getPautaSelecionada() {
		return pautaSelecionada;
	}

	public void setPautaSelecionada(Pauta pautaSelecionada) {
		this.pautaSelecionada = pautaSelecionada;
	}

	public List<Pauta> getPautas() {
		return pautas;
	}

	public void setPautas(List<Pauta> pautas) {
		this.pautas = pautas;
	}
}