package com.carlesramos.pm_exercicicartes.model;
// Generated 14 feb. 2020 17:54:49 by Hibernate Tools 5.2.12.Final

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Partidas generated by hbm2java
 */
public class Partidas implements java.io.Serializable {

	@SerializedName("idPartida")
	private Integer idPartida;
	@SerializedName("idSession")
	private Integer idSession;
	@SerializedName("jugadorA")
	private Integer jugadorA;
	@SerializedName("jugadorB")
	private Integer jugadorB;
	@SerializedName("ganador")
	private Integer ganador;
	@SerializedName("terminada")
	private Boolean terminada;
	@SerializedName("fecha")
	private Date fecha;

	public Partidas() {
		terminada = false;
	}

	public Partidas(Integer idSession, Integer jugadorA, Integer jugadorB, Integer ganador, Boolean terminada,
			Date fecha) {
		this.idSession = idSession;
		this.jugadorA = jugadorA;
		this.jugadorB = jugadorB;
		this.ganador = ganador;
		this.terminada = terminada;
		this.fecha = fecha;
	}

	public Integer getIdPartida() {
		return this.idPartida;
	}

	public void setIdPartida(Integer idPartida) {
		this.idPartida = idPartida;
	}

	public Integer getIdSession() {
		return this.idSession;
	}

	public void setIdSession(Integer idSession) {
		this.idSession = idSession;
	}

	public Integer getJugadorA() {
		return this.jugadorA;
	}

	public void setJugadorA(Integer jugadorA) {
		this.jugadorA = jugadorA;
	}

	public Integer getJugadorB() {
		return this.jugadorB;
	}

	public void setJugadorB(Integer jugadorB) {
		this.jugadorB = jugadorB;
	}

	public Integer getGanador() {
		return this.ganador;
	}

	public void setGanador(Integer ganador) {
		this.ganador = ganador;
	}

	public Boolean getTerminada() {
		return this.terminada;
	}

	public void setTerminada(Boolean terminada) {
		this.terminada = terminada;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
