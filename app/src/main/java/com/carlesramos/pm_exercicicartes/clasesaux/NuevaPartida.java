package com.carlesramos.pm_exercicicartes.clasesaux;

import com.carlesramos.pm_exercicicartes.model.Cartas;

import java.io.Serializable;
import java.util.ArrayList;

public class NuevaPartida implements Serializable {
	private ArrayList<Cartas> cartasCliente;
	private int idPartida;
	private int jugadorInicial;
	
	public NuevaPartida(ArrayList<Cartas>cartas, int idPartida, int jugadorInicial) {
		this.cartasCliente = cartas;
		this.idPartida = idPartida;
		this.jugadorInicial = jugadorInicial;
	}
	
	public NuevaPartida(int idPartida, int jugadorInicial) {
		this.idPartida = idPartida;
		this.jugadorInicial = jugadorInicial;
	}

	public int getIdPartida() {
		return idPartida;
	}

	public int getJugadorInicial() {
		return jugadorInicial;
	}	
	
	public ArrayList<Cartas> getCartasCliente(){
		return cartasCliente;
	}
}
