package Componentes;

import javax.swing.JFrame;

public class pruebaComboFiltro extends JFrame{

	public pruebaComboFiltro() {
		FilterTextField combo = new FilterTextField();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(combo);
		
		pack();
		setVisible(true);
		
	}
	
	
	public static void main(String a[]) {
		new pruebaComboFiltro();
		}
}
