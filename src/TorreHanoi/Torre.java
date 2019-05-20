package TorreHanoi;

public class Torre {

	private int altura;
	private int torre_x;
	
	public Torre(int posX) {
		this.altura = 345;
		setTorre_x(posX);
	}
	
	public void subeAltura() {
		altura -= VistaHanoi.hDisco;
	}
	
	public void bajaAltura() {
		altura += VistaHanoi.hDisco;
	}
	
	public int getAltura() {
		return altura;
	}
	
	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getTorre_x() {
		return torre_x;
	}

	public void setTorre_x(int torre_x) {
		this.torre_x = torre_x;
	}
	
}
