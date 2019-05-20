package examenUnidad1;

import java.awt.Color;
import javax.swing.*;

public class Galones extends JPanel{

	
	int modulo;
	boolean lleno;

	Galones(int modulo){
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.modulo = modulo;
		lleno = false;
		setBackground(Color.GREEN);
	}


	



}
