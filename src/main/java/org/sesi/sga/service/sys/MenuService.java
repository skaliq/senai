package org.sesi.sga.service.sys;

import java.sql.SQLException;
import java.util.List;

import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.dao.sys.MenuDAO;
import org.sesi.sga.model.sys.Menu;
import org.sesi.sga.model.sys.MenuItem;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class MenuService extends ServiceImpl<Menu> {

	@Override
	protected GenericDAO<Menu> getDao() {
		return new MenuDAO();
	}
	
	public List<MenuItem> getItensByMenu(Menu menu) {
		List<MenuItem> lista = null;
		try {
			GenericDAOImpl.configuraConexao();
			lista = new MenuDAO().getItensByMenu(menu);
			GenericDAOImpl.closeConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}

}
