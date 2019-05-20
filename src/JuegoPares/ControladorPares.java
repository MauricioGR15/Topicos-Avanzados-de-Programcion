package JuegoPares;

import java.awt.*;
import java.awt.event.*;

public class ControladorPares implements ActionListener{

	VistaPares vista;
//	ModeloPares modelo;
	Imagenes auxImg,auxImg2;
	int cont = 0;
	int contAciertos = 0;
	
	public ControladorPares(VistaPares vista) {
		this.vista = vista;
//		this.modelo = modelo;
	}


	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == vista.btnReiniciar) {
			vista.rehacerPanel();
			vista.conectaControlador(this);
			cont = contAciertos = 0;
			auxImg2 = null;
			return;
		}

		if(evt.getSource() instanceof Imagenes) {
			auxImg = (Imagenes) evt.getSource();
			auxImg.setEnabled(false);
			System.out.println("Llego " + auxImg.nombre );
			cont++;
			
			if(cont>1 && auxImg.nombre.compareTo(auxImg2.nombre) == 0) {
				contAciertos+=1;
				cont = 0;
				if(contAciertos== vista.numCartas/2)
					vista.mensajeFinalizado();
				return;
			}
			auxImg.update(auxImg.getGraphics());
			if(cont %2 == 0) {
				try {
					Thread.sleep(900);
				}catch(Exception e) {}
				auxImg.setEnabled(true);
				auxImg2.setEnabled(true);
				cont = 0;
			}
			auxImg2 = auxImg;
		}
		


	}

}
