package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.Funcao;

public class FuncaoDAO extends GenericDAOImpl<Funcao> {
	private static final String TABLE = "FUNCAO";
	private static final String[] FIELDS = {"NOME","DESCRICAO"};

	@Override
	public String getTableName() {
		return TABLE;
	}
	
	public Funcao popular(ResultSet rs) throws SQLException {
		Funcao funcao = new Funcao();
		
		funcao.setId(rs.getInt("ID"));
		funcao.setNome(rs.getString("NOME"));
		funcao.setDescricao(rs.getString("DESCRICAO"));
		
		return funcao;
	}
	
	@Override
	public PreparedStatement preparePersistStatement(Funcao entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(
				super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getNome());
		ps.setString(2, entidade.getDescricao());
		if (isUpdate) {
			ps.setInt(3, entidade.getId());
		}
		return ps;
	}

}
