package Trabajo1;

import utilidades.Rutinas;

public class Persona {

	private String nombre;
	private int edad, estatura;
	
	public Persona(String nombre, int edad, int estatura) {
		this.nombre = nombre;
		this.edad = edad;
		this.estatura = estatura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getEstatura() {
		return estatura;
	}

	public void setEstatura(int estatura) {
		this.estatura = estatura;
	}
	
	public String toString() {
		switch(Principal.Criterio) {
			case 2: return Rutinas.PonCeros(edad, 5);
			case 3: return Rutinas.PonCeros(estatura, 5);
			case 4: return Rutinas.PonCeros(edad, 5) + Rutinas.PonCeros(edad, 6) + Rutinas.PonBlancos(nombre, 20);
			default: return Rutinas.PonBlancos(nombre, 20);
		}
	
	}
	
	
}
