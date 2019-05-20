import javax.swing.*;
import java.awt.*;
class FrameFlowLayout extends JFrame {
   public FrameFlowLayout() {
	   super("**Layout**");
	      HazInterfaz();
	      HazEscuchas();
   }
   private void HazInterfaz(){
	      
	       setLayout(new FlowLayout(2,10,10));
	      setSize( 350, 150 );
	      setLocation(100,300);
	      JLabel Etiqueta1 = new JLabel("No.Control",SwingConstants.CENTER);
	      add(Etiqueta1);
	      JLabel Etiqueta2 = new JLabel("Nombre",SwingConstants.CENTER);
	      add(Etiqueta2);
	      add(new JButton("Edad"));
	      add(new JButton("EC"));
	      add(new JButton("SEXO"));
	      setVisible(true);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	      setLocationRelativeTo(null);
   }
   private void HazEscuchas(){
	   
   }
   public static void main(String [] a) {
	   new FrameFlowLayout();
 
  }
}
