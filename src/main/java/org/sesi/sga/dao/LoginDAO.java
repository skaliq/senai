package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.Login;
import org.sesi.sga.service.PessoaService;

public class LoginDAO extends GenericDAOImpl<Login> {
	private static final String TABLE = "LOGIN";
	private static final String[] FIELDS = {"USUARIO","SENHA","ID_PESSOA"};

	@Override
	public String getTableName() {
		return TABLE;
	}
	
	public Login getByLoginData(Login login) {
		Login theLogin = new Login();
		String sql = "SELECT * FROM login WHERE usuario = ? AND senha = ?";
		try {
			PreparedStatement ps = super.getPreparedStatement(sql);
			ps.setString(1, login.getUsuario());
			ps.setString(2, login.getSenha());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				theLogin = popular(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theLogin;
	}

	@Override
	public Login popular(ResultSet rs) throws SQLException {
		Login login = new Login();

		login.setId(rs.getInt("ID"));
		login.setUsuario(rs.getString("USUARIO"));
		login.setSenha(rs.getString("SENHA"));
		login.setPessoa(new PessoaService().getById(rs.getInt("ID_PESSOA")));

		return login;
	}

	@Override
	public PreparedStatement preparePersistStatement(Login entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(
				super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getUsuario());
		ps.setString(2, entidade.getSenha());
		ps.setInt(3, entidade.getPessoa().getId());
		if (isUpdate) {
			ps.setInt(4, entidade.getId());
		}
		return ps;
	}

}
