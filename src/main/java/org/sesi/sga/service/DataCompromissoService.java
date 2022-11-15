package org.sesi.sga.service;

import org.sesi.sga.dao.DataCompromissoDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.model.DataCompromisso;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class DataCompromissoService extends ServiceImpl<DataCompromisso> {

	@Override
	protected GenericDAO<DataCompromisso> getDao() {
		return new DataCompromissoDAO();
	}

}
