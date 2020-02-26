package com.carlesramos.pm_exercicicartes.clasesaux;


import com.carlesramos.pm_exercicicartes.model.Cartas;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JugadaCpu implements Serializable {

	@SerializedName("carta")
	private Cartas carta;
	@SerializedName("caracteristica")
	private String caracteristica;
	@SerializedName("ganadasCPU")
	private int ganadasCPU;
	@SerializedName("ganadasPlayer")
	private int ganadasPlayer;
	
	public JugadaCpu(Cartas carta, String caracteristica, int ganadasCPU, int ganadasPlayer) {
		super();
		this.carta = carta;
		this.caracteristica = caracteristica;
		this.ganadasCPU = ganadasCPU;
		this.ganadasPlayer = ganadasPlayer;
	}

	public Cartas getCarta() {
		return carta;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCarta(Cartas carta) {
		this.carta = carta;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public int getGanadasCPU() {
		return ganadasCPU;
	}

	public int getGanadasPlayer() {
		return ganadasPlayer;
	}
}
