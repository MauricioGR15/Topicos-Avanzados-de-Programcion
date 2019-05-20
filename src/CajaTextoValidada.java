import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class CajaTextoValidada extends JFrame implements FocusListener,KeyListener, ActionListener{

	JTextField TxtNoControl,TxtNombre,TxtEdad;
	Color BackTxt;
	JRadioButton RadioHombre,RadioMujer;
	public CajaTextoValidada(){
		super("Caja texto válidada");
		HazInterfaz();
		HazEscuchas();
   	   
	}
	public void HazInterfaz(){
		setLayout(new GridLayout(0,2,5,5));
		TxtNoControl=new JTextField();
		BackTxt=TxtNoControl.getBackground();
		TxtNombre=new JTextField();
		TxtEdad=new JTextField();
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
		TxtNoControl.addKeyListener(this);
		TxtNoControl.addActionListener(this);
		TxtNoControl.addFocusListener(this);
		
		TxtNombre.addKeyListener(this);
		TxtNombre.addActionListener(this);
		TxtNombre.addFocusListener(this);
		
		TxtEdad.addKeyListener(this);
		TxtEdad.addActionListener(this);
		TxtEdad.addFocusListener(this);
		
		RadioHombre.addActionListener(this);
		RadioMujer.addActionListener(this);
		
	}
	public static void main(String [] a){
		new CajaTextoValidada();
	}
	
	public void keyPressed(KeyEvent Evt) {
		if(Evt.isControlDown()){
			Evt.consume();
			return;
		}
		
	}
	
	public void keyReleased(KeyEvent Evt) {
		System.out.println("liberada");
	}
	private void MetNumerico(KeyEvent Evt,int Tamaño){
		JTextField TxtAux=(JTextField) Evt.getSource();
		if(TxtAux.getText().length()==Tamaño && Evt.getKeyChar()!=KeyEvent.VK_ENTER ){
			Evt.consume();
			Toolkit.getDefaultToolkit().beep();
			return;
		}
		if(Evt.getKeyChar()<'0' || Evt.getKeyChar()>'9' ){
			Evt.consume();
			return;
		}	
	}
	public void keyTyped(KeyEvent Evt) {
		if(Evt.getSource()==TxtNoControl){
			MetNumerico(Evt,8);
			return;
		}
		if(Evt.getSource()==TxtEdad){
			MetNumerico(Evt,3);
			return;
		}
		if(Evt.getSource()==TxtNombre){
			if(TxtNombre.getText().length()==20){
				Evt.consume();
				Toolkit.getDefaultToolkit().beep();
				return;
			}
			if(!(Evt.getKeyChar()>='A' && Evt.getKeyChar()<='Z' ||
					Evt.getKeyChar()>='a' && Evt.getKeyChar()<='z' ||
					Evt.getKeyChar()==' ')  ){
				Evt.consume();
				return;
			}
			return;
		}
		

	}
	@Override
	public void actionPerformed(ActionEvent Evt) {
		if(Evt.getSource()==RadioHombre){
			System.out.println("boton hombre");
			return;
		}
		if(Evt.getSource()==RadioMujer){
			System.out.println("boton mujer");
			return;
		}
		if(Evt.getSource()==TxtNoControl){
			int NC=Integer.parseInt(TxtNoControl.getText());
			if(NC<1000){
				JOptionPane.showMessageDialog(null, "No control inadecuado");
				TxtNoControl.requestFocus();
				return;
			}
		   TxtNombre.requestFocus();
		   return;
		}
		if(Evt.getSource()==TxtNombre){
			   TxtEdad.requestFocus();
			   return;
			}
	}
	@Override
	public void focusGained(FocusEvent Evt) {
		JTextField Aux=(JTextField) Evt.getSource();
		Aux.setBackground(Color.YELLOW);
		Aux.setBorder(new LineBorder(Color.RED));
		Aux.selectAll();
		Aux.setFocusTraversalKeysEnabled(false);
		Font Fuente=new Font("Tahoma",Font.BOLD,20);
		Aux.setFont(Fuente);		
		
	}
	@Override
	public void focusLost(FocusEvent Evt) {
		JTextField Aux=(JTextField) Evt.getSource();
		Aux.setBackground(BackTxt);	
		Aux.setBorder(new LineBorder(Color.LIGHT_GRAY));
		Font Fuente=new Font("TimesRoman",Font.PLAIN,18);
		Aux.setFont(Fuente);	
		
	}
}
