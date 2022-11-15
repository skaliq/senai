package org.sesi.sga.managedbean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.sesi.sga.model.Login;
import org.sesi.sga.service.LoginService;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 3129152344325468532L;
	private String username;
	private String password;
	private LoginService service;
	private Boolean isLoggedIn;
	private Login login;
	
	public LoginBean() {
		this.service = new LoginService();
		this.login = new Login();
		this.isLoggedIn = false;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		String nextPage = "#";
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin válido", "Tudo certo");
		this.login = service.getLogin(new Login(this.username, this.password));
		if (this.login.getUsuario() != null) {
			this.isLoggedIn = true;
			nextPage = "home.xhtml";
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
		}
		
		FacesContext.getCurrentInstance().addMessage(null, message);
		PrimeFaces.current().ajax().addCallbackParam("loggedIn", this.isLoggedIn);

		return nextPage;
	}

	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
}
