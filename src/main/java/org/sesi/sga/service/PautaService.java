package org.sesi.sga.service;

import org.sesi.sga.dao.PautaDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.model.Pauta;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class PautaService extends ServiceImpl<Pauta> {

	@Override
	protected GenericDAO<Pauta> getDao() {
		return new PautaDAO();
	}

}
