package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.DataCompromisso;

public class DataCompromissoDAO extends GenericDAOImpl<DataCompromisso> {
	private static final String TABLE = "DATA_COMPROMISSO";
	private static final String[] FIELDS = {"DATA","ID_PESSOA"};

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public DataCompromisso popular(ResultSet rs) throws SQLException {
		DataCompromisso data = new DataCompromisso();

		data.setId(rs.getInt("ID"));
		data.setData(LocalDate.parse(rs.getString("DATA")));
		int idPessoa = rs.getInt("ID_PESSOA");
		data.setPessoa(new PessoaDAO().getById(idPessoa));

		return data;
	}

	@Override
	public PreparedStatement preparePersistStatement(DataCompromisso entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(
				super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getData().toString());
		ps.setInt(2, entidade.getPessoa().getId());
		if (isUpdate) {
			ps.setInt(3, entidade.getId());
		}
		return ps;
	}

}
