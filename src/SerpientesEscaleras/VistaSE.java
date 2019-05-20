package SerpientesEscaleras;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import utilidades.*;

public class VistaSE extends JFrame{

	JPanel panel2,panelDados,pnlTablero,panelJugadores,panelTurno,panelNorte;
	JOptionPane jOpt;
	JLabel lblJ,lblDados, lblimgJug;
	JButton  btnDados;
	JTextField tfNombreJug;
	
	ImageIcon serpFin = Rutinas.AjustarImagen("se-imagenes/serpienteBaja.png", 13, 13);
	ImageIcon escalera = Rutinas.AjustarImagen("se-imagenes/ladder.png", 13, 13);
	int numeroJugadores;


	public VistaSE(){
		super("Serpientes y Escaleras");
		hazInterfaz();
	}


	private void hazInterfaz(){

		pedirNoJugadores();

		setSize(700,700);
		setIconImage(new ImageIcon("se-imagenes/ladder.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);	
		hazPanelNorte();
		hazTablero();
		casillasTablero();
		
		setLocationRelativeTo(null);
		setResizable(false);

	}

	private void hazPanelNorte() {

		panelNorte = new JPanel(new GridLayout(0,2,10,10));
		panelNorte.setBackground(Color.black);

		//Panel1 Jugadores
		panelJugadores = new JPanel(new GridLayout(0,5,5,5));
		panelJugadores.setBackground(new Color(200,200,200));
		panelJugadores.setBorder(BorderFactory.createLineBorder(Color.white,3));

		//Panel2(Contiene Turno y Dados)
		panel2 = new JPanel(new GridLayout(0,2,5,5));
		panel2.setBackground(new Color(200,200,200));
		panel2.setBorder(BorderFactory.createLineBorder(Color.white,3));

		//Panel Turnos
		panelTurno = new JPanel(new GridLayout(0,1,5,5));
		panelTurno.setBackground(new Color(200,200,200));
		JLabel lblTurno = new JLabel("TURNO",SwingConstants.CENTER);
		lblTurno.setForeground(Color.WHITE);
		lblTurno.setFont(new Font("Russo One",0,16));
		panelTurno.add(lblTurno);
		lblimgJug = new JLabel(Rutinas.AjustarImagen("se-imagenes/j1.png", 25, 25));
		panelTurno.add(lblimgJug);

		panel2.add(panelTurno);

		//Panel Dados
		panelDados = new JPanel(new GridLayout(0,2,8,8));
		panelDados.setBackground(new Color(200,200,200));

		ImageIcon dado= new ImageIcon(Rutinas.AjustarImagen("se-imagenes/dado.png", 50, 50).getImage());
		btnDados = new JButton(dado);
		btnDados.setDisabledIcon(dado);
		btnDados.setBackground(Color.WHITE);
		lblDados = new JLabel("0",SwingConstants.CENTER);
		lblDados.setFont(new Font("Russo One",Font.BOLD,32));
		lblDados.setForeground(Color.WHITE);
		panelDados.add(btnDados);
		panelDados.add(lblDados);

		panel2.add(panelDados);

		panelNorte.add(panelJugadores);
		panelNorte.add(panel2);
		add(panelNorte,BorderLayout.NORTH);

	}

	public void hazTablero() {
		pnlTablero = new JPanel(new GridLayout(0,10));
		pnlTablero.setBackground(new Color(200,200,200));
		JPanel aux;
		int x=100;
		for(int i=0; i<10; i++) {
			if((x/10) % 2 != 0) {
				x-=9;
				for(int j=0; j<10;j++) {
					aux = LogicaSE.ObtenerNodo(x).Info;
					pnlTablero.add(aux);
					x++;
				}
				x-=11;
				continue;
			}
			for(int j=10; j>0; j-- ) {
				aux = LogicaSE.ObtenerNodo(x).Info;
				pnlTablero.add(aux);
				x--;
			}
		}
		add(pnlTablero,BorderLayout.CENTER);

	}	

	public void casillasTablero() {
		for(int i=1; i<101;i++) {
			Casilla aux,aux2;
			aux = LogicaSE.ObtenerNodo(i).Info;
			if(aux.getTipoCasilla() == 'S') {
				aux.getPanelN().setBackground(new Color(255,106,106));
				aux.setImgCasilla(new JLabel(serpFin));
				aux.getPanelN().add(aux.getImgCasilla(),BorderLayout.EAST);
				aux2 = LogicaSE.ObtenerNodo(i-aux.getPosiciones()).Info;
				aux2.getPanelN().setBackground(new Color(255,106,106));
			}
			if(aux.getTipoCasilla() == 'E') {
				aux.getPanelN().setBackground(new Color(250,250,255));
				aux.setImgCasilla(new JLabel(escalera));
				aux.getPanelN().add(aux.getImgCasilla(),BorderLayout.EAST);
				aux2 = LogicaSE.ObtenerNodo(i+aux.getPosiciones()).Info;
				aux2.getPanelN().setBackground(new Color(250,250,255));
			}
		}

	}
	
	public void pedirNoJugadores() {
		while(true) {
			try {
				numeroJugadores = Integer.parseInt(JOptionPane.showInputDialog("Numero Jugadores (2-10)"));
				if(numeroJugadores < 2 || numeroJugadores >10) {
					JOptionPane.showMessageDialog(null, "Ingrese un numero del 2 al 10");
					continue;
				}
				break;
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Ingrese un numero del 2 al 10");
			}
		}
	}
	
	public void ActualizaTurno(int j){
		lblimgJug.setIcon(Rutinas.AjustarImagen("se-imagenes/j"+j+".png", 25, 25));
		lblimgJug.update(lblimgJug.getGraphics());
	}
	
	public void reiniciarJuego() {
		pedirNoJugadores();
		remove(panelNorte);
		remove(pnlTablero);
		hazPanelNorte();
		hazTablero();
		update(getGraphics());
	}

}
