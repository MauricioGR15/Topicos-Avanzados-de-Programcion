package SerpientesEscaleras;

import java.awt.*;
import javax.swing.*;

import utilidades.*;

public class Casilla extends JPanel {

	private int Casilla;
	private char TipoCasilla;
	private int Posiciones;
	private JPanel pnlJugadores, panelN;
	private JLabel imgCasilla,noCasilla;

	public Casilla(int nC) {
		Casilla = nC;
		TipoCasilla = 'N';
		Posiciones=0;
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		noCasilla  = new JLabel(Casilla+"",SwingConstants.LEFT);
		panelN = new JPanel(new BorderLayout());
		panelN.setBackground(new Color(200,200,200));
		panelN.add(noCasilla,BorderLayout.WEST);
		add(panelN,BorderLayout.NORTH);
		panelJugadores();
	}

	private void panelJugadores(){
		pnlJugadores = new JPanel();
		pnlJugadores.setBackground(new Color(240,240,240));
		add(pnlJugadores,BorderLayout.CENTER);
	}

	public int getCasilla() {
		return Casilla;
	}

	public void setCasilla(int casilla) {
		Casilla = casilla;
	}

	public char getTipoCasilla() {
		return TipoCasilla;
	}

	public void setTipoCasilla(char tipoCasilla) {
		TipoCasilla = tipoCasilla;
	}

	public int getPosiciones() {
		return Posiciones;
	}

	public void setPosiciones(int posiciones) {
		Posiciones = posiciones;
	}

	public JPanel getPnlJugadores() {
		return pnlJugadores;
	}

	public void setPnlJugadores(JPanel pnlJugadores) {
		this.pnlJugadores = pnlJugadores;
	}

	public JPanel getPanelN() {
		return panelN;
	}

	public void setPanelN(JPanel panelN) {
		this.panelN = panelN;
	}

	public JLabel getImgCasilla() {
		return imgCasilla;
	}

	public void setImgCasilla(JLabel imgCasilla) {
		this.imgCasilla = imgCasilla;
	}

	public JLabel getNoCasilla() {
		return noCasilla;
	}

	public void setNoCasilla(JLabel noCasilla) {
		this.noCasilla = noCasilla;
	}
}

class Jugador {

	ImageIcon imagen;
	JLabel lblImagen;
	private int numJugador;
	NodoDL<Casilla> posicion;

	public Jugador(ImageIcon img, int numJ, NodoDL<Casilla>pos) {
		imagen = img;
		setNumJugador(numJ);
		lblImagen = new JLabel(img);
		posicion = pos;
	}

	public void avanzaJugador() {
		posicion = posicion.getSig();
	}
	
	public void retrocedeJugador() {
		posicion = posicion.getAnt();
	}
	
	//Va a poner la imagen en la casilla actual
	public void dibujaJugador() {
		JPanel aux = posicion.Info.getPnlJugadores();
		aux.add(lblImagen);
		aux.update(aux.getGraphics());
	}

	public int getNumJugador() {
		return numJugador;
	}

	public void setNumJugador(int numJugador) {
		this.numJugador = numJugador;
	}



}
