package examenUnidad1;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import utilidades.*;
import javax.swing.Timer;

public class Escuchadores implements ActionListener{
	
	Interfaz interfaz;
	Timer T;
	int agua;
	int capActual;
	Random rnd = new Random();
	Galones aux; 
	boolean band = true;
	int sobra;
	
	public Escuchadores (Interfaz interfaz){
		this.interfaz = interfaz;
		interfaz.btnInicia.addActionListener(this);
	}

	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == interfaz.btnInicia){
			T = new Timer(300,this);
			T.start();
			agua = 0;
			capActual=0;
			aux = interfaz.tinaco.get(1);
			interfaz.btnInicia.setEnabled(false);
			return;
		}
		metodoMontecarlo();
		
		
		
		if(agua>380){
//			extraerAgua();
		}
	}
	
	public void recibirAgua(int gals){
		
		
		
	}
	
	public void extraerAgua(int gals){

	}
	
	public void metodoMontecarlo(){
		double aleatorio = rnd.nextDouble();
		int aguaX = Rutinas.nextInt(1, 10);
		if(aleatorio < 0.5){
			recibirAgua(aguaX);
			agua+=aguaX;
			System.out.println("echó agua");
		}
			
		if(aleatorio > 0.5 && agua > 40){	
			extraerAgua(aguaX);
			agua-=aguaX;
			System.out.println("sacó agua");
		}
		System.out.println("Agua >>> " + agua);
	}

}
