package org.sesi.sga.service;

import java.sql.SQLException;

import org.sesi.sga.dao.LoginDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.Login;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class LoginService extends ServiceImpl<Login> {

	@Override
	protected GenericDAO<Login> getDao() {
		return new LoginDAO();
	}

	public Login getLogin(Login login) {
		Login retorno = null;
		try {
			GenericDAOImpl.configuraConexao();
			retorno = new LoginDAO().getByLoginData(login);
			GenericDAOImpl.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
