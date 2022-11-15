package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.Endereco;

public class EnderecoDAO extends GenericDAOImpl<Endereco> {
	private static final String TABLE = "ENDERECO";
	private static final String[] FIELDS = {"TIPO_LOGRADOURO","NOME_LOGRADOURO",
			"NUMERO_LOGRADOURO","BAIRRO","COMPLEMENTO","REFERENCIA","ID_CIDADE"};

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public Endereco popular(ResultSet rs) throws SQLException {
		Endereco endereco = new Endereco();

		endereco.setId(rs.getInt("ID"));
		endereco.setTipoLogradouro(rs.getString("TIPO_LOGRADOURO"));		
		endereco.setNomeLogradouro(rs.getString("NOME_LOGRADOURO"));
		endereco.setNumeroLogradouro(rs.getInt("NUMERO_LOGRADOURO"));
		endereco.setBairro(rs.getString("BAIRRO"));
		endereco.setComplemento(rs.getString("COMPLEMENTO"));
		endereco.setReferencia(rs.getString("REFERENCIA"));
		Integer idCidade = rs.getInt("ID_CIDADE");
		endereco.setCidade(new CidadeDAO().getById(idCidade));

		return endereco;
	}

	@Override
	public PreparedStatement preparePersistStatement(Endereco entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(super.getUpdateClause(FIELDS));
		ps.setString(1, entidade.getTipoLogradouro());
		ps.setString(2, entidade.getNomeLogradouro());
		ps.setInt(3, entidade.getNumeroLogradouro());
		ps.setString(4, entidade.getBairro());
		ps.setString(5, entidade.getComplemento());
		ps.setString(6, entidade.getReferencia());
		ps.setInt(7, entidade.getCidade().getId());
		if (isUpdate) {
			ps.setInt(8, entidade.getId());
		}
		
		return ps;
	}

}
