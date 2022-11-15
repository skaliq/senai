package org.sesi.sga.service.sys;

import org.sesi.sga.dao.interfaces.GenericDAO;
import org.sesi.sga.dao.sys.MenuItemDAO;
import org.sesi.sga.model.sys.MenuItem;
import org.sesi.sga.service.interfaces.ServiceImpl;

public class MenuItemService extends ServiceImpl<MenuItem> {

	@Override
	protected GenericDAO<MenuItem> getDao() {
		return new MenuItemDAO();
	}

}
