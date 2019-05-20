import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.border.LineBorder;
import javax.swing.*;


public class JLeeDecimal  extends JTextField implements  KeyListener, FocusListener  {
	private int Longitud;
	Color BackTxt;
	public JLeeDecimal(){
		this(10);
	}
	public JLeeDecimal(int Longitud){
		this.Longitud=Longitud;
		addKeyListener(this);
		addFocusListener(this);
		BackTxt=getBackground();
		this.setTransferHandler(null);// evita ctlc ctlv
	}
	public void keyPressed(KeyEvent Evt) {
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
	private String PonComas(String Cadena){
		
		String Decimal="",Texto=Cadena;
		int Pos=Cadena.indexOf(".");
		if(Pos>-1){
			Decimal=Cadena.substring(Pos);
			Texto=Cadena.substring(0,Pos);
		}
				
		
		
		if(Texto.length()<4)
			return Texto+Decimal;
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
		return Res+Decimal;
	}
	public Double getCantidad(){
		
		try {
			return Double.parseDouble(QuitaComas());
		} catch(Exception E){return 0.0;}
	}
	public void keyTyped(KeyEvent Evt) {
		char Car;
		if(QuitaComas().length()==Longitud){
			Evt.consume();
			Toolkit.getDefaultToolkit().beep();
			return;
		}
		Car=Evt.getKeyChar();
		if( !(Character.isDigit(Car) || Car=='-' || Car=='.')    ){
			Evt.consume();
			return;
		}

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
		if(Car=='.'){
		   if(getText().indexOf(".")>=0 ){
			   Evt.consume();
			   return;
		   }
		   if(getText().length()==0){
			   setText("0");
			   return;
		   }
		   setText(getText()+".");
		   Evt.consume();
		   return;
		}
		int Pos=getText().indexOf(".");
		if(Pos>-1 && getText().length()-Pos >2     ){
			Evt.consume();
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
