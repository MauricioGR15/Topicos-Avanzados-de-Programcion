package AppContabilidad;

import utilidades.Rutinas;

public class Poliza {

	private int noPol;
	private String nombrePol;
	private double importe;
	private double suma;
	public static final int TAM = 4+8+22;
	
	public Poliza(int noPol, String nombrePol) {
		this.noPol = noPol;
		this.nombrePol = Rutinas.PonBlancos(nombrePol, 50);
		this.setImporte(0);
	}
	
	public void calcularSuma(char tipo, double cant) {
		switch(tipo) {
		case 'C':
			suma+=cant;
			break;
		case 'A':
			suma-=cant;
			break;
		}
	}
	
	public void sumaImporte(double cant) {
		setImporte(getImporte() + cant);
	}
	

	public double getSuma() {
		return suma;
	}

	public void setSuma(double importe) {
		this.suma = importe;
	}

	public int getNoPol() {
		return noPol;
	}

	public void setNoPol(int noPol) {
		this.noPol = noPol;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getNombrePol() {
		return nombrePol;
	}

	public void setNombrePol(String nombrePol) {
		this.nombrePol = nombrePol;
	}

	
}
