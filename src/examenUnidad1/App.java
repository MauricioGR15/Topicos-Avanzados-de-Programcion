package examenUnidad1;

//Mauricio Garc�a Rubio
//T�picos Avanazados de Programaci�n
//9-10

public class App {
	

	public static void main(String[] args) {
		Interfaz interfaz = new Interfaz();
		Escuchadores escuchas = new Escuchadores(interfaz);
		escuchas.interfaz.setVisible(true);

	}

}
