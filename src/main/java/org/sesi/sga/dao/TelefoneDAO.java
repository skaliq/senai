package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.Pessoa;
import org.sesi.sga.model.Telefone;

public class TelefoneDAO extends GenericDAOImpl<Telefone> {
	private static final String TABLE = "TELEFONE";
	private static final String[] FIELDS = {"DDD","NUMERO","WHATS",
			"ID_PESSOA","ATIVO"};

	@Override
	public String getTableName() {
		return TABLE;
	}
	
	public List<Telefone> getByIdPessoa(Integer id) {
		List<Telefone> telefones = new ArrayList<>();
		String sql = "SELECT * FROM " + TABLE + " WHERE ID_PESSOA = ?";
		try {
			PreparedStatement ps = super.getPreparedStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				telefones.add(this.popular(rs));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return telefones;
	}

	/**
	 * A sobrescrita deste método retorno somente a propriedade
	 * Pessoa.nomeCompleto, não trazendo outras propriedades da instância de Pessoa.
	 */
	@Override
	public Telefone popular(ResultSet rs) throws SQLException {
		Telefone telefone = new Telefone();
		Pessoa pessoa = new Pessoa();
		pessoa.setNomeCompleto(new PessoaDAO().getNomeById(rs.getInt("ID_PESSOA")));
		telefone.setPessoa(pessoa);

		telefone.setId(rs.getInt("ID"));
		telefone.setDdd(rs.getString("DDD"));
		telefone.setNumero(rs.getString("NUMERO"));
		telefone.setWhats(rs.getBoolean("WHATS"));		
		telefone.setAtivo(rs.getBoolean("ATIVO"));

		return telefone;
	}
	@Override
	public PreparedStatement preparePersistStatement(Telefone entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(
				super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getDdd());
		ps.setString(2, entidade.getNumero());
		ps.setBoolean(3, entidade.getWhats());
		ps.setInt(4, entidade.getPessoa().getId());
		ps.setBoolean(5, entidade.getAtivo());
		if (isUpdate) {
			ps.setInt(6, entidade.getId());
		}
		return ps;
	}
	
}
