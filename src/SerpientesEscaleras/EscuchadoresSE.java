package SerpientesEscaleras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import utilidades.*;
import java.awt.*;

public class EscuchadoresSE implements ActionListener{

	private VistaSE view;
	private LogicaSE model;
	private int noJugador=1;
	private int numDados,contDados=0,avanza;
	private int jugEnTurno;
	private Timer T,T2;
	private Jugador jugAux;
	boolean bandera;

	public EscuchadoresSE(LogicaSE modelo, VistaSE vista) {
		model = modelo;
		view = vista;
		hazEscuchadores();
		noPlayers(view.numeroJugadores);
	}

	private void hazEscuchadores() {
		view.btnDados.addActionListener(this);
	}

	public void actionPerformed(ActionEvent Evt) {
		jugAux = model.jugadores.get(jugEnTurno);
		
		if(Evt.getSource() == view.btnDados) {
			view.btnDados.setEnabled(false);
			view.lblimgJug = new JLabel(jugAux.imagen);
			view.panelTurno.update(view.panelTurno.getGraphics());
			avanza = 0;
			contDados=0;
			T = new Timer(50,this);
			T.start();
			return;
		}
		
		
		if(Evt.getSource() == T) {
			numDados = Rutinas.nextInt(2, 12);
			view.lblDados.setText(numDados+"");
			contDados++;
			if(contDados==10) {
				T.stop();
				view.btnDados.setEnabled(true);
				T2 = new Timer(500,this);
				T2.start();
				avanza = calculaPosiciones(jugAux);
			}
			return;
		}
		
		NodoDL<Casilla> nodoAux= jugAux.posicion;
		Casilla casAux = nodoAux.Info;
		if(Evt.getSource() == T2) {
			
			jugAux.dibujaJugador();
			if(nodoAux.getSig()==null) {
				bandera = true;
			}
			if(bandera)
				jugAux.retrocedeJugador();
			else
				jugAux.avanzaJugador();
			if(casAux.getCasilla()>1)
				nodoAux.getAnt().Info.update(nodoAux.getAnt().Info.getGraphics());
			if(casAux.getCasilla() == avanza) {
				bandera=false;
				T2.stop();
				tipoCasilla(casAux);
				casAux.update(casAux.getGraphics());
				if(casAux.getCasilla() == 100) {
					JOptionPane.showMessageDialog( view, "El jugador " +jugEnTurno+1+ " ha ganado!!!");
					reiniciaJuego();
					return;
				}
				casAux.update(casAux.getGraphics());
				jugEnTurno++;
				view.ActualizaTurno(jugEnTurno);
				view.panelTurno.update(view.panelTurno.getGraphics());
				if(jugEnTurno==model.jugadores.size()) 
					jugEnTurno=0;
			}
			return;
		}

	}

	public void noPlayers(int num) { 
		for(int i=0; i<num;i++) {
			ImageIcon img = model.imgJugadores.get(noJugador-1);
			Jugador nuevo = new Jugador(img,noJugador,LogicaSE.tablero.getFrente());
			model.jugadores.add(nuevo);
			noJugador++;
			view.panelJugadores.add(nuevo.lblImagen);
		}
	}
	
	public void tipoCasilla(Casilla casAux) {
		int pos = casAux.getPosiciones(); 
		char c = casAux.getTipoCasilla();
		switch(c) {
		case 'E':
			for(int i=0; i<pos-1;i++)
				jugAux.avanzaJugador();
			jugAux.dibujaJugador();
			break;
		case 'S':
			for(int i=0; i<pos-1;i++)
				jugAux.retrocedeJugador();
			jugAux.dibujaJugador();
			break;
		case 'N': break;
		case 'T': break;
		}
	}

	
	public void reiniciaJuego() {
		noJugador = 1;
		numDados = 0;
		contDados = 0;
		jugEnTurno = 0;
		view.reiniciarJuego();
		noPlayers(view.numeroJugadores);
	}

	public int calculaPosiciones(Jugador jugTurno) {
		return jugTurno.posicion.Info.getCasilla() + numDados-1;
	}

	public void vistaVisible() {
		view.setVisible(true);
	}

}
