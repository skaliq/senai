package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.Unidade;

public class UnidadeDAO extends GenericDAOImpl<Unidade> {
	private static final String TABLE = "UNIDADE";
	private static final String[] FIELDS = {"NOME","TELEFONE","EMAIL","ID_ENDERECO"};

	@Override
	public String getTableName() {
		return TABLE;
	}
	
	public Unidade popular(ResultSet rs) throws SQLException {
		Unidade unidade = new Unidade();
		
		unidade.setId(rs.getInt("ID"));
		unidade.setNome(rs.getString("NOME"));
		unidade.setTelefone(rs.getString("TELEFONE"));
		unidade.setEmail(rs.getString("EMAIL"));
		
		int idEndereco = rs.getInt("ID_ENDERECO");
		unidade.setEndereco(new EnderecoDAO().getById(idEndereco));
		
		return unidade;
	}

	@Override
	public PreparedStatement preparePersistStatement(Unidade entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(
				super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getNome());
		ps.setString(2, entidade.getTelefone());
		ps.setString(3, entidade.getEmail());
		if (isUpdate) {
			ps.setInt(4, entidade.getId());
		}
		return ps;
	}

}
