package TorreHanoi;

import java.awt.Color;

import utilidades.Rutinas;

public class Disco{

	private int x,y,w,h;
	private Color color;
	
	public Disco(int x,int y,int w,int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.setH(h);
		color = new Color(Rutinas.nextInt(0, 50),Rutinas.nextInt(0, 50),Rutinas.nextInt(150, 255));
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
	
	
}



