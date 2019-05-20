import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
public class JLeeEntero extends JTextField implements KeyListener, FocusListener {
	private int Longitud;
	Color BackTxt;
	public JLeeEntero(){
		this(10);
	}
	public JLeeEntero(int Longitud){
		this.Longitud=Longitud;
		addKeyListener(this);
		addFocusListener(this);
		BackTxt=getBackground();
		this.setTransferHandler(null);// evita ctlc ctlv
	}
	public void keyPressed(KeyEvent Evt) {
//		if(Evt.isControlDown()){
//			Evt.consume();
//			return;
//		}
//		//consume la tecla Inicio,flecha izq o flecha der
		if(Evt.getKeyCode()==36 ||Evt.getKeyCode()==37 || Evt.getKeyCode()==39){
			Evt.consume();
			return;
		}		
		
	}

	
	public void keyReleased(KeyEvent Evt) {
		String Texto=QuitaComas();
		
		setText(PonComas(Texto));
	}
	private String QuitaComas(){
		String Res="";
		char Car;
		for(int i=0 ; i<getText().length(); i++){
			Car=getText().charAt(i);
			if(Car==',')
				continue;
			Res=Res+Car;
		}
		return Res;
	}
	private String PonComas(String Texto){
		if(Texto.length()<4)
			return Texto;
		boolean Band=false;
		if(Texto.charAt(0)=='-'){
			Texto=Texto.substring(1);
			Band=true;
		}
		
		
		String Res="";
		char Car;
		int Cont=0;
		for(int i=Texto.length()-1 ; i>=0 ; i--){
			Cont++;
			Car=Texto.charAt(i);
			Res=Car+Res;
			if(Cont % 3 ==0 && i>0)
				Res=","+Res;
			
		}
		if(Band)
			Res="-"+Res;
		return Res;
	}
	public long getCantidad(){
		
		try {
			return Long.parseLong(QuitaComas());
		} catch(Exception E){return 0;}
	}
	public void keyTyped(KeyEvent Evt) {
		char Car;
		if(QuitaComas().length()==Longitud){
			Evt.consume();
			Toolkit.getDefaultToolkit().beep();
			return;
		}
		Car=Evt.getKeyChar();
		if( !(Character.isDigit(Car) || Car=='-')    ){
			Evt.consume();
			return;
		}
//		if(Car=='-' && getText().length()!=0  ){
//			Evt.consume();
//			return;
//		}
		String Texto;
		if(Car=='-'   ){
			if(getText().indexOf("-")>-1 ){
			   Evt.consume();
			   return;
			}
			Evt.consume();
			setText(Car+getText());
			return;
		}
	}

	public void focusGained(FocusEvent Evt) {
		
		setBackground(Color.YELLOW);
		setBorder(new LineBorder(Color.RED));
		selectAll();
		setFocusTraversalKeysEnabled(false);
		Font Fuente=new Font("Tahoma",Font.BOLD,20);
		setFont(Fuente);			
	}

	public void focusLost(FocusEvent Evt) {		
		setBackground(BackTxt);	
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		Font Fuente=new Font("TimesRoman",Font.PLAIN,18);
		setFont(Fuente);					
	}

}
