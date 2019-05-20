package TorreHanoi;

import java.awt.event.*;
import javax.swing.*;

public class Controlador implements ActionListener{

	private Movimientos moves;
	private ModeloHanoi model;
	public VistaHanoi view;
	
	private Timer T;
	private Torre auxtI,auxtF;
	private Disco auxDisc;
	private String m;
	private int sube,desplaza,baja;
	private int contMov; 
	public static final int px=1;
	private boolean band = true;
	private boolean band2=true;

	Controlador(ModeloHanoi model, VistaHanoi view){
		this.model = model;
		this.view = view;
		moves = new Movimientos();
		model.Hanoi('A', 'B', 'C', VistaHanoi.noDiscos);
		hazEscuchadores();
		model.muestraMov();
	}

	public void hazEscuchadores() {
		view.iniciar.addActionListener(this);
		view.radio1.addActionListener(this);
		view.radio2.addActionListener(this);
		view.radio3.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == view.iniciar) {
			T = new Timer(34,this);
			T.start();
			view.iniciar.setEnabled(false);
			return;
		}
		if(evt.getSource() instanceof JRadioButton) {
			metRadioButton(evt);
			return;
		}
		if(evt.getSource() == T) {
			if(!band2) {
				contMov++;
				if(contMov == model.movimientos.size()) {
					T.stop();
					System.out.println("acabo");
					reiniciar();
					return;
				}
				band = true;
			}

			m = model.movimientos.get(contMov);

			if(band) {
				auxtI = moves.getTorre(m.charAt(3));
				auxtF = moves.getTorre(m.charAt(4));
				auxDisc = view.discos[Integer.parseInt(m.substring(0, 3))-1];
				sube = moves.getLimSubida();
				desplaza = auxtF.getTorre_x();
				baja = auxtF.getAltura()-10;
				auxtI.bajaAltura();
				auxtF.subeAltura();
				band = false;
				band2 = true;
			}

			int x = auxDisc.getX()+(auxDisc.getW()/2);
			int y = auxDisc.getY();

			if(y!=sube && x!=desplaza) 
				moves.discoSube(auxDisc);
			if(x!=desplaza && y==sube)
				moves.discoDesplazamiento(auxDisc, auxtI, auxtF);
			if(x==desplaza && y!=baja) 
				moves.discoBaja(auxDisc);
			if(y==baja && x==desplaza) {
				band2 = false;
			}
			view.lienzo.dibujaLienzo();
		}
	}

	public void reiniciar(){
		view.reiniciaVista();
		model.movimientos.clear();
		model.Hanoi('A', 'B', 'C', VistaHanoi.noDiscos);
		System.out.println("\n\n\nInicia de nuevo");
		model.muestraMov();
		view.iniciar.setEnabled(true);
		band = true; band2 = true;
		contMov=sube=desplaza=baja= 0;
		view.radio2.setSelected(true);
		moves = new Movimientos();
	}

	public void metRadioButton(ActionEvent evt) {
		if(evt.getSource() == view.radio1) {
			T.setDelay(67);
			return;
		}
		if(evt.getSource() == view.radio2) {
			T.setDelay(34);
			return;
		}
		if(evt.getSource() == view.radio3) {
			T.setDelay(17);
			return;
		}
	}

}
