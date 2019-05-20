
import javax.swing.*; 
import java.awt.*; import java.awt.event.*;
class FrameBoxLayout extends JFrame implements ActionListener {
	JRadioButton Radio1,Radio2;
	ButtonGroup Gpo1;
	JCheckBox ChkEC,ChkEstatus,ChkMedida;
	JScrollPane PS;
	
	public FrameBoxLayout() {
		super("**Box**");
		HazInterfaz();
		HazEscuchas();
	}
	public void HazInterfaz(){
		// el marco por default tiene BorderLayout
		Gpo1=new ButtonGroup();
		
		setSize( 500, 150 );
		setLocation(100,300);
		JPanel Panel1=new JPanel(); 
		Panel1.setLayout(new BoxLayout(Panel1,BoxLayout.Y_AXIS));
		JPanel Panel2=new JPanel(); 
		Panel2.setLayout(new BoxLayout(Panel2,BoxLayout.X_AXIS));
		Panel1.add(new JButton("Aceptar"));
		Panel1.add(new JButton("Cancelar"));
		Panel1.add(new JButton("Salir")); 
		PS=new JScrollPane(Panel1);
	
		add(PS,BorderLayout.EAST);
		Panel2.add(new JLabel("Nombre")); 
		Panel2.add(new JTextField(10));
		Panel2.add(new JLabel("Sexo")); 
		Panel2.add(new JTextField(2));
		add(Panel2,BorderLayout.NORTH);
		JPanel Panel3=new JPanel();
		Panel3.setLayout(new BoxLayout(Panel3,BoxLayout.Y_AXIS));
		Radio1=new JRadioButton("Mascúlino",true);
		Radio2=new JRadioButton("Femenino");
		Gpo1.add(Radio1);
		Gpo1.add(Radio2);
		Panel3.add(new JLabel("Seleccione sexo"));
		Panel3.add(Radio1);
		Panel3.add(Radio2);
		add(Panel3,BorderLayout.WEST);  
		
		ChkEC=new JCheckBox("Soltera",true);
		ChkEstatus=new JCheckBox("Rica",true);
		ChkMedida=new JCheckBox("90 60 90",true);
		JPanel Panel4=new JPanel();
		Panel4.add(new JLabel("Mujer ideal"));
		Panel4.add(ChkEC);
		Panel4.add(ChkEstatus);
		Panel4.add(ChkMedida);
		add(Panel4,BorderLayout.SOUTH);
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public void HazEscuchas(){
		Radio1.addActionListener(this);
		Radio2.addActionListener(this);
	}
	public static void main(String [] a) {
		new FrameBoxLayout();
		
	}

	public void actionPerformed(ActionEvent Evt) {

		
	}
}
