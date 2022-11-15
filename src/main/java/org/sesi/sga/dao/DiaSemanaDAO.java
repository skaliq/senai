package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.DiaSemana;

public class DiaSemanaDAO extends GenericDAOImpl<DiaSemana> {
	private static final String TABLE = "DIA_SEMANA";
	private static final String[] FIELDS = {"NOME_DIA","SEQUENCIA"};

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public DiaSemana popular(ResultSet rs) throws SQLException {
		DiaSemana dia = new DiaSemana();
		dia.setId(rs.getInt("ID"));
		dia.setNomeDia(rs.getString("NOME_DIA"));
		dia.setSequencia(rs.getInt("SEQUENCIA"));
		return dia;
	}

	@Override
	public PreparedStatement preparePersistStatement(DiaSemana entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(
				super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getNomeDia());
		ps.setInt(2, entidade.getSequencia());
		if (isUpdate) {
			ps.setInt(3, entidade.getId());
		}
		return ps;
	}

}
