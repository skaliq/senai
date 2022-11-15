package org.sesi.sga.service;

import org.sesi.sga.dao.CompromissoDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.model.Compromisso;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class CompromissoService extends ServiceImpl<Compromisso> {

	@Override
	protected GenericDAO<Compromisso> getDao() {
		return new CompromissoDAO();
	}

}
