package org.sesi.sga.managedbean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.sesi.sga.managedbean.backing.PessoaCrudBacking;
import org.sesi.sga.model.Pessoa;
import org.sesi.sga.service.PessoaService;
import org.sesi.sga.service.exceptions.DBException;

@Named
@SessionScoped
public class PessoaCrudBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject private PessoaCrudBacking backing;
	@Inject private Pessoa pessoa;
	@Inject private Pessoa pessoaSelecionada;
	@Inject private PessoaService service;
	private Integer idPessoaSelecionada;
	private List<Pessoa> pessoas;

	public PessoaCrudBean() {}

	@PostConstruct
	public void init() {
		this.pessoas = new ArrayList<>();
		this.backing.init();
	}
	
	public void onItemSelect(SelectEvent<Integer> event) {
		this.backing.setIdPessoaPesq(event.getObject());
		pesqPessoaSelecionada();
    }
	
	public void onUnidadeItemSelect(SelectEvent<Integer> event) {
		this.backing.setIdUnidade(event.getObject());
		try {
			this.pessoas = this.service.getByUnidade(this.backing.getIdUnidade());
		} catch (DBException ex) {
			addMessage(FacesMessage.SEVERITY_FATAL, "ERRO",ex.getMessage());
		}
		
	}
	
	public void onFuncaoItemSelect(SelectEvent<Integer> event) {
		this.backing.setIdFuncao(event.getObject());
		try {
			this.pessoas = this.service.getByFuncao(this.backing.getIdFuncao());
		} catch (DBException ex) {
			addMessage(FacesMessage.SEVERITY_FATAL, "ERRO",ex.getMessage());
		}
	}
	
	public void pesqPessoaSelecionada() {
		this.pessoas = new ArrayList<>();
		try {
			this.pessoaSelecionada = this.service.getById(this.backing.getIdPessoaPesq());
			this.pessoas.add(this.pessoaSelecionada);
		} catch (SQLException e) {
			addMessage(FacesMessage.SEVERITY_FATAL, "ERRO", e.getMessage());
		}
	}
	
	/** Busca e retorna a lista de registros encontrados de acordo com as letras informadas na pesquisa */
	public List<Pessoa> completeText(String query) {
		List<Pessoa> list = new ArrayList<>();
		try {
			list = this.service.getByParteNome(query.toLowerCase());
		} catch (DBException ex) {
			addMessage(FacesMessage.SEVERITY_FATAL, "ERRO",ex.getMessage());
		}
		return list;
    }
	
	/** Retorna todos os registros de Pessoa da base desde que não marcado como deletada */
	public void getAll() {
		try {
			this.pessoas = this.service.getAll("NOME_COMPLETO");
		} catch (DBException ex) {
			addMessage(FacesMessage.SEVERITY_FATAL, "ERRO", ex.getMessage());
		}
	}

	public String salva() {
		Pessoa pessoaToPersist = this.backing.configPessoa(this.pessoaSelecionada);
		try {
			this.pessoaSelecionada = service.save(pessoaToPersist);
			this.pessoas = service.getAll("NOME_COMPLETO");
		} catch (DBException ex) {
			addMessage(FacesMessage.SEVERITY_ERROR, "ERRO", ex.getMessage());
		}
		addMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", "Pessoa salva na base.");
		this.limpa();

		return "#";
	}

	/** Realiza as ações necessárias quando a edição inline é acionada */
	public void onRowEdit(RowEditEvent<?> event) {
		this.pessoaSelecionada = (Pessoa) event.getObject();
		String nomeCompleto = this.pessoaSelecionada.getNomeCompleto();
		
		try {
			if (service.update(this.pessoaSelecionada)) {
				addMessage(FacesMessage.SEVERITY_INFO,
					"Edição terminada", nomeCompleto + " foi atualizado");
			} else {
				addMessage(FacesMessage.SEVERITY_ERROR,
					"Erro na edição", nomeCompleto + " não foi atualizado");
			}
		} catch (DBException e) {
			addMessage(FacesMessage.SEVERITY_FATAL, "ERRO", e.getMessage());
		}
	}

	/** Realiza as ações necessárias quando a edição inline é cancelada */
	public void onRowCancel(RowEditEvent<?> event) {
		this.pessoaSelecionada = (Pessoa) event.getObject();
		addMessage(FacesMessage.SEVERITY_INFO, "Edição cancelada", 
				String.valueOf(this.pessoaSelecionada.getNomeCompleto()));
	}

	/** Verifica se existe uma instância de pessoaSelecionada pronta para ser usada */
	public boolean getTemPessoaSelecionada() {
		return this.pessoaSelecionada != null;
	}

	/** Prepara pessoaSelecionada para receber os dados para o cadastro de uma nova Pessoa */
	public void cadastraNovo() {
		this.pessoaSelecionada = new Pessoa();
	}

	/** Marca um registro na base de dados como deletado */
	public void remove() {
		try {
			service.removeById(pessoaSelecionada.getId());
			this.pessoas.remove(pessoaSelecionada);
			addMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", "Remoção realizada.");
		} catch (DBException e) {
			addMessage(FacesMessage.SEVERITY_FATAL, "ERRO", e.getMessage());
		}
	}

	/** Limpa a lista de registros */
	public void limpa() {
		this.pessoas = new ArrayList<>();
	}
	
	// Método para exibição de mensagens
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}
	
	// DAQUI PARA BAIXO: getters e setters
	public Pessoa getPessoaSelecionada() {
		return this.pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoa) {
		this.pessoaSelecionada = pessoa;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	public PessoaCrudBacking getBacking() {
		return backing;
	}
	public void setBacking(PessoaCrudBacking backing) {
		this.backing = backing;
	}
	public Integer getIdPessoaSelecionada() {
		return idPessoaSelecionada;
	}
	public void setIdPessoaSelecionada(Integer idPessoaSelecionada) {
		this.idPessoaSelecionada = idPessoaSelecionada;
		try {
			this.pessoaSelecionada = this.service.getById(idPessoaSelecionada);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
