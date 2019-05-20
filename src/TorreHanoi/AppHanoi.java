package TorreHanoi;

public class AppHanoi {

	public AppHanoi(){
		ModeloHanoi modelo = new ModeloHanoi();
		VistaHanoi vista = new VistaHanoi();
		Controlador controlador = new Controlador(modelo,vista);
		controlador.view.setVisible(true);
	}
	
	public static void main(String args []){
		new AppHanoi();
	}
		
}
