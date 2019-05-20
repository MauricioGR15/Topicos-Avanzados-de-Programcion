import java.awt.BorderLayout;

import javax.swing.*;
public class Ejemplo1V2  extends JFrame{
	public Ejemplo1V2(String Nombre){
		super("Captura de personal");
		HazInterfaz(Nombre);
		HazEscuchas();

	}
	public void HazInterfaz(String Nombre){
		JButton Btn1,Btn2;
		Btn1=new JButton("Aceptar",new ImageIcon(Nombre));
		Btn2=new JButton("Rechazar",Rutinas.AjustarImagen("Caballos.jpg", 50, 50));
		//setTitle("Primer pantalla");
		setSize(400, 300);
		setLocationRelativeTo(null);
		
		add(Btn1,BorderLayout.SOUTH);
		add(Btn2,BorderLayout.NORTH);
		add(new JButton("Limpiar"),BorderLayout.EAST);
		add(new JButton("Grabar"),BorderLayout.WEST)	;	

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage (new ImageIcon(Nombre).getImage());
		setVisible(true);		
	}
	public void HazEscuchas(){
		
	}
	public static void main(String [] a){
		System.out.println("total de elementos "+a.length);
		new Ejemplo1V2(a[0]);
	}

}
