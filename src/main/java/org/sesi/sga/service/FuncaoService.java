package org.sesi.sga.service;

import java.io.Serializable;

import org.sesi.sga.dao.FuncaoDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.model.Funcao;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class FuncaoService extends ServiceImpl<Funcao>  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected GenericDAO<Funcao> getDao() {
		return new FuncaoDAO();
	}

}
