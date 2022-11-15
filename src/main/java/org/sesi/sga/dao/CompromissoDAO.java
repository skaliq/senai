package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.Compromisso;

public class CompromissoDAO extends GenericDAOImpl<Compromisso> {
	private static final String TABLE = "COMPROMISSO";
	private static final String[] FIELDS = {"HORA_INICIO","HORA_FIM",
			"ASSUNTO","ID_DATA_COMPROMISSO","ID_PAUTA","ID_PROJETO"};

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public Compromisso popular(ResultSet rs) throws SQLException {
		Compromisso compromisso = new Compromisso();

		compromisso.setId(rs.getInt("ID"));
		compromisso.setHoraInicio(LocalTime.parse(rs.getString("HORA_INICIO")));
		compromisso.setHoraFim(LocalTime.parse(rs.getString("HORA_FIM")));
		compromisso.setAssunto(rs.getString("ASSUNTO"));
		
		int idDataCompromisso = rs.getInt("ID_DATA_COMPROMISSO");
		compromisso.setDataCompromisso(new DataCompromissoDAO().getById(idDataCompromisso));

		int idPauta = rs.getInt("ID_PAUTA");
		compromisso.setPauta(new PautaDAO().getById(idPauta));
		
		Integer idProjeto = rs.getInt("ID_PROJETO");
		if (idProjeto != null) {
			compromisso.setProjeto(new ProjetoDAO().getById(idProjeto));
		}
		
		return compromisso;
	}

	@Override
	public PreparedStatement preparePersistStatement(Compromisso entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(getUpdateClause(FIELDS));
		ps.setString(1, entidade.getHoraInicio().toString());
		ps.setString(2, entidade.getHoraFim().toString());
		ps.setString(3, entidade.getAssunto());
		ps.setInt(4, entidade.getDataCompromisso().getId());
		ps.setInt(5, entidade.getPauta().getId());
		ps.setInt(6, entidade.getProjeto().getId());
		if (isUpdate) {
			ps.setInt(7, entidade.getId());
		}
		return ps;
	}

}
