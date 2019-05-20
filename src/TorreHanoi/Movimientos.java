package TorreHanoi;

public class Movimientos {
	
	private Torre A,B,C;
	private int posX=200;
	private int limSubida=150;
	
	public Movimientos() {
		A = new Torre(posX);
		B = new Torre(posX+200);
		C = new Torre(posX+400);
		A.setAltura(A.getAltura()-(VistaHanoi.hDisco * VistaHanoi.noDiscos));
	}
	
	public int getDesplazamientoX(Torre tI, Torre tF) {
		if(tI.getTorre_x() < tF.getTorre_x())
			return tF.getTorre_x();
		return tI.getTorre_x();
	}
	
	public int getLimSubida() {
		return limSubida;
	}
	
	public int getLimBajada(Torre tF) {
		return tF.getAltura();
	}
	
	public void discoSube(Disco d) {
		d.setY(d.getY()-Controlador.px);
	}
	
	public void discoBaja(Disco d) {
		d.setY(d.getY()+Controlador.px);
	}
	
	public void discoDesplazamiento(Disco d, Torre ti, Torre tf) {
		if(ti.getTorre_x() < tf.getTorre_x()) 
			d.setX(d.getX()+Controlador.px);
		else
			d.setX(d.getX()-Controlador.px);
	}
		
	public Torre getTorre(char t) {
		if(t == 'A')
			return A;
		if(t == 'B')
			return B;
		return C;
	}

}
