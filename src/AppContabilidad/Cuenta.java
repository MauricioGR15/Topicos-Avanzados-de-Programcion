package AppContabilidad;

import utilidades.Rutinas;

public class Cuenta {
	
	private String nombre;
	private String numCue;
	private double Saldo;
	private double Cargo;
	private double Abono;
	private boolean activo;
	static final int TAM = 22+11+8+8+8+1; 
	static final int POSC = 22+11+8;
	static final int POSA = 22+11+8+8;
	static final int TAM2 = 15;
	
	public Cuenta(String numCue,String nombre,double Saldo) {
		this.nombre = Rutinas.PonBlancos(nombre, 20);
		this.numCue = Rutinas.PonBlancos(numCue, 9);
		this.Saldo = Saldo;
		Cargo = 0;
		Abono = 0;
		activo = true;
	}
	
	public Cuenta(String numCue,String nombre,double Saldo, double cargo,double abono,boolean activo) {
		this.nombre = Rutinas.PonBlancos(nombre, 20);
		this.numCue = Rutinas.PonBlancos(numCue, 9);
		this.Saldo = Saldo;
		Cargo = cargo;
		Abono = abono;
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumCue() {
		return numCue;
	}

	public void setNumCue(String numCue) {
		this.numCue = numCue;
	}

	public double getSaldo() {
		return Saldo;
	}

	public void setSaldo(double saldo) {
		Saldo = saldo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public double getCargo() {
		return Cargo;
	}

	public void setCargo(double cargo) {
		Cargo = cargo;
	}

	public double getAbono() {
		return Abono;
	}

	public void setAbono(double abono) {
		Abono = abono;
	}

}
