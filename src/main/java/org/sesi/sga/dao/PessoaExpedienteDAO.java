package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.PessoaExpediente;

public class PessoaExpedienteDAO extends GenericDAOImpl<PessoaExpediente> {
	private static final String TABLE = "PESSOA_EXPEDIENTE";
	private static final String[] FIELDS = {"DESDE","ID_PESSOA","ID_EXPEDIENTE"};

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public PessoaExpediente popular(ResultSet rs) throws SQLException {
		PessoaExpediente pessoaExpediente = new PessoaExpediente();

		pessoaExpediente.setId(rs.getInt("ID"));
		pessoaExpediente.setDesde(LocalDate.parse(rs.getString("DESDE")));
		int idPessoa = rs.getInt("ID_PESSOA");
		int idExpediente = rs.getInt("ID_EXPEDIENTE");
		pessoaExpediente.setPessoa(new PessoaDAO().getById(idPessoa));
		pessoaExpediente.setExpediente(new ExpedienteDAO().getById(idExpediente));

		return pessoaExpediente;
	}

	@Override
	public PreparedStatement preparePersistStatement(PessoaExpediente entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(
				super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getDesde().toString());
		ps.setInt(2, entidade.getPessoa().getId());
		ps.setInt(3, entidade.getExpediente().getId());
		if (isUpdate) {
			ps.setInt(4, entidade.getId());
		}
		return ps;
	}

}
