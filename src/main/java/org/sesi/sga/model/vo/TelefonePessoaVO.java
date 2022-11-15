package org.sesi.sga.model.vo;

import java.io.Serializable;

import org.sesi.sga.model.Telefone;

public class TelefonePessoaVO implements Serializable {
	private static final long serialVersionUID = 8275782877917750143L;
	private Telefone telefone;
	private String nomePessoa;
	
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public String getNomePessoa() {
		return nomePessoa;
	}
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
}