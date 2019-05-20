package Aeropuerto;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import utilidades.Rutinas;
import utilidades.Semaforo;

public class Avion extends Thread implements ActionListener{
	
	TorreControl torre;
	private int turno;
	private boolean usandoPista, aterrizo, sentido;
	private Image avion= Rutinas.AjustarImagen("airport-img/Avion.png", 30, 20).getImage(); 
	private Image avionInv= Rutinas.AjustarImagen("airport-img/AvionRev.png", 30, 20).getImage();
	private Image avionG= Rutinas.AjustarImagen("airport-img/AvionRev.png", 90, 60).getImage();
	private Semaforo s;
	int AvionX,AvionY;
	int intentos;
	Timer t;
	int time;
	static final int px = 1;
	
	public Avion(int turno, Semaforo sf, TorreControl vista) {
		this.turno = turno;
		this.torre = vista; 
		sentido = true;
		usandoPista = false;
		aterrizo = false;
		AvionX = Rutinas.nextInt(10, 650);
		AvionY = 210 - (turno * 20);
		this.s = sf; 
		time = torre.time;
		t = new Timer(time, this);
		System.out.println("Avion " + turno);
	}
	
	public void run() {
		t.start();
		while(!aterrizo) {
			intentos++;
			s.Espera();
			if(turno == torre.turno) {
				System.out.println("llegaaa");
				aterrizar();
				while(true) {					
					System.out.println(aterrizo);   //Comentado no funciona xd
					if(aterrizo == true) {
						System.out.println("llega");
						try {
							Thread.sleep(Rutinas.nextInt(2000, 3000));
						}catch(Exception e) {e.printStackTrace();}
						
						torre.turno++;
						t.stop();
						break;
					}
				}
				System.out.println("Salio");
				s.Libera();
			}
			s.Libera();
		}
	}
	
	public void dibujarAvion(Graphics g) {
		if(sentido)
			g.drawImage(avion, AvionX, AvionY, 30,20,null);
		else {
			if(!usandoPista)
				g.drawImage(avionInv, AvionX, AvionY, 30, 20, null);
			else 
				g.drawImage(avionG, AvionX, AvionY, 90,60,null);
		}
	}

	public void volarEspera() {
			if(AvionX <= 10) 
				sentido = true;
			if(AvionX >650) {
				sentido = false;
			}
				//checar si es su turno
				// si si es semaforo en espera
				// aumentar turno
				// liberar semaforo hasta que el avion acabe la pista
				

			if(sentido)
				AvionX += px;
			else
				AvionX -= px;
	}

	
	public void aterrizar() {
		if(!usandoPista) {
			AvionX = 560;
			AvionY = 336;
			sentido = false;
			usandoPista = true;
		}

		if(AvionX > 50) {
			AvionX -= px;
			if(AvionX % 10 == 0) {
				//time+=2;
				t.setDelay(time);
			}
		}

		else {
			aterrizo = true;
			System.out.println(aterrizo);
		}
			
	}
	
	
	public void actionPerformed(ActionEvent evt) {
		if(!aterrizo && !usandoPista) {
			volarEspera();
			return;
		}
		
		if(!aterrizo && usandoPista) {
//			System.out.println("AvionX >" + AvionX );
			aterrizar();
			return;
		}
	}

}
