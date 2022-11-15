package org.sesi.sga.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nomeCompleto;
	private String email;
	private Funcao funcao;
	private Unidade unidade;
	private Casa casa;
	private List<Telefone> telefones;
	private Boolean deletada;
	
	public Pessoa(Integer id) {
		this.id = id;
	}
	public Pessoa() {
		super();
	}
	public Casa getCasa() {
		return casa;
	}
	public void setCasa(Casa casa) {
		this.casa = casa;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public Boolean getDeletada() {
		return deletada;
	}
	public void setDeletada(Boolean deletada) {
		this.deletada = deletada;
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
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}	
	
}
