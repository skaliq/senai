package org.sesi.sga.service;

import org.sesi.sga.dao.TelefoneDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.model.Telefone;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class TelefoneService extends ServiceImpl<Telefone> {

	@Override
	protected GenericDAO<Telefone> getDao() {
		return new TelefoneDAO();
	}

}
