package JuegoPares;

public class MainPares {
	
public static void main(String [] a) {
		
		VistaPares vista = new VistaPares();
		ControladorPares controlador= new ControladorPares(vista);
		vista.conectaControlador(controlador);
		vista.vistaVisible();
	}

}
