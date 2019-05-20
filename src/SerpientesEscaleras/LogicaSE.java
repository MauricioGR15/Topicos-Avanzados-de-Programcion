package SerpientesEscaleras;

import utilidades.*;
import java.util.*;

import javax.swing.ImageIcon;

public class LogicaSE {

	static ListaDL<Casilla>tablero;
	Vector<Jugador> jugadores = new Vector<Jugador>();
	Vector<ImageIcon> imgJugadores;

	public LogicaSE() {
		creaTablero();
		imagenesJugadores();
	}

	public void creaTablero() {
		tablero = new ListaDL<Casilla>();
		for(int i=1; i<101; i++) {
			tablero.InsertaFin(new Casilla(i));
		}
		serpientes();
		escaleras();
	}

	public void serpientes() {
		NodoDL<Casilla> aux,aux2;
		int num,t;

		for(int i=0; i<5 ; i++) {
			num = Rutinas.nextInt(30, 95);
			aux = ObtenerNodo(num);

			if(aux.Info.getTipoCasilla() != 'N') {
				i--;
				continue;
			}
			t = Rutinas.nextInt(10, 20);
			aux2 = ObtenerNodo(num-t);
			if(aux2.Info.getTipoCasilla() != 'N') {
				i--;
				continue;
			}
			aux.Info.setTipoCasilla('S');
			aux.Info.setPosiciones(t);	
			aux2.Info.setTipoCasilla('T');
		}
	}

	public void escaleras() {
		NodoDL<Casilla> aux,aux2;
		int num,t;

		for(int i=0; i<5; i++) {
			num = Rutinas.nextInt(15,70);
			aux = ObtenerNodo(num);
			if(aux.Info.getTipoCasilla() != 'N') {
				i--;
				continue;
			}
			t = Rutinas.nextInt(10, 20);
			aux2 = ObtenerNodo(num+t);
			if(aux2.Info.getTipoCasilla() != 'N') {
				i--;
				continue;
			}
			aux.Info.setTipoCasilla('E');
			aux.Info.setPosiciones(t);
			aux2.Info.setTipoCasilla('T');
		}
	}
	
	private void imagenesJugadores() {
		imgJugadores = new Vector<ImageIcon>(10);
		for(int i =1; i<11;i++) {
			imgJugadores.add(Rutinas.AjustarImagen("se-imagenes/j"+i+".png", 20, 20));
		}
	}

	public static NodoDL<Casilla> ObtenerNodo(int pos){
		NodoDL<Casilla> aux = tablero.getFrente();
		for(int i = 1; i<pos;i++,aux=aux.getSig());
		return aux;
	}

}
