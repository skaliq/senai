package org.sesi.sga.model.sys;

import java.io.Serializable;

public class MenuItem implements Serializable {
	private static final long serialVersionUID = -4734222519101535657L;
	private Integer id;
	private String label;
	private String icon;
	private String url;
	private Menu menu;
	
	public MenuItem() {
		super();
	}
	
	public MenuItem(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
