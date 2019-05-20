package examenUnidad1;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

import utilidades.*;


public class Interfaz extends JFrame {

	JButton btnInicia;
	JPanel panelTinaco,panelLabels,Tinaco;
	Vector <Galones> tinaco;
	
	Interfaz(){
		super("Mauricio García Rubio");
		listaTinaco();
		hazInterfaz();
		
	}
	
	public void hazInterfaz(){
		setSize(400,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		btnInicia = new JButton("Inicia Simulacion");
		add(btnInicia,BorderLayout.NORTH);
		panelPrincipal();
	}
	
	public void panelPrincipal(){
		panelTinaco = new JPanel(new GridLayout(0,2));
		hazPnlLbls();
		tinacoInterfaz();
		add(panelTinaco);
	}
	
	public void hazPnlLbls(){
		panelLabels = new JPanel();
		panelLabels.setLayout(new GridLayout(0,1));
		
		int limInferior = 391;
		for(int i=400; i>0; i-=10){
			panelLabels.add(new JLabel(limInferior+"-"+i,SwingConstants.LEFT));
			limInferior-=10;
		}
		panelTinaco.add(panelLabels,BorderLayout.WEST);
	}
	
	public void tinacoInterfaz(){
		Tinaco = new JPanel();
		Tinaco.setLayout(new GridLayout(0,1,0,0));
		for(int i=40; i<=0 ; i-- ){
			Tinaco.add(tinaco.get(i));
		}
		panelTinaco.add(Tinaco,BorderLayout.EAST);
	}
	
	
	public void listaTinaco(){
		tinaco = new Vector<Galones>();
		for(int i=1; i<41 ; i++ ){
			tinaco.add(new Galones(i));
		}
	}
	
}
