import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class CajaTextoLeeEntero extends JFrame implements ActionListener{
	JLeeDecimal TxtNoControl,TxtNombre,TxtEdad;
	Color BackTxt;
	JRadioButton RadioHombre,RadioMujer;
	public CajaTextoLeeEntero(){
		super("Caja texto válidada");
		HazInterfaz();
		HazEscuchas();
   	   
	}
	public void HazInterfaz(){
		setLayout(new GridLayout(0,2,5,5));
		TxtNoControl=new JLeeDecimal(8);
		BackTxt=TxtNoControl.getBackground();
		TxtNombre=new JLeeDecimal(30);
		TxtEdad=new JLeeDecimal(3);
		add(RadioHombre=new JRadioButton("Hombre"));
		add(RadioMujer=new JRadioButton("Mujer"));
		add(new JLabel("NoControl :",SwingConstants.RIGHT));
		add(TxtNoControl);
		add(new JLabel("Nombre :",SwingConstants.RIGHT));
		add(TxtNombre);
		add(new JLabel("Edad :",SwingConstants.RIGHT));
		add(TxtEdad);
		
		setSize(300,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setVisible(true);
		setResizable(false);
		TxtNoControl.requestFocus();

	
	}
	public void HazEscuchas(){
		TxtNoControl.addActionListener(this);
		TxtNombre.addActionListener(this);
		TxtEdad.addActionListener(this);
	}
	public static void main(String [] a){
		new CajaTextoLeeEntero();

	}
	@Override
	public void actionPerformed(ActionEvent Evt) {
		if(Evt.getSource()==TxtNoControl){
			Double NoCtl=TxtNoControl.getCantidad();
			System.out.println("Cantidad= "+NoCtl);
			if(NoCtl<1000){
				JOptionPane.showMessageDialog(this, "No. control no válido");
			//	TxtNoControl.selectAll();
				return;
			}
			TxtNombre.requestFocus();
		}
		
	}
}
