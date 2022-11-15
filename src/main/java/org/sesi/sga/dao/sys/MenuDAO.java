package org.sesi.sga.dao.sys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;
import org.sesi.sga.model.sys.Menu;
import org.sesi.sga.model.sys.MenuItem;

public class MenuDAO extends GenericDAOImpl<Menu> {
	private static final String TABLE = "MENU";
	private static final String[] FIELDS = {"LABEL","ICON","URL"};

	@Override
	public String getTableName() {
		return TABLE;
	}
	
	public List<MenuItem> getItensByMenu(Menu menu) {
		List<MenuItem> itens = new ArrayList<>();
		String sql = "SELECT * FROM MENU_ITEM WHERE ID_MENU = " + menu.getId();
		MenuItemDAO menuItemDao = new MenuItemDAO();
		try {
			ResultSet rs = super.getStatement().executeQuery(sql);
			while (rs.next()) {
				itens.add(menuItemDao.popular(rs));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return itens;
	}

	@Override
	public Menu popular(ResultSet rs) throws SQLException {
		Menu menu = new Menu();
		
		menu.setId(rs.getInt("ID"));
		menu.setLabel(rs.getString("LABEL"));
		menu.setIcon(rs.getString("ICON"));
		menu.setUrl(rs.getString("URL"));
		
		return menu;
	}

	@Override
	public PreparedStatement preparePersistStatement(Menu entidade, Boolean isUpdate) throws SQLException {
		PreparedStatement ps = super.getPreparedStatement(
				super.getInsertClause(FIELDS.length));
		ps.setString(1, entidade.getLabel());
		ps.setString(2, entidade.getIcon());
		ps.setString(3, entidade.getUrl());
		if (isUpdate) {
			ps.setInt(4, entidade.getId());
		}
		return ps;
	}

}
