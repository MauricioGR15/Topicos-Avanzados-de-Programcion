import javax.swing.*;
import java.awt.*;

public class Ejemplo1 {
	
	public static void main(String [] a){
		JFrame Pan=new JFrame();
	
		JButton Btn1,Btn2;
		Btn1=new JButton("Aceptar");
		Btn2=new JButton("Rechazar");
		
//		for(int i=0 ; i<20 ; i++)
//			Pan.add(new JButton("Btn # "+i));
		Pan.setTitle("Primer pantalla");
		
		Pan.setSize(400, 300);
		Pan.setLocationRelativeTo(null);
		
		Pan.add(Btn1,BorderLayout.SOUTH);
		Pan.add(Btn2,BorderLayout.NORTH);
		Pan.add(new JButton("Limpiar"),BorderLayout.EAST);
		Pan.add(new JButton("Grabar"),BorderLayout.WEST)	;	
//		Pan.setLocation(200,200);0
//		Pan.setBounds( 200, 100,400, 300);
		Pan.setResizable(false);

		Pan.setVisible(true);
		System.out.println("haz finzlizado");
	}
}
