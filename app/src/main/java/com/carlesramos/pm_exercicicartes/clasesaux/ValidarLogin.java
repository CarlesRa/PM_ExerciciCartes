package com.carlesramos.pm_exercicicartes.clasesaux;

public class ValidarLogin {
	
	private String idSession;
	private int idJugador;
	
	public ValidarLogin(String idSession, int idJugador) {
		this.idSession = idSession;
		this.idJugador = idJugador;
	}
	
	public String getIdSession() {
		return idSession;
	}
	
	public int getIdJugador() {
		return idJugador;
	}
	

	
	
}
