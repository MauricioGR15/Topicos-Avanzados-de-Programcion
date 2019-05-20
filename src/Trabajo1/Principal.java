package Trabajo1;

import java.util.Scanner;

public class Principal {
	
	static int Criterio = 1;
	
	
	public static void main(String[]a) {
		
		Scanner sc = new Scanner(System.in);
		ListaMetodos lista = new ListaMetodos();
		ListaMetodos.ListaPersonas.InsertaOrdenado(new Persona("Andrea",19,167));
		ListaMetodos.ListaPersonas.InsertaOrdenado(new Persona("Tony",21,185));
		ListaMetodos.ListaPersonas.InsertaOrdenado(new Persona("Xavier",23,170));
		ListaMetodos.ListaPersonas.InsertaOrdenado(new Persona("Yolanda",19,200));
		ListaMetodos.ListaPersonas.InsertaOrdenado(new Persona("Brenda",18,165));
		
		int opt = 100;
		while(opt != 0) {
			System.out.println("1.- Capturar \n2.- Criterio \n3.- Consultar \n0.- Salir");
			opt = new Scanner(System.in).nextInt();
			
			switch(opt) {
			case 1: //
				System.out.print("Nombre: ");
				String nom = sc.next();
				System.out.print("Edad: ");
				int edad = sc.nextInt();
				System.out.print("Estatura: ");
				int est = sc.nextInt();
				ListaMetodos.ListaPersonas.InsertaOrdenado(new Persona(nom,edad,est));
				break;
				
				
			case 2: //Criterio
				System.out.println(">>> Seleccionar nuevo criterio"
						+ "\n1 - Nombre \n2 - Edad \n3 - Estatura \n4 - Edad/Estatura/Nombre");
				int opc = sc.nextInt();
				Criterio = opc;	//Controla criterio
				lista.QuickSort(0,ListaMetodos.ListaPersonas.Length()-1);
				break;
				
			case 3:	//Consulta
				lista.consulta();
				break;
				
			}
		}
		
		sc.close();
	}

}
