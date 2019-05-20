package SerpientesEscaleras;

public class MainSE {

	public static void main(String[] args) {
		
		LogicaSE logica = new LogicaSE();
		VistaSE vista = new VistaSE();
		EscuchadoresSE controller = new EscuchadoresSE(logica,vista);
		controller.vistaVisible();
	}

}
