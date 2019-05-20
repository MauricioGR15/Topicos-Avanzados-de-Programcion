import java.awt.event.*;

import javax.swing.*;
import java.awt.*;

public class TamanioBoton extends JFrame {
	 private JButton boton;
	 public TamanioBoton() 	 {
		 super("Manejo de tmaño de botón");
		 HazInterfaz();
//		 HazEscuchas();
	 }
	 private void HazInterfaz(){
			setLayout(new FlowLayout());

			   add(new JLabel("Este botón aumenta 50 pixeles por dimensión"));
			    boton = new JButton("Boton");
//		        boton.setSize(150,100);
		        boton. setPreferredSize(new Dimension(80,85));

			    add(boton);
			    setSize(350, 500);
			    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    setLocationRelativeTo(null);
			    setVisible(true);
	 }
	 private void HazEscuchas(){
	      boton.addActionListener(new ActionListener()
	      {   public void actionPerformed(ActionEvent evt){  
		    boton.setSize(boton.getWidth() + 50, boton.getHeight() + 50);
		    boton.update(boton.getGraphics());
		    //update(getGraphics());
	      	}
	       });
	 }
	 public static void main(String[] args)
	 {
		 new TamanioBoton();
	 }
}
