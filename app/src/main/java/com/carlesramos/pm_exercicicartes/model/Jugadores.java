package com.carlesramos.pm_exercicicartes.model;
// Generated 14 feb. 2020 17:54:49 by Hibernate Tools 5.2.12.Final

import com.google.gson.annotations.SerializedName;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Jugadores generated by hbm2java
 */
public class Jugadores implements java.io.Serializable {

	@SerializedName("idJugador")
	private Integer idJugador;
	@SerializedName("nickName")
	private String nickName;
	@SerializedName("email")
	private String email;
	@SerializedName("password")
	private String password;
	@SerializedName("ganadas")
	private Integer ganadas;
	@SerializedName("empatadas")
	private Integer empatadas;
	@SerializedName("perdidas")
	private Integer perdidas;

	public Jugadores() {
		ganadas = 0;
		empatadas = 0;
		perdidas = 0;
	}

	public Jugadores(String nickName, String email, String password, Integer ganadas, Integer empatadas,
			Integer perdidas) {
		this.nickName = nickName;
		this.email = email;
		this.password = obtenerSHA1(nickName, password);
		this.ganadas = ganadas;
		this.empatadas = empatadas;
		this.perdidas = perdidas;
	}

	public Integer getIdJugador() {
		return this.idJugador;
	}

	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getGanadas() {
		return this.ganadas;
	}

	public void setGanadas(Integer ganadas) {
		this.ganadas = ganadas;
	}

	public Integer getEmpatadas() {
		return this.empatadas;
	}

	public void setEmpatadas(Integer empatadas) {
		this.empatadas = empatadas;
	}

	public Integer getPerdidas() {
		return this.perdidas;
	}

	public void setPerdidas(Integer perdidas) {
		this.perdidas = perdidas;
	}

	private String obtenerSHA1(String nickName, String password) {
		String sha1 = null;
		StringBuilder sb = new StringBuilder();
		sb.append(nickName);
		sb.append(password);
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(sb.toString().getBytes("UTF-8"));
			sha1 = String.format("%040x", new BigInteger(1,digest.digest()));
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return sha1;
	}

}
