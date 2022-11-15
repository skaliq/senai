package org.sesi.sga.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.sesi.sga.dao.PessoaDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.Pessoa;
import org.sesi.sga.model.Telefone;
import org.sesi.sga.service.exceptions.DBException;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class PessoaService extends ServiceImpl<Pessoa> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected GenericDAO<Pessoa> getDao() {
		return new PessoaDAO();
	}

	@Override
	public Pessoa save(Pessoa pessoa) throws DBException {
		Pessoa retorno = null;
		ServiceImpl<Telefone> serviceFone = new TelefoneService();
		retorno = super.save(pessoa);
		for (Telefone fone : pessoa.getTelefones()) {
			if (fone != null && fone.getNumero() != null && !fone.getNumero().isEmpty()) {
				fone.setPessoa(retorno);
				serviceFone.save(fone);
			}
		}
		return retorno;
	}

	public List<Pessoa> getByUnidade(Integer idUnidade) throws DBException {
		List<Pessoa> list = new ArrayList<>();

		GenericDAOImpl.configuraConexao();
		list = new PessoaDAO().getByUnidade(idUnidade);
		GenericDAOImpl.closeConnection();

		return list;
	}

	public List<Pessoa> getByParteNome(String parteNome) throws DBException {
		List<Pessoa> list = new ArrayList<>();

		GenericDAOImpl.configuraConexao();
		list = new PessoaDAO().getByParteNome(parteNome);
		GenericDAOImpl.closeConnection();

		return list;
	}

	public List<Pessoa> getByFuncao(Integer idFuncao) throws DBException {
		List<Pessoa> list = new ArrayList<>();

		GenericDAOImpl.configuraConexao();
		list = new PessoaDAO().getByFuncao(idFuncao);
		GenericDAOImpl.closeConnection();

		return list;
	}

}
