package examenUnidad1;

//Mauricio García Rubio
//Tópicos Avanazados de Programación
//9-10

public class App {
	

	public static void main(String[] args) {
		Interfaz interfaz = new Interfaz();
		Escuchadores escuchas = new Escuchadores(interfaz);
		escuchas.interfaz.setVisible(true);

	}

}
