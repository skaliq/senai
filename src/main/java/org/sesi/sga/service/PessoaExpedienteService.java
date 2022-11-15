package org.sesi.sga.service;

import org.sesi.sga.dao.PessoaExpedienteDAO;
import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.model.PessoaExpediente;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class PessoaExpedienteService extends ServiceImpl<PessoaExpediente> {

	@Override
	protected GenericDAO<PessoaExpediente> getDao() {
		return new PessoaExpedienteDAO();
	}

}
