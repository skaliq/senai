package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.Expediente;

public class ExpedienteDAO extends GenericDAOImpl<Expediente> {
	private static final String TABLE = "EXPEDIENTE";
	private static final String[] FIELDS = {"HORA_ENTRADA","HORA_SAIDA",
			"HORA_INICIO_INTERVALO","HORA_FIM_INTERVALO"};

	@Override
	public String getTableName() {
		return TABLE;
	}
	
	public Expediente popular(ResultSet rs) throws SQLException {
		Expediente expediente = new Expediente();
		
		expediente.setId(rs.getInt("ID"));
		expediente.setHoraEntrada(LocalTime.parse(rs.getString("HORA_ENTRADA")));
		expediente.setHoraSaida(LocalTime.parse(rs.getString("HORA_SAIDA")));
		expediente.setHoraInicioIntervalo(LocalTime.parse(rs.getString("HORA_INICIO_INTERVALO")));
		expediente.setHoraFimIntervalo(LocalTime.parse(rs.getString("HORA_FIM_INTERVALO")));
		
		return expediente;
	}

	@Override
	public PreparedStatement preparePersistStatement(Expediente entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(
				super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getHoraEntrada().toString());
		ps.setString(2, entidade.getHoraSaida().toString());
		ps.setString(3, entidade.getHoraInicioIntervalo().toString());
		ps.setString(4, entidade.getHoraFimIntervalo().toString());
		if (isUpdate) {
			ps.setInt(5, entidade.getId());
		}
		return ps;
	}

}
