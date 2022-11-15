package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.Projeto;
import org.sesi.sga.model.Status;

public class ProjetoDAO extends GenericDAOImpl<Projeto> {
	private static final String TABLE = "PROJETO";
	private static final String[] FIELDS = {"NOME","DESCRICAO",
			"DATA_INICIO","DATA_FIM","STATUS"};

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public Projeto popular(ResultSet rs) throws SQLException {
		Projeto projeto = new Projeto();

		projeto.setId(rs.getInt("ID"));
		projeto.setNome(rs.getString("NOME"));
		projeto.setDescricao(rs.getString("DESCRICAO"));	

		String dataInicioString = rs.getString("DATA_INICIO");
		LocalDate dataInicio = LocalDate.parse(dataInicioString);
		projeto.setDataInicio(dataInicio);
		
		String dataFimString = rs.getString("DATA_FIM");
		if (dataFimString != null) {
			projeto.setDataInicio(LocalDate.parse(dataFimString));
		}
		
		Status status = Status.valueOf(rs.getString("STATUS"));
		projeto.setStatus(status);
		
		return projeto;
	}

	@Override
	public PreparedStatement preparePersistStatement(Projeto entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(
				super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getNome());
		ps.setString(2, entidade.getDescricao());
		ps.setString(3, entidade.getDataInicio().toString());
		ps.setString(4, entidade.getDataFim().toString());
		ps.setString(5, entidade.getStatus().toString());
		if (isUpdate) {
			ps.setInt(6, entidade.getId());
		}
		return ps;
	}

}
