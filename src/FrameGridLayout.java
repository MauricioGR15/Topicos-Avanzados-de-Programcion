
import javax.swing.*; 

import java.awt.*; import java.awt.event.*;
class FrameGridLayout extends JFrame implements ActionListener,FocusListener {
        JTextField Nombre=new JTextField(20);
        JTextField NoControl;
        JButton Grabar,Limpiar;
      public void HazInterfaz(){
          setLayout(new GridLayout(3,2,5,10));
          setSize( 350, 150 );
          setLocation(100,300); 
          JLabel Etiqueta1 = new JLabel("No.Control",SwingConstants.RIGHT);
          add(Etiqueta1);
         NoControl=new JTextField("10");
         add(NoControl);
         JLabel Etiqueta2 = new JLabel("Nombre",SwingConstants.RIGHT);
         add(Etiqueta2);
         add(Nombre);
         Grabar=new JButton("Grabar");
         add(Grabar);
         add(Limpiar=new JButton("Limpiar"));
         setVisible(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      	  
      }
      public void HazEscuchas(){
    	 NoControl.addActionListener(this);
    	 NoControl.addFocusListener(this);
    	 
    	 Nombre.addActionListener(this);
    	 Nombre.addFocusListener(this);
    	 
    	 Limpiar.addActionListener(this);
    	 
    	 
      }
      public FrameGridLayout() {
          super("**Grid**");
          HazInterfaz();
          HazEscuchas();
         
   }
   public static void main(String [] a) {
       new FrameGridLayout();
   }
@Override
	public void actionPerformed(ActionEvent Evt) {
		if(Evt.getSource() instanceof JTextField ){
			MetCajasTexto(Evt);
			return;
		}
		if(Evt.getSource() instanceof JButton ){
			MetBotones(Evt);
			return;
		}

}
	private void MetCajasTexto(ActionEvent Evt){
		if(Evt.getSource()==NoControl){
			Nombre.requestFocus();
			return;
		}
		if(Evt.getSource()==Nombre){
			Grabar.requestFocus();
			return;
		}
	}
	private void MetBotones(ActionEvent Evt){
		if(Evt.getSource()==Limpiar){
			NoControl.setText("");
			Nombre.setText("");
			NoControl.requestFocus();
			return;
		}
	}
	@Override
	public void focusGained(FocusEvent Evt) {
		System.out.println("tomó el foco");
		JTextField Aux=(JTextField) Evt.getSource();
		Aux.setFont(new Font("TimesRoman",Font.BOLD,20));
		
	}
	@Override
	public void focusLost(FocusEvent Evt) {
		System.out.println("dejó el foco");

		
	}
}

