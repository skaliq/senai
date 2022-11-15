package org.sesi.sga.service;

import java.io.Serializable;

import org.sesi.sga.dao.UnidadeDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.model.Unidade;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class UnidadeService extends ServiceImpl<Unidade>  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected GenericDAO<Unidade> getDao() {
		return new UnidadeDAO();
	}

}
