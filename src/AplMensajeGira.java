import java.awt.BorderLayout;

import javax.swing.*;
public class AplMensajeGira extends JFrame{

	public AplMensajeGira(){
		
		setSize(500,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		add(new JMensajeGira("TODOS LOS DIAS ES UNA OPORTUNIDAD DE MEJORAR, NO TE QUEJES"));
		
		add(new JMensajeGira("Todo bien, gracias"),BorderLayout.NORTH );
		setVisible(true);
		
		
	}
	public static void main(String [] a){
		new AplMensajeGira();
	}
}
