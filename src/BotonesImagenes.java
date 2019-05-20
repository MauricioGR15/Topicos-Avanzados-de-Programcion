import javax.swing.*;

import java.util.*;

import java.awt.event.*;
import java.awt.*;
public class BotonesImagenes extends JFrame implements ActionListener {
    JButton [] VB;
    ImageIcon Imagen;
    int Cont=0;
	public BotonesImagenes(){
		super("Manejo imagenes en botones");
		HazInterfaz();
		HazEscuchas();
	}
	private void HazInterfaz(){
		setLayout(new GridLayout(0,4,5,5));
		VB=new JButton[30];
		
	
		Imagen=Rutinas.AjustarImagen("Leon.jpg", 50, 50);
		for(int i=0 ; i<VB.length ; i++){
			VB[i]=new JButton(Imagen);
			add(VB[i]);
			VB[i].setRolloverIcon(Rutinas.AjustarImagen("Avion.png", 50,50));
			VB[i].setPressedIcon(Rutinas.AjustarImagen("Maquina.gif",50,50));
		}
		
		
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
	private void HazEscuchas(){
		for(int i=0 ; i<VB.length ; i++)
			VB[i].addActionListener(this);
	}
	public static void main(String[] args) {
		new BotonesImagenes();
	}
	@Override
	public void actionPerformed(ActionEvent Evt) {
		JButton Btn=(JButton) Evt.getSource();
		
		//Btn.setEnabled(false);
		//Btn.setDisabledIcon(Imagen);
		Btn.setIcon(Rutinas.AjustarImagen("Caballos.jpg", 50, 50));
		Btn.update(Btn.getGraphics());
		//update(getGraphics());
		Cont++;
		if(Cont%2==0){
			try{
				Thread.sleep(1000);
			} catch (Exception e){
				
			}
		}
	}

}
