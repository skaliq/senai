package org.sesi.sga.service;

import org.sesi.sga.dao.DiaSemanaDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.model.DiaSemana;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class DiaSemanaService extends ServiceImpl<DiaSemana> {

	@Override
	protected GenericDAO<DiaSemana> getDao() {
		return new DiaSemanaDAO();
	}

}
