package org.sesi.sga.service;

import org.sesi.sga.dao.CidadeDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.model.Cidade;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class CidadeService extends ServiceImpl<Cidade> {

	@Override
	protected GenericDAO<Cidade> getDao() {
		return new CidadeDAO();
	}

}
