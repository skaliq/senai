package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.Pauta;

public class PautaDAO extends GenericDAOImpl<Pauta> {
	private static final String TABLE = "PAUTA";
	private static final String[] FIELDS = {"ASSUNTO"};

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public Pauta popular(ResultSet rs) throws SQLException {
		Pauta pauta = new Pauta();

		pauta.setId(rs.getInt("ID"));
		pauta.setAssunto(rs.getString("ASSUNTO"));

		return pauta;
	}

	@Override
	public PreparedStatement preparePersistStatement(Pauta entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(
				super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getAssunto());
		if (isUpdate) {
			ps.setInt(2, entidade.getId());
		}
		return ps;
	}

}
