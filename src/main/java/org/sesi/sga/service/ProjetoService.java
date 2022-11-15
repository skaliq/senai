package org.sesi.sga.service;

import org.sesi.sga.dao.ProjetoDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.model.Projeto;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class ProjetoService extends ServiceImpl<Projeto> {

	@Override
	protected GenericDAO<Projeto> getDao() {
		return new ProjetoDAO();
	}

}
