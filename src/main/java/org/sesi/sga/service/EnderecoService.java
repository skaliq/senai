package org.sesi.sga.service;

import org.sesi.sga.dao.EnderecoDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.model.Endereco;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class EnderecoService extends ServiceImpl<Endereco> {

	@Override
	protected GenericDAO<Endereco> getDao() {
		return new EnderecoDAO();
	}

}
