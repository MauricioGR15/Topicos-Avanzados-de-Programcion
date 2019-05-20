package TorreHanoi;

import java.awt.*;
import javax.swing.*;

import utilidades.Rutinas;

public class Canvass extends Canvas{
	
	Image backbuffer;
	int alturaPalo = 175;
	Graphics g;
	Image fondo = Rutinas.AjustarImagen("hanoi-img/bg.jpg", 800, 400).getImage();
	Image bamboo = Rutinas.AjustarImagen("hanoi-img/bamboo.png", 100, alturaPalo).getImage();
	private Disco [] d;

	public Canvass(Image img) {
		setBounds(100,100,800,400);
		backbuffer = img;
		g = backbuffer.getGraphics();
	}
	
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(backbuffer,0,0, getWidth(), getHeight(), null);
		
	}
	
	public void dibujaLienzo() {
		super.paint(g);
//		g.drawImage(imagen,pos x, pos y, ancho, alto);
		g.drawImage(fondo,0,0,800,400,null);
		g.drawImage(bamboo, 150, 175,100, alturaPalo, null);
		g.drawImage(bamboo, 350, 175,100, alturaPalo, null);
		g.drawImage(bamboo, 550, 175,100, alturaPalo, null);
		
		if(d != null) {
			for(int i=0; i<d.length;i++) {
				g.setColor(d[i].getColor());
				g.fillRect(d[i].getX(), d[i].getY(), d[i].getW(), d[i].getH());
			}
		}
		repaint();
	}
	
	public void setDiscos(Disco[] d) {
		this.d = d;
	}

}
