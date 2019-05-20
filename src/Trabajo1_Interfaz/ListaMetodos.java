package Trabajo1_Interfaz;

import utilidades.*;

public class ListaMetodos {
	
	static ListaDL<Persona> ListaPersonas = new ListaDL<Persona>();
	
	static void QuickSort() {
		QuickSort(0,ListaPersonas.Length()-1);
	}
	
	static void QuickSort(int izq, int der) {
		NodoDL<Persona> pivote = ObtenerNodo(izq);
		int i = izq;
		int j = der;
		NodoDL<Persona> aux = ObtenerNodo(i), aux2 = ObtenerNodo(j);
		
		Persona auxIntercambio;
		
		while(i<j) {
			while(ObtenerNodo(i).Info.toString().compareTo(pivote.Info.toString()) <= 0 && i < j) {
				i++;
				aux = aux.getSig();
			}
			while(ObtenerNodo(j).Info.toString().compareTo(pivote.Info.toString()) > 0) {
				j--;
				aux2 = aux2.getAnt();
			}
			if(i<j) {
				auxIntercambio = aux.Info;
				aux.Info = aux2.Info;
				aux2.Info = auxIntercambio;
			}
		}
		auxIntercambio=pivote.Info;
		ObtenerNodo(izq).Info = aux2.Info;
		aux2.Info = auxIntercambio;
		
		if(izq < j-1) 
			QuickSort(izq,j-1);
		if(j+1 < der) 
			QuickSort(j+1, der);
		
	}
	
	void consulta() {
		if(ListaPersonas.getFrente() == null) 
			System.out.println("Lista vacia");
		else{
			NodoDL<Persona> aux = ListaPersonas.getFrente();
			System.out.println("Edad: \tEstatura: \tNombre:");
			while(aux!= null) { 
				System.out.println( aux.Info.getEdad() + "\t" + aux.Info.getEstatura() + "\t\t" + aux.Info.getNombre());
				aux = aux.getSig();
			}
		}
	}
	
	static NodoDL<Persona> ObtenerNodo(int pos){
		NodoDL<Persona> aux = ListaPersonas.getFrente();
		for(int i = 0; i<pos;i++,aux=aux.getSig());
		return aux;
	}
	
	
	

}


