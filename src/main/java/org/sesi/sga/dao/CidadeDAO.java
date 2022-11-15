package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.Cidade;
import org.sesi.sga.model.Estado;

public class CidadeDAO extends GenericDAOImpl<Cidade> {
	private static final String TABLE = "CIDADE";
	private static final String[] FIELDS = {"NOME","ESTADO"};

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public Cidade popular(ResultSet rs) throws SQLException {
		Cidade cidade = new Cidade();

		cidade.setId(rs.getInt("ID"));
		cidade.setNome(rs.getString("NOME"));		
		cidade.setSigla(Estado.fromSigla(rs.getString("ESTADO")));

		return cidade;
	}
	@Override
	public PreparedStatement preparePersistStatement(Cidade entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(
				super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getNome());
		ps.setString(2, entidade.getSigla().toString());
		if (isUpdate) {
			ps.setInt(3, entidade.getId());
		}
		return ps;
	}

}
