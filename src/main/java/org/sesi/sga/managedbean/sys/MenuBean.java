package org.sesi.sga.managedbean.sys;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.sesi.sga.model.sys.Menu;
import org.sesi.sga.model.sys.MenuItem;
import org.sesi.sga.service.sys.MenuService;

@Named
@RequestScoped
public class MenuBean implements Serializable {
	private static final long serialVersionUID = 2200773864308155125L;
	private MenuService service;
	private List<Menu> menus;
	private List<MenuItem> menuItens;
	private MenuModel model = new DefaultMenuModel();

	public MenuBean() {
		this.service = new MenuService();
	}

	@PostConstruct
	public void init() {
		try {
			this.menus = service.getAll("LABEL");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Menu menu : this.menus) {
			menu.setItensMenu(service.getItensByMenu(menu));
			addMenu(menu);
		}
	}

	public DefaultSubMenu addMenu(Menu menu) {
		DefaultSubMenu theMenu = new DefaultSubMenu();
		theMenu.setLabel(menu.getLabel());
		theMenu.setIcon(menu.getIcon());
		for (MenuItem item : menu.getItensMenu()) {
			DefaultMenuItem mi = new DefaultMenuItem();
			mi.setUrl(menu.getUrl());
			mi.setValue(item.getLabel());
			mi.setUrl(item.getUrl());
			mi.setIcon(item.getIcon());
			theMenu.getElements().add(mi);
		}
		model.getElements().add(theMenu);
		
		return theMenu;
	}

	public MenuModel getModel() {
		return model;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public List<MenuItem> getMenuItens() {
		return menuItens;
	}

	public void setMenuItens(List<MenuItem> menuItens) {
		this.menuItens = menuItens;
	}

}
