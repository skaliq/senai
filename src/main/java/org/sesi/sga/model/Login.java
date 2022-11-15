package org.sesi.sga.model;

import java.io.Serializable;
import java.util.Objects;

public class Login implements Serializable {
	private static final long serialVersionUID = -6538627967919114L;
	private Integer id;
	private String usuario;
	private String senha;
	private Pessoa pessoa;
	
	public Login() {
		super();
	}
	public Login(Integer id) {
		this.id = id;
	}
	
	public Login(String user, String password) {
		this.usuario = user;
		this.senha = password;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
		Login other = (Login) obj;
		return Objects.equals(id, other.id);
	}
	
}
