package Aeropuerto;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import javax.swing.JFrame;
import utilidades.Elemento;
import utilidades.Rutinas;
import utilidades.Semaforo;

public class TorreControl extends JFrame{

	Avion aviones[];
	Semaforo S;
	Graphics g;
	int turno = 0;
	Image bf, bg = Rutinas.AjustarImagen("airport-img/Pista.png", 700, 400).getImage();
	final int time = 5;
	
	public TorreControl() {
		S = new Semaforo(1);
		hacerAviones();
		hazInterfaz();	
		
		for(int i=0; i<aviones.length; i++) 
			aviones[i].start();
		
		System.out.println(turno);
		dibujaAviones();
		for(int i=0; i<aviones.length; i++) 
			System.out.println("El avión " + i + " intentó: " + aviones[i].intentos);
	}
	
	public void hazInterfaz() {
		setSize(700,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		
		bf = createImage(getWidth(),getHeight());
		g = bf.getGraphics();
		
		pinta();
	}
	
	public void hacerAviones() {
		aviones = new Avion[Rutinas.nextInt(3, 4)];
		Vector<Integer> turnos = new Vector<Integer>();
		for(int i=0;i<aviones.length;i++) 
			turnos.add(i);
		
		for(int i=0;i<aviones.length;i++) {
			int pos = Rutinas.nextInt(0, turnos.size()-1);
			int turno = turnos.get(pos);
			turnos.remove(pos);
			aviones[i] = new Avion(turno,S,this);
		}
	}
	
	public boolean hayaVivos() {
		for(int i=0;i<aviones.length;i++) {
			if(aviones[i].isAlive())
				return true;
		}
		return false;
	}
	
	public boolean hayEsperando() {
		for(int i=0;i<aviones.length;i++) {
			if(aviones[i].getState() == Thread.State.WAITING) {
				System.out.println("Si hay esperando");
				return true;
			}
		}
		return false;
	}
	
	public void pinta() {
		super.paint(g);
		g.drawImage(bg, 0, 0, 700, 400, null);
		
		for(int i = 0; i<aviones.length; i++) 
			if(aviones[i].isAlive())
				aviones[i].dibujarAvion(g);
		
		repaint();
	}
	
	public void dibujaAviones() {
		while(hayaVivos()) {
			pinta();
			try {
				Thread.sleep(time);
//				System.out.println("se duerme");
			}catch(Exception e) {}
		}
		System.out.println("Acabo");
	}
	
	public void paint(Graphics g) {
		g.drawImage(bf,0,0,getWidth(),getHeight(),null);
	}
	
	
	public static void main(String args[]) {
		new TorreControl();
	}
	
}