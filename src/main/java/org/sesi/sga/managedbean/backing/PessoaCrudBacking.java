package org.sesi.sga.managedbean.backing;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.sesi.sga.model.Casa;
import org.sesi.sga.model.Funcao;
import org.sesi.sga.model.Pessoa;
import org.sesi.sga.model.Telefone;
import org.sesi.sga.model.Unidade;
import org.sesi.sga.service.FuncaoService;
import org.sesi.sga.service.UnidadeService;

/** Classe que armazena valores de uso exclusivo na página xhtml */
public class PessoaCrudBacking implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject private Telefone telefone1;
	@Inject private Telefone telefone2;
	@Inject private FuncaoService funcaoService;
	@Inject private UnidadeService unidadeService;
	private List<Funcao> funcoes;
	private List<Unidade> unidades;
	private Casa[] casas;
	private Casa casa;
	private Integer idFuncao;
	private Integer idUnidade;
	private Integer idPessoaPesq;
	
	public void init() {
		try {
			this.funcoes = this.funcaoService.getAll("NOME");
			this.unidades = this.unidadeService.getAll("NOME");
			this.casas = Casa.values();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Pessoa configPessoa(Pessoa pessoa) {
		Pessoa configuredPessoa = pessoa;
		configuredPessoa.setFuncao(new Funcao(this.idFuncao));
		configuredPessoa.setUnidade(new Unidade(this.idUnidade));
		configuredPessoa.setCasa(this.casa);
		configuredPessoa.setTelefones(new ArrayList<>());
		this.telefone1.setAtivo(true);
		this.telefone2.setAtivo(true);
		configuredPessoa.getTelefones().add(telefone1);
		configuredPessoa.getTelefones().add(telefone2);
		return configuredPessoa;
	}
	
	public List<Funcao> getFuncoes() {
		return funcoes;
	}
	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}
	public List<Unidade> getUnidades() {
		return unidades;
	}
	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}
	public Casa[] getCasas() {
		return casas;
	}
	public void setCasas(Casa[] casas) {
		this.casas = casas;
	}
	public Casa getCasa() {
		return casa;
	}
	public void setCasa(Casa casa) {
		this.casa = casa;
	}
	public Integer getIdFuncao() {
		return idFuncao;
	}
	public void setIdFuncao(Integer idFuncao) {
		this.idFuncao = idFuncao;
	}
	public Integer getIdUnidade() {
		return idUnidade;
	}
	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}
	public Integer getIdPessoaPesq() {
		return idPessoaPesq;
	}
	public void setIdPessoaPesq(Integer idPessoaPesq) {
		this.idPessoaPesq = idPessoaPesq;
	}
	public Telefone getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(Telefone telefone1) {
		this.telefone1 = telefone1;
	}
	public Telefone getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(Telefone telefone2) {
		this.telefone2 = telefone2;
	}
	
}
