package JuegoPares;

import utilidades.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class VistaPares extends JFrame{

	JPanel panel, panelBtns;
	JButton btnReiniciar;
	int numCartas; 
	String [] imgs ;
	Imagenes [] vB;
	Vector<Integer> vector;
	
	public VistaPares() {
		super("Juego de pares");
		HazInterfaz();
		setSize(600,600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void HazInterfaz() {
		
		setIconImage(new ImageIcon("pares-img/inverse.png").getImage());
		arregloImgs();
		
		//Area SUR para reiniciar el panel de botones
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		btnReiniciar = new JButton("Reiniciar");
		panel.add(btnReiniciar);
		add(panel,BorderLayout.SOUTH); 

		//Panel de botones
		generarPanel(); 
	}
	
	public void generarPanel() {
		panelBtns = new JPanel();
		panelBtns.setBackground(Color.BLACK);
		numCartas = Aleatorios.obtenerAleatorio();
		vB = new Imagenes [numCartas];
		panelBtns.setLayout(new GridLayout(0,4,5,5));
		hacerBotones();
		add(panelBtns);
		panelBtns.setVisible(true);
	}
	
	public void hacerBotones() {
		
		vector = new Vector<Integer>();	//Vector en el que se guardan valores enteros
		for(int i = 0; i<numCartas/2;i++) {	//itera el numero que se dio aleateario entre 2
			int pos = Rutinas.nextInt(20);	//Se obtiene un numero aleatorio del 0-19
			while(vector.contains(pos)) {	//si ya existe ese dato, generar aleatorios hasta que no
				pos = Rutinas.nextInt(20);
			}
				vector.add(pos);	//Se inserta el mismo dato en posiciones contiguas
				vector.add(pos);
		}
		
		//Ciclo para obtener los datos del vector aleatoriamente y e instanciar el objeto Imagenes
		for(int i=0; i<vB.length;i++) {
			int posVector = Rutinas.nextInt(vector.size());	
			int posImg = vector.get(posVector);	//posImg es igual al dato que sera usado para obtener el String
			vB[i] = new Imagenes(imgs[posImg]);
			panelBtns.add(vB[i]);
			vector.remove(posVector);
		}
	}
	
	public void rehacerPanel() {
		remove(panelBtns);
		generarPanel();
		validate();
		repaint();
		panelBtns.update(getGraphics());	
	}
	
	public void arregloImgs() {
		imgs = new String[20];
		for(int i=0; i<20;i++) {
			imgs[i] = "pares-img/"+i+".png";
		}
	}
	
	
	public void conectaControlador(ControladorPares c) {
		btnReiniciar.addActionListener(c);
		//Agregar escuchadores a todas las imagenes
		for(int i = 0; i<vB.length; i++)
			vB[i].addActionListener(c);
	}
	
	public void mensajeFinalizado() {
		JOptionPane.showMessageDialog(null, "Finalizado!!!");
	}
	
	public void vistaVisible() {
		setVisible(true);
	}
	
	
}
