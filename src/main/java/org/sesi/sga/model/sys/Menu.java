package org.sesi.sga.model.sys;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {
	private static final long serialVersionUID = -3833796931064157924L;
	private Integer id;
	private String label;
	private String icon;
	private String url;
	private List<MenuItem> itensMenu;
	
	public Menu() {
		super();
	}
	
	public Menu(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<MenuItem> getItensMenu() {
		return itensMenu;
	}

	public void setItensMenu(List<MenuItem> itensMenu) {
		this.itensMenu = itensMenu;
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
