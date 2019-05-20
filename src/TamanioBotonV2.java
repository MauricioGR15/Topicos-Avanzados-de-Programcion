
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
public class TamanioBotonV2 extends JFrame implements ActionListener {
	 private JButton boton, boton1;
	 int Cont=0;
	 public TamanioBotonV2() 	 {
		 super("Manejo de tmaño de botón");
		 HazInterfaz();
		 HazEscuchas();
	 }
	 private void HazInterfaz(){
			setLayout(new FlowLayout());

			   add(new JLabel("Este botón aumenta 50 pixeles por dimensión"));
			    boton = new JButton("Boton");
//		        boton.setSize(150,100);
		        boton. setPreferredSize(new Dimension(80,85));

			    add(boton);
			    boton1 = new JButton("Boton1");
		        boton1. setPreferredSize(new Dimension(80,85));
		        add(boton1);
			    setSize(350, 500);
			    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    setLocationRelativeTo(null);
			    setVisible(true);
	 }
	 private void HazEscuchas(){
	      boton.addActionListener(this);
	      boton1.addActionListener(this);
	 }
	 public static void main(String[] args)
	 {
		 new TamanioBotonV2();
	 }
	
	public void actionPerformed(ActionEvent Evt) {
		JButton Btn=(JButton) Evt.getSource();
			if(++Cont%2==0)
				Btn.setBackground(Color.BLUE);
			else
				Btn.setBackground(Color.YELLOW);
			Btn.setSize(Btn.getWidth() + 50, Btn.getHeight() + 50);

		
	}
}
