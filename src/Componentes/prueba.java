package Componentes;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class prueba extends JFrame {

	public static void main(String[] args) {
		new prueba();
	}
	
	public prueba() {
		setSize(700, 500);
		setLocationRelativeTo(null);
		
		JComboBoxEMC emc = new JComboBoxEMC();
//		emc.getSelected(lugar)
		
		add(new JComboBoxEMC("Campeche"), BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
