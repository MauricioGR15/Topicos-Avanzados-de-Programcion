package JuegoPares;

import utilidades.*;
import java.awt.Color;
import javax.swing.JButton;

	public class Imagenes extends JButton{

		String nombre;
		
		public Imagenes(String nombre) {
			setIcon(Rutinas.AjustarImagen("pares-img/football.png", 80, 80));
			this.nombre= nombre;
			setDisabledIcon(Rutinas.AjustarImagen(nombre, 80, 80));
			setRolloverIcon(Rutinas.AjustarImagen("pares-img/inverse.png", 80, 80));
			setBackground(Color.WHITE);
		}
		
	}
