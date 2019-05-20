import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class CCanvas extends Canvas {

	String cad = "Escrito en canvas";

	public void paint(Graphics g) {
		super.paint(g);
		Color colorOriginal = g.getColor();

		g.drawString(cad, 40, 20);
		g.drawRect(35, 8, (cad.length()*7), 14);
		// dibujo de algunas lineas
		for (int i=20; i< 50; i= i+3) {
			if ((i % 2) == 0) 
				g.setColor(Color.blue);
			else 
				g.setColor(Color.red);
			g.drawLine(40, (90-i), 120, 25+i);
		}

		g.drawOval(40, 95, 120, 20);
		g.fillOval(40, 95, 120, 20);
		//g.drawRect(0,60,350,250);
		g.drawRect(0,0,349,249);
		g.setColor(colorOriginal);
	}
}

public class GraficasCanvasScreenOff extends JFrame implements ActionListener{
	Graphics g;
	Image backbuffer = null;
	boolean BanAvion=false,SentidoAvion=true;
	Timer TAvion,TMaquina;
	int PosAvion=1;
	Image Avion=Rutinas.AjustarImagen("Avion.png", 60, 50).getImage();
	
	class OtroCanvas extends Canvas {
		public void paint(Graphics g){
			g.drawImage(backbuffer, 0, 0, OScreen.getWidth(), OScreen.getHeight(), this);
		}

		public void DibujaOScreen(){
				super.paint(g);
			//	g.clearRect(0,0,349,249);
			// dibujo de algunas lineas
			for (int i=20; i< 50; i= i+3) {
				if ((i % 2) == 0)
					g.setColor(Color.blue);
				else 
					g.setColor(Color.red);
				g.drawLine(40, (90-i), 120, 25+i);
			}
			g.setColor(Color.BLUE);
			g.drawRect(0,0,349,249);
			if(BanAvion)
				g.drawImage(Avion   ,PosAvion ,200 ,null);

				
			repaint();
		}
		
	}	
	
	CCanvas Screen;
	OtroCanvas OScreen;
	JButton Btn,BtnMaquina;
	
	public GraficasCanvasScreenOff(){
		super("Lienzo CANVAS");
		HazInterfaz();
		HazEscuchas();
	}
	public void HazInterfaz(){
		TAvion=new Timer(100,this);
		TMaquina=new Timer(100,this);
		
		setSize(700,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		Screen=new CCanvas();
		Screen.setBounds(0,60,350,250);
		add(Screen);
	
		OScreen=new OtroCanvas();
		OScreen.setBounds(351,60,350,250);

		add(OScreen);
		
		Btn=new JButton("Pon Avión");
		Btn.setBounds(10,10,100,40);
		add(Btn);

		BtnMaquina=new JButton("Pon máquina");
		BtnMaquina.setBounds(560,10,110,40);
		add(BtnMaquina);
		
		setVisible(true);
		

		backbuffer = createImage(OScreen.getWidth(), OScreen.getHeight());
		g =backbuffer.getGraphics();

		OScreen.DibujaOScreen();
	}
	public void HazEscuchas(){
		Btn.addActionListener(this);
		BtnMaquina.addActionListener(this);
	}
	public static void main(String [] a){
		new GraficasCanvasScreenOff();
	}

	@Override
	public void actionPerformed(ActionEvent Evt) {
		if(Evt.getSource()==BtnMaquina){
			
			if(!BanAvion)
				TAvion.start();
			BanAvion=true;
			
			return;
		}
		if(Evt.getSource()==TAvion){
			if(SentidoAvion){
				PosAvion+=10;
				if(PosAvion>290){
					PosAvion=290;
					SentidoAvion=false;
				}	
			}
			else{
				PosAvion-=10;
				if(PosAvion<=0){
					PosAvion=1;
					SentidoAvion=true;
				}	
				
			}
				
		}
		OScreen.DibujaOScreen();
	}	
	
}


