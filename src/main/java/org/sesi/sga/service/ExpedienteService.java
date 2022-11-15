package org.sesi.sga.service;

import org.sesi.sga.dao.ExpedienteDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.model.Expediente;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class ExpedienteService extends ServiceImpl<Expediente> {

	@Override
	protected GenericDAO<Expediente> getDao() {
		return new ExpedienteDAO();
	}

}
