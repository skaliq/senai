package org.sesi.sga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.Casa;
import org.sesi.sga.model.Pessoa;
import org.sesi.sga.service.exceptions.DBException;

public class PessoaDAO extends GenericDAOImpl<Pessoa> {
	private static final String TABLE = "PESSOA";
	private static final String[] FIELDS = {"NOME_COMPLETO","EMAIL",
			"ID_FUNCAO","CASA","ID_UNIDADE","DELETADA"};

	@Override
	public String getTableName() {
		return TABLE;
	}
	
	@Override
	public Boolean removeById(Integer id) throws DBException {
		String sql = "UPDATE " + TABLE + " SET DELETADA = ? WHERE ID = ?";
		try {
			PreparedStatement ps = super.getPreparedStatement(sql);
			ps.setBoolean(1, true);
			ps.setInt(2, id);
			ps.execute();
		} catch (SQLException e) {
			throw new DBException(e);
		}
		
		return true;
	}
	
	public String getNomeById(Integer idPessoa) throws DBException {
		String nomePessoa = null;
		String sql = "SELECT NOME_COMPLETO FROM " + TABLE + " WHERE ID = ?";
		PreparedStatement ps = super.getPreparedStatement(sql);
		try {
			ps.setInt(1, idPessoa);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				nomePessoa = rs.getString("NOME_COMPLETO");
			}
		} catch (SQLException ex) {
			throw new DBException(ex);
		}
		return nomePessoa;
	}
	
	public List<Pessoa> getByUnidade(Integer idUnidade) throws DBException {
		List<Pessoa> list = new ArrayList<>();
		String sql = "SELECT * FROM " + TABLE + " WHERE ID_UNIDADE = ?";
		
		try {
			PreparedStatement ps = super.getPreparedStatement(sql);
			ps.setInt(1, idUnidade);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(this.popular(rs));
			}
		} catch (SQLException ex) {
			throw new DBException(ex);
		}
		return list;
	}
	
	public List<Pessoa> getByFuncao(Integer idFuncao) throws DBException {
		List<Pessoa> list = new ArrayList<>();
		String sql = "SELECT * FROM " + TABLE + " WHERE ID_FUNCAO = ?";
		
		try {
			PreparedStatement ps = super.getPreparedStatement(sql);
			ps.setInt(1, idFuncao);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(this.popular(rs));
			}
		} catch (SQLException ex) {
			throw new DBException(ex);
		}
		return list;
	}
	
	/**
	 * Retorna somente os registros que tenham DELETADA = false, ou seja,
	 * somente os registros que não constem como apagados.
	 * @param parteNome
	 * @return
	 */
	public List<Pessoa> getByParteNome(String parteNome) throws DBException {
		List<Pessoa> list = new ArrayList<>();
		String sql = "SELECT * FROM " + TABLE + " WHERE NOME_COMPLETO LIKE ? AND DELETADA = false";
		
		try {
			PreparedStatement ps = super.getPreparedStatement(sql);
			ps.setString(1, "%"+parteNome+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(this.popular(rs));
			}
		} catch (SQLException ex) {
			throw new DBException(ex);
		}
		
		return list;
	}
	
	@Override
	public Pessoa getById(Integer id) throws DBException {
		String sql = "SELECT * FROM pessoa p ";
		sql += "INNER JOIN funcao f ON p.id_funcao = f.id ";
		sql += "WHERE p.id = ?";
		Pessoa pessoa = null;
		try {
			PreparedStatement ps = super.getPreparedStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pessoa = popular(rs);
			}
		} catch (SQLException e) {
			throw new DBException(e);
		}
		return pessoa;
	}

	/**
	 * Retorno todos os registros que não constam como DELETADA == true
	 */
	@Override
	public List<Pessoa> getAll(String orderBy) throws DBException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM pessoa p ");
		sql.append("INNER JOIN funcao f ON p.id_funcao = f.id ");
		sql.append("WHERE p.DELETADA = false ");
		if (orderBy != null) {
			sql.append("ORDER BY p." + orderBy);
		}
		
		List<Pessoa> lista = new ArrayList<>();
		try {
			ResultSet rs = super.getStatement().executeQuery(sql.toString());
			while (rs.next()) {
				lista.add(popular(rs));
			}
		} catch (SQLException e) {
			throw new DBException(e);
		}
		return lista;
	}

	@Override
	public Pessoa popular(ResultSet rs) throws SQLException {
		Pessoa pessoa = new Pessoa();

		pessoa.setId(rs.getInt("ID"));
		pessoa.setNomeCompleto(rs.getString("NOME_COMPLETO"));
		pessoa.setEmail(rs.getString("EMAIL"));
		pessoa.setFuncao(new FuncaoDAO().getById(rs.getInt("ID_FUNCAO")));
		pessoa.setCasa(Casa.valueOf(rs.getString("CASA")));
		pessoa.setUnidade(new UnidadeDAO().getById(rs.getInt("ID_UNIDADE")));
		pessoa.setTelefones(new TelefoneDAO().getByIdPessoa(pessoa.getId()));
		pessoa.setDeletada(rs.getBoolean("DELETADA"));

		return pessoa;
	}


	/**
	 * O valor default para o campo DELETADA é false. Por isso este campo é settado
	 * como false na inclusão, ou seja, se um registro está sendo incluído ele não 
	 * deve ser incluído já deletado, mas ativo.
	 */
	@Override
	public PreparedStatement preparePersistStatement(Pessoa entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getNomeCompleto());
		ps.setString(2, entidade.getEmail());
		ps.setInt(3, entidade.getUnidade().getId());
		ps.setInt(4, entidade.getFuncao().getId());
		ps.setString(5, entidade.getCasa().toString());

		if (isUpdate) {
			ps.setBoolean(6, entidade.getDeletada());
			ps.setInt(7, entidade.getId());
		} else {
			ps.setBoolean(6, Boolean.TRUE);
		}
		return ps;
	}

}
