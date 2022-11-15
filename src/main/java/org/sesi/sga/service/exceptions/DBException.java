package org.sesi.sga.service.exceptions;

import java.sql.SQLException;

import org.sesi.sga.dao.jdbc.GenericDAOImpl;

public class DBException extends SQLException {
	private static final long serialVersionUID = 1L;

	public DBException() {
		super();
		try {
			GenericDAOImpl.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DBException(String msg) {
		super(msg);
		try {
			GenericDAOImpl.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DBException(Exception ex) {
		super(ex);
		try {
			GenericDAOImpl.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
