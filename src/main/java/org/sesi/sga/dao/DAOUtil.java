package org.sesi.sga.dao;

public final class DAOUtil {
	private static final String SABADO = "Sábado";
	private static final String DOMINGO = "Domingo";
	
	private DAOUtil() {}
	
	public static Boolean diaSemanaTemHifen(String nomeDia) {
		if (nomeDia != null && 
				!nomeDia.equals(SABADO) && !nomeDia.equals(DOMINGO)) {
			return nomeDia.contains("-");
		}
		return false;
	}
}
