package TorreHanoi;

import java.awt.*;
import javax.swing.*;
import utilidades.*;

public class VistaHanoi extends JFrame{

	Image background;
	Canvass lienzo;
	JRadioButton radio1,radio2,radio3;
	ButtonGroup grupo;
	JButton iniciar;
	int x,y,w,xDisco=120,yDisco=345,wDisco=140;
	static int noDiscos;
	final static int hDisco = 10;
	Disco discos[];
	
	public VistaHanoi(){
		super("Torres de Hanoi");
		hazInterfaz();
	}

	public void hazInterfaz(){
		pedirNoDiscos();
		background =(Rutinas.AjustarImagen("hanoi-img/bg.jpg", 1000, 700)).getImage();
		setSize(1000,700);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		getContentPane().setBackground(Color.BLACK);
		
		lienzo = new Canvass(createImage(800,400));
		add(lienzo);
		arregloDiscos();
		lienzo.dibujaLienzo();
		hazRadioButtons();
		hazBoton();
	}
	
	public void hazRadioButtons() {
		grupo = new ButtonGroup();
		
		radio1 = new JRadioButton("Lento");
		radio2 = new JRadioButton("Normal",true);
		radio3 = new JRadioButton("Rapido");
		
		radio1.setBounds(370, 550, 80, 40);
		radio2.setBounds(470, 550, 80, 40);
		radio3.setBounds(570, 550, 80, 40);
		
		radio1.setBackground(null);
		radio1.setForeground(Color.WHITE);
		radio1.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));
		radio1.setOpaque(false);
		
		radio2.setBackground(null);
		radio2.setForeground(Color.WHITE);
		radio2.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));
		radio2.setOpaque(false);
		
		radio3.setBackground(null);
		radio3.setForeground(Color.WHITE);
		radio3.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));
		radio3.setOpaque(false);
		
		grupo.add(radio1);
		grupo.add(radio2);
		grupo.add(radio3);
		
		add(radio1);
		add(radio2);
		add(radio3);
		
		radio1.grabFocus();
		radio2.grabFocus();
		radio3.grabFocus();
		
	}
	
	public void hazBoton() {
		iniciar = new JButton("Iniciar");
		iniciar.setBounds(425,600,150,50);
		add(iniciar);
		iniciar.grabFocus();
	}
	
	public void pedirNoDiscos() {
		while(true) {
			try {
				noDiscos = Integer.parseInt(JOptionPane.showInputDialog("Numero de Discos (3-15)"));
				if(noDiscos < 3 || noDiscos >15) {
					JOptionPane.showMessageDialog(null, "Ingrese un numero del 3 al 15");
					continue;
				}
				break;
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Ingrese un numero del 3 al 15");
			}
		}
	}
	
	public void arregloDiscos() {
		discos = new Disco[noDiscos];
		x = xDisco+(2*noDiscos);y = yDisco-(hDisco*noDiscos);w = wDisco-(2*noDiscos);
		for(int i=0; i<noDiscos;i++) {
			discos[i] = new Disco(x,y,w,hDisco);
			x-=2; y+=10; w+=4; 
		}
		lienzo.setDiscos(discos);
	}
	
	public void reiniciaVista() {
		noDiscos = 0;
		pedirNoDiscos();
		arregloDiscos();
		lienzo.dibujaLienzo();
	}
	
	
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background, 0, 0, 1000, 700, null);
	}
}
