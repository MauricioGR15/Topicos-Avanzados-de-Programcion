import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;


public class GraphicoScreenOff extends JFrame implements ActionListener,  MouseListener {
	int X_RuedaDelantera=0,Sub=0;
	boolean B=true,Triangulo=false,Circulo=false;
	Image [] Imagen={Rutinas.AjustarImagen("Avion.png", 60, 50).getImage(),
			Rutinas.AjustarImagen("AvionReversa.png", 60, 50).getImage() };
	private Timer T,T1;
	boolean Derecha;
	JButton BtnAvance,BtnTriangulo,BtnCirculo;
	int Pos=0;
	Graphics g;
	Image backbuffer = null;
	public GraphicoScreenOff(){
		super("Manejo de gráficos");
		Derecha=true;
	
		
		T=new Timer(200,this);
		T1=new Timer(500,this);
		T.start();
		HazInterfaz();
		HazEscuchador();
		
		backbuffer = createImage(getWidth(), getHeight());
		g =backbuffer.getGraphics();
	
		Dibuja();
	}
	private void HazInterfaz(){
		setSize(500,600);
		setLayout(null);
		setLocationRelativeTo(null);
		BtnAvance=new JButton("Avance");
		BtnAvance.setBounds(350, 0, 100, 50);
		
		BtnCirculo=new JButton("Circulo");
		BtnCirculo.setBounds(350, 60, 100, 50);
		
		BtnTriangulo=new JButton("Triangulo");
		BtnTriangulo.setBounds(350, 120, 100, 50);
		
		add(BtnAvance);
		add(BtnCirculo);
		add(BtnTriangulo);
	//	BtnAvance.grabFocus();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//Muevelo();
	}
	private void HazEscuchador(){
		BtnAvance.addActionListener(this);
		BtnCirculo.addMouseListener(this);
		BtnTriangulo.addMouseListener(this);
	}
	private void Muevelo(){
		for(int i=0 ; i< 10 ; i++){
			System.out.println("etro");
			X_RuedaDelantera+=10;
			repaint();
			
			}
	}
	public static void main(String [] a){
		new GraphicoScreenOff();
	}
	public void paint( Graphics g) 	{
		g.drawImage(backbuffer, 0, 0, getWidth(), getHeight(), this);
	}
	public void Dibuja(){
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
		if(Circulo){
			g.setColor(Color.RED);
			g.fillOval(200, 300, 60, 60);
		}
		if(Triangulo){
			g.setColor(Color.RED);
//			g.drawLine(200,300,150,400);
//			g.drawLine(200,300,250,400);
//			g.drawLine(250,400,150,400);
			int [] VX={150,200,250};
			int [] VY={400,300,400};
			g.drawPolygon(VX, VY, VX.length);
		}		g.drawImage(Imagen[Sub],Pos,450,null);
		repaint();

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
			Dibuja();

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
			   Dibuja();
			   return;
		   }
		   Pos-=10;
		   if(Pos<=0){
			   Derecha=true;
			   Pos=0;
			   Sub=0;
		   }
		   Dibuja();
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent Evt) {
		if(Evt.getSource()==BtnTriangulo){
			Triangulo=true;
			Dibuja();
			return;
		}
		if(Evt.getSource()==BtnCirculo){
			Circulo=true;
			Dibuja();
			return;
		}
		
	}
	@Override
	public void mouseExited(MouseEvent Evt) {
		if(Evt.getSource()==BtnTriangulo){
			Triangulo=false;
			Dibuja();
			return;
		}
		if(Evt.getSource()==BtnCirculo){
			Circulo=false;
			Dibuja();
			return;
		}
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
