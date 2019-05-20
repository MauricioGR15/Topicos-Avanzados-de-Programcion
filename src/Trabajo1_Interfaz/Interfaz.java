package Trabajo1_Interfaz;

import java.awt.*;
import javax.swing.*;
import utilidades.*;

import java.awt.event.*;

public class Interfaz extends JFrame implements ActionListener, FocusListener {
	
	ButtonGroup grupo;
	JRadioButton radio1,radio2,radio3,radio4;
	JTextField nombre,edad,estatura;
	JButton consulta,agregar,limpiar;
	static int Criterio = 1;
	
	
	public Interfaz() {
		super("Trabajo 1");
		hazInterfaz();
		hazEscuchas();
	}

	public void hazInterfaz(){
		grupo = new ButtonGroup();
		
		
		
		JPanel panel = new JPanel();
		radio1 = new JRadioButton("Nombre",true);
		radio2 = new JRadioButton("Edad");
	    radio3 = new JRadioButton("Estatura");
		radio4 = new JRadioButton("Edad-Estat-Nom");
		panel.add(new JLabel("Seleccione criterio: "));
		grupo.add(radio1);
		grupo.add(radio2);
		grupo.add(radio3);
		grupo.add(radio4);
		panel.add(radio1);
		panel.add(radio2);
		panel.add(radio3);
		panel.add(radio4);
		add(panel,BorderLayout.NORTH);
		
		JPanel panel2 = new JPanel();
		
		panel2.add(new JLabel("Nombre",SwingConstants.RIGHT));
		nombre = new JTextField(15);
		edad = new JTextField(3);
		estatura = new JTextField(3);
		panel2.add(nombre);
		panel2.add(new JLabel("Edad",SwingConstants.RIGHT));
		panel2.add(edad);
		panel2.add(new JLabel("Estatura",SwingConstants.RIGHT));
		panel2.add(estatura);
		add(panel2,BorderLayout.WEST);
		
		JPanel panel3 = new JPanel();
		consulta = new JButton("Consulta");
		agregar = new JButton("Agregar");
		limpiar = new JButton("Limpiar");
		panel3.add(agregar);
		panel3.add(consulta);
		panel3.add(limpiar);
		add(panel3,BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	public void hazEscuchas(){
		
		radio1.addActionListener(this);
		radio2.addActionListener(this);
		radio3.addActionListener(this);
		radio4.addActionListener(this);
		
		nombre.addActionListener(this);
		nombre.addFocusListener(this);
		edad.addActionListener(this);
		edad.addFocusListener(this);
		estatura.addActionListener(this);
		estatura.addFocusListener(this);
		
		consulta.addActionListener(this);
		agregar.addActionListener(this);
		limpiar.addActionListener(this);
		
	}
	
	
	
	public static void main(String []arg){
		new Interfaz();
	}

	@Override
	public void focusGained(FocusEvent evt) {
		
		
	}

	@Override
	public void focusLost(FocusEvent evt) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		
		if(evt.getSource() instanceof JTextField) {
			MetCajasTexto(evt);
			return;
		}
		if(evt.getSource() instanceof JButton) {
			MetBotones(evt);
			return;
		}
		if(evt.getSource() instanceof JRadioButton) {
			MetRadios(evt);
			return;
		}
	}
	
	public void MetCajasTexto(ActionEvent evt) {
		if(evt.getSource() == nombre) {
			edad.requestFocus();
			return;
		}
		if(evt.getSource() == edad) {
			estatura.requestFocus();
			return;
		}
		if(evt.getSource()==estatura) {
			agregar.requestFocus();
		}
	}
	
	public void MetBotones(ActionEvent evt) {
		if(evt.getSource()==limpiar) {
			nombre.setText("");
			edad.setText("");
			estatura.setText("");
			return;
		}
		
		if(evt.getSource()==consulta) {
			
			if(ListaMetodos.ListaPersonas.getFrente() == null) {
				JOptionPane.showMessageDialog(null, "La lista esta vacia");
				return;
			}
			JDialog consPanel = new JDialog();
			consPanel.setTitle("Consulta");
			consPanel.setLocationRelativeTo(null);
			consPanel.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			consPanel.setModal(true);
			consPanel.setLayout(new GridLayout(0,3,5,5));
			NodoDL<Persona> aux = ListaMetodos.ListaPersonas.getFrente();
			
			
			consPanel.add(new Label("Nombre"));
			consPanel.add(new Label("Edad"));
			consPanel.add(new Label("Estatura"));
			
			while(aux!=null) {
				consPanel.add(new Label(aux.Info.getNombre()));
				consPanel.add(new Label(aux.Info.getEdad()+""));
				consPanel.add(new Label(aux.Info.getEstatura()+""));
				aux = aux.getSig();
			}
			
			
			consPanel.pack();
			consPanel.setVisible(true);
			return;
		}
		
		if(evt.getSource() == agregar) {
			String name = nombre.getText();
			int age = Integer.parseInt(edad.getText());
			int height = Integer.parseInt(estatura.getText());
			ListaMetodos.ListaPersonas.InsertaOrdenado(new Persona(name,age,height));
			return;
		}
	}
	
	public void MetRadios(ActionEvent evt) {
		if(evt.getSource()==radio1) {
			Criterio = 1;
			ListaMetodos.QuickSort();
		}
		if(evt.getSource()==radio1) {
			Criterio = 2;
			ListaMetodos.QuickSort();
		}
		if(evt.getSource()==radio1) {
			Criterio = 3;
			ListaMetodos.QuickSort();
		}
		if(evt.getSource()==radio1) {
			Criterio = 4;
			ListaMetodos.QuickSort();
		}
	}

}
