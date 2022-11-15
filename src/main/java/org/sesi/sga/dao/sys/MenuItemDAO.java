package org.sesi.sga.dao.sys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.sys.MenuItem;

public class MenuItemDAO extends GenericDAOImpl<MenuItem> {
	private static final String TABLE = "MENU_ITEM";
	private static final String[] FIELDS = {"LABEL","ID_MENU","ICON","URL"};

	@Override
	public String getTableName() {
		return TABLE;
	}
	
	public MenuItem popular(ResultSet rs) throws SQLException {
		MenuItem menu = new MenuItem();
		
		menu.setId(rs.getInt("ID"));
		menu.setLabel(rs.getString("LABEL"));
		menu.setMenu(new MenuDAO().getById(rs.getInt("ID_MENU")));
		menu.setIcon(rs.getString("ICON"));
		menu.setUrl(rs.getString("URL"));
		
		return menu;
	}

	@Override
	public PreparedStatement preparePersistStatement(MenuItem entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(
				super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getLabel());
		ps.setInt(2, entidade.getMenu().getId());
		ps.setString(3, entidade.getIcon());
		ps.setString(4, entidade.getUrl());
		if (isUpdate) {
			ps.setInt(5, entidade.getId());
		}
		return ps;
	}

}
