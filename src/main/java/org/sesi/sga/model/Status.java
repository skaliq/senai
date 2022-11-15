package org.sesi.sga.model;

public enum Status {
	A("Ativo"), E("Encerrado"), C("Cancelado");
	
	private String status;
	
	private Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
