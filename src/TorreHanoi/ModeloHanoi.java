package TorreHanoi;

import java.util.*;

import utilidades.Rutinas;

public class ModeloHanoi {
	
	Vector<String> movimientos = new Vector <String>();
	
	private int Contador;
	
	public void Hanoi(char Inicial,char Central,char Final,int N) {
	   	  if(N==1) {
	         Contador++;
	   	  	 movimientos.add(Rutinas.PonCeros(N, 3)+"" + Inicial + Final);
	   	  }
	   	  else {
	   	  	Hanoi(Inicial,Final,Central,N-1);
	        Contador++;
	   	  	movimientos.add(Rutinas.PonCeros(N, 3)+"" + Inicial + Final);
	   	  	Hanoi(Central,Inicial,Final,N-1);
	   	  }
	   }
	
	public void muestraMov() {
		for(int i=0;i<movimientos.size();i++)
			System.out.println(movimientos.get(i));
	}

}
