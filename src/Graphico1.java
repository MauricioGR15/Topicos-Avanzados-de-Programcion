import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Graphico1 extends JFrame implements ActionListener {
	int X_RuedaDelantera=0,Sub=0;
	boolean B=true;
	Image [] Imagen={Rutinas.AjustarImagen("Avion.png", 60, 50).getImage(),
			Rutinas.AjustarImagen("AvionReversa.png", 60, 50).getImage() };
	private Timer T,T1;
	boolean Derecha;
	JButton BtnAvance;
	int Pos=0;
	
	public Graphico1(){
		super("Manejo de gráficos");
		Derecha=true;
		T=new Timer(200,this);
		T1=new Timer(500,this);
		T.start();
		HazInterfaz();
		HazEscuchador();
	}
	private void HazInterfaz(){
		setSize(500,600);
		setLayout(null);
		setLocationRelativeTo(null);
		BtnAvance=new JButton("Avance");
		BtnAvance.setBounds(350, 0, 100, 50);
		add(BtnAvance);
	//	BtnAvance.grabFocus();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//Muevelo();
	}
	private void HazEscuchador(){
		BtnAvance.addActionListener(this);
	}
	private void Muevelo(){
		for(int i=0 ; i< 10 ; i++){
			System.out.println("etro");
			X_RuedaDelantera+=10;
			repaint();
			
			}
	}
	public static void main(String [] a){
		new Graphico1();
	}
	public void paint( Graphics g) 	{
		super.paint(g);
		g.drawString("INSTITUTO TECNOLÓGICO DE CULIACÁN",50,40);
		g.drawLine(20,45,300,45);
		g.drawString("INGENIERÍA EN SISTEMAS COMPUTACIONALES",50,60);
		g.setColor(Color.BLUE);
		g.fillRect(50,70,100,50);
		g.fillRoundRect(250, 70,100, 50, 20, 20);
		Color CAFE=new Color(192,128,64);
		g.setColor(CAFE);
		g.fillRect(50+X_RuedaDelantera,150,200,100);
		g.setColor(Color.RED);
		g.fillOval(60+X_RuedaDelantera,220,60,60);
		g.fillOval(190+X_RuedaDelantera,220,50,60);
		g.setColor(Color.WHITE);
		g.fillRect(200+X_RuedaDelantera,160,50,30);


		g.drawImage(Imagen[Sub],Pos,450,null);
	}

	public void actionPerformed(ActionEvent Evt) {
		if(Evt.getSource()==BtnAvance){
			if(X_RuedaDelantera==0)
				B=true;
			if(X_RuedaDelantera==250)
				B=false;
			if(B)
				X_RuedaDelantera+=10;
			else
				X_RuedaDelantera-=10;
			repaint();
			return;
		}
		if(Evt.getSource()==T){
		   if(Derecha){
			   Pos+=10;
			   if(Pos>480){
				   Derecha=false;
				   Pos=480;
				   Sub=1;
			   }
			   repaint();
			   return;
		   }
		   Pos-=10;
		   if(Pos<=0){
			   Derecha=true;
			   Pos=0;
			   Sub=0;
		   }
		   repaint();
		}
		
	}

}
