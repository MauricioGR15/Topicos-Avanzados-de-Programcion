package utilidades;

public class Aleatorios {

	public static int obtenerAleatorio() {
		int num;
		num = (int) Math.floor(Math.random()*(20-10+1)+10);  // Valor entre M y N, ambos incluidos.
		if(!(num%2 == 0)) 
			num+=1;
		
		return num;
	}
	
}
