package AppContabilidad;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import utilidades.Rutinas;

public class Controlador implements ActionListener,ItemListener, FocusListener, KeyListener {

	Modelo modelo;
	Vista vista;
	private Vector<Cuenta> vector;

	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		hazEscuchadoresJMenuItems();
		vista.add(vista.panelAgregar());
		vista.setBuF(vista.getTfCuenta().getFont());
		hazEscuchadoresPanelAgrega();
	}

	private void hazEscuchadoresJMenuItems() {
		vista.getAgrega().addActionListener(this);
		vista.getBaja().addActionListener(this);
		vista.getModifica().addActionListener(this);
		vista.getConsulta().addActionListener(this);
		vista.getCaptura().addActionListener(this);
	}

	private void hazEscuchadoresPanelAgrega() {
		vista.getBtnLimpiar().addActionListener(this);
		vista.getBtnGrabar().addActionListener(this);
		vista.getBtnGrabar().addKeyListener(this);
		vista.getTfCuenta().addActionListener(this);
		vista.getTfCuenta().addFocusListener(this);
		vista.getTfCuenta().addKeyListener(this);
		vista.getTfNombre().addActionListener(this);
		vista.getTfNombre().addFocusListener(this);
		vista.getTfNombre().addKeyListener(this);
		vista.getTfSaldo().addActionListener(this);
		vista.getTfSaldo().addFocusListener(this);
		vista.getTfSaldo().addKeyListener(this);
	}

	private void hazEscuchadoresPanelBaja() {
		vista.getBtnBaja().addActionListener(this);
	}

	private void hazEscuchadoresPanelModifica() {
		vista.getTfNombreMod().addActionListener(this);
		vista.getBtnEfectuarCambio().addActionListener(this);
	}
	
	private void hazEscuchadoresPanelPoliza() {
		vista.getrAbono().addActionListener(this);
		vista.getrCarga().addActionListener(this);
		vista.getBtnPol().addActionListener(this);
		vista.getBtnPol().addFocusListener(this);
		vista.getBtnLimpiarPol().addActionListener(this);
		vista.getBtnLimpiarPol().addFocusListener(this);
		vista.getBtnAgrega().addActionListener(this);
		vista.getBtnAgrega().addFocusListener(this);
		vista.getTfNoPol().addKeyListener(this);
		vista.getTfNoPol().addFocusListener(this);
		vista.getrAbono().addKeyListener(this);
		vista.getrAbono().addFocusListener(this);
		vista.getrCarga().addKeyListener(this);
		vista.getrCarga().addFocusListener(this);
		vista.getSsCuenta().addKeyListener(this);
		vista.getSsCuenta().addFocusListener(this);
		vista.getNomPoliza().addKeyListener(this);
		vista.getNomPoliza().addFocusListener(this);
		vista.getCant().addKeyListener(this);
		vista.getCant().addFocusListener(this);
	}
	
	private char metRadios() {
		if(vista.getrAbono().isSelected()) 
			return 'A';
		return 'C';
	}
	
	Poliza auxPol = new Poliza(0,"");
	private void metBotones(ActionEvent evt) {
		
		if(evt.getSource() == vista.getBtnGrabar()) {
			String numC = Rutinas.PonBlancos(vista.getTfCuenta().getText(), 9);
			String nom = vista.getTfNombre().getText();
			double sal = (vista.getTfSaldo().getText().equals(""))?0:Double.parseDouble(vista.getTfSaldo().getText()) ;
			if(!vista.verificaTextFields())
				return;
			
			if(modelo.validaMayor(numC) && !modelo.Duplicada(numC)) {
				modelo.agregarCuenta(new Cuenta(numC,nom,sal));
				vista.JOptionPanes(Vista.GUARDADO);
				return;
			}
			vista.JOptionPanes(Vista.YA_EXISTE);
			
			return;
		}

		if(evt.getSource() == vista.getBtnLimpiar()) {
			vista.limpiaTF();
			vista.getTfCuenta().requestFocus();
			return;
		}

		if(evt.getSource() == vista.getBtnBaja()) {
			int num = 0;
			if(!modelo.archEstado()) {
				vista.JOptionPanes(Vista.NO_EXISTE);
				return;
			}
			num = vista.pregunta();
			switch(num) {
				case 0: 
					String cue = vista.getlblCuentaBaja().getText().trim();
					cue = Rutinas.PonBlancos(cue, 6);
					modelo.bajaCuenta(cue);
					vista.JOptionPanes(Vista.BAJA);
					break;
				case 1: break;
			}
			return;
		}
		
		if(evt.getSource() == vista.getBtnEfectuarCambio()) {
			if(!modelo.archEstado()) {
				vista.JOptionPanes(Vista.NO_EXISTE);
				return;
			}
			String nuevo = vista.getTfNombreMod().getText();
			if(!vista.verificaTF())
				 return;
			nuevo = Rutinas.PonBlancos(nuevo, 20);
			modelo.modificaCuenta(nuevo,modelo.getPosMod());
			vista.JOptionPanes(Vista.MODIFICADO);
			return;
		}
		
		if(evt.getSource() == vista.getBtnAgrega()) {
			String ssCuenta = vista.getSsCuenta().getText();
			double cant = (vista.getCant().getText().equals(""))?0:Double.parseDouble(vista.getCant().getText());
			char tipo = metRadios();
			if(!vista.verificaTFPolizas())
				return;
			
			Cuenta cue = modelo.getCuenta(ssCuenta);
			
			if(cue == null) {
				vista.JOptionPanes(Vista.NO_EXISTE);
				return;
			}
			if(tipo == 'C') 
				cue.setCargo(cant);
			else
				cue.setAbono(cant);
			
			vista.agregarContenido(cue, cant, tipo);
			vector.add(cue);
			auxPol.calcularSuma(tipo, cant);
			vista.actualizaLblSuma(cant,tipo);
			vista.getTfNoPol().setEnabled(false);
			return;
		}
		if(evt.getSource() == vista.getBtnPol()) {
			
			int noPol = vista.getTfNoPol().getText().equals("")?0: Integer.parseInt(vista.getTfNoPol().getText());
			String nomPol = vista.getNomPoliza().getText();
			if(auxPol.getSuma() !=0) {
				vista.JOptionPanes(Vista.DESBALANCEADA);
				return;
			}
			
			if(!modelo.polizaRepetida(noPol)) {
				auxPol.setNoPol(noPol);
				auxPol.setNombrePol(nomPol);
				modelo.agregarPoliza(auxPol);
				auxPol = new Poliza(0,"");
				modelo.afectacion(vector);
				JOptionPane.showMessageDialog(vista, "Han sido afectadas " + modelo.getContAfect() + " cuentas");
				vector.clear();
				vista.getTfNoPol().setEnabled(true);
				vista.getContenido().removeAll();
				vista.getLblSuma().setText("0");
				vista.getContenido().updateUI();
				return;
			}
			
			vista.JOptionPanes(Vista.POLIZA_EXISTE);
			
			return;
		}
		
		if(evt.getSource() == vista.getBtnLimpiarPol()) {
			vista.limpiaPol();
			return;
		}
	}


	private void metJMenuItem(ActionEvent evt) {
		if(evt.getSource() == vista.getAgrega()) {
			vista.limpiaFrame();
			vista.setSize(600, 200);
			vista.add(vista.panelAgregar());
			vista.setBuF(vista.getTfCuenta().getFont());
			hazEscuchadoresPanelAgrega();
			vista.getPnlAgregar().updateUI();
			return;
		}
		if(evt.getSource() == vista.getBaja()) {
			vista.limpiaFrame();
			vista.setSize(600, 200);
			vista.add(vista.panelBaja());
			hazEscuchadoresPanelBaja();
			vista.getPnlBaja().updateUI();
			ArrayList<String> array = modelo.llenaArrayList();
			if(array != null) {
				vista.llenaComboCuentasBaja(array);
				vista.getCbCuentasBaja().addItemListener(this);
			}
			return;
		}

		if(evt.getSource() == vista.getModifica()) {
			vista.limpiaFrame();
			vista.setSize(600, 200);
			vista.add(vista.panelModificar());
			hazEscuchadoresPanelModifica();
			vista.getPnlModificar().updateUI();
			ArrayList<String> array = modelo.llenaArrayList();
			if(array != null) {
				vista.llenaComboCuentas(array);
				vista.getCbCuentas().addItemListener(this);
			}
			
			return;
		}
		if(evt.getSource() == vista.getConsulta()) {
			if(!modelo.archEstado()) {
				vista.JOptionPanes(Vista.NO_EXISTE);
				return;
			}
			
			vista.setArrayCuentas(modelo.getArrayCuentas());
			vista.panelCosultas();
			
			return;
		}
		
		if(evt.getSource() == vista.getPoliza()) {
			vista.limpiaFrame();
			vista.validate();
			vista.setSize(600, 400);
			vista.add(vista.panelPoliza());
			vector = new Vector<Cuenta>();
			auxPol = new Poliza(0,"");
			hazEscuchadoresPanelPoliza();
			vista.getPnlPoliza().updateUI();
		}
	}

	public void actionPerformed(ActionEvent evt) {
		metJMenuItem(evt);
		metBotones(evt);
	}
	
	public void itemStateChanged(ItemEvent evt) {
		if(evt.getStateChange()!=ItemEvent.SELECTED)
			return;
		if(evt.getSource() == vista.getCbCuentas()) {
			int posMod = vista.getCbCuentas().getSelectedIndex() -1;
			if(posMod == -1) return;
			Cuenta cue = modelo.getCuenta(posMod);
			if(cue == null) {
				vista.JOptionPanes(Vista.NO_EXISTE);
				return;
			}
			vista.actualizaInfoMod(cue.getNumCue(), cue.getNombre(), cue.getSaldo());
			vista.getTfNombreMod().requestFocus();
			vista.getTfNombreMod().selectAll();
			return;
		}
		if(evt.getSource() == vista.getCbCuentasBaja()) {
			int posMod = vista.getCbCuentasBaja().getSelectedIndex() - 1;
			if(posMod == -1) return;
			Cuenta cue = modelo.getCuenta(posMod);
			if(cue == null) { 
				vista.JOptionPanes(Vista.NO_EXISTE);
				return;
			}
			vista.actualizaInfoBaja(cue.getNumCue(), cue.getNombre(), cue.getSaldo());
			vista.getBtnBaja().requestFocus();
			return;
		}
	}

	public void focusGained(FocusEvent evt) {
		if(evt.getSource() instanceof JTextField) {
			JTextField aux = (JTextField)evt.getSource();
			aux.selectAll();
			aux.setFont(new Font("Century Gothic",Font.BOLD,12));
			return;
		}

	}

	public void focusLost(FocusEvent evt) {
		if(evt.getSource() instanceof JTextField) {
			JTextField aux = (JTextField)evt.getSource();
			aux.setFont(vista.getBuF());
			return;
		}


	}

	@Override
	public void keyPressed(KeyEvent evt) {
		if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
			if(evt.getSource() instanceof JTextField) {
				JTextField aux = (JTextField) evt.getSource();
				aux.transferFocus();
				return;
			}
			if(evt.getSource() instanceof JButton) {
				JButton aux = (JButton) evt.getSource();
				aux.doClick();
				aux.transferFocus();
				return;
			}
			if(evt.getSource() instanceof JRadioButton) {
				JRadioButton aux = (JRadioButton) evt.getSource();
				aux.doClick();
				aux.transferFocus();
				return;
			}
		}
	}
	
	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent evt) {
		if(evt.getSource()== vista.getTfCuenta()) {
			limTamañoNum(evt,9);
			return;
		}
		if(evt.getSource() == vista.getTfSaldo() || evt.getSource() == vista.getCant()) {
			limTamañoNum(evt,15);
			return;
		}
		if(evt.getSource() == vista.getTfNombre() || evt.getSource() == vista.getTfNombreMod() || evt.getSource()==vista.getNomPoliza()){
			limTamañoText(evt,20);
			return;
		}
		if(evt.getSource() == vista.getSsCuenta()) {
			limTamañoNum(evt,9);
			return;
		}
		if(evt.getSource() == vista.getTfNoPol()) {
			limTamañoNum(evt,5);
			return;
		}
	}
	
	private void limTamañoNum(KeyEvent evt, int tam) {
		JTextField auxText = (JTextField) evt.getSource();
		if(auxText.getText().length() == tam && evt.getKeyChar()!= KeyEvent.VK_ENTER ){
			evt.consume();
			Toolkit.getDefaultToolkit().beep();
			return;
		}
		if(evt.getKeyChar()<'0' || evt.getKeyChar()>'9' ){
			evt.consume();
			return;
		}
	}
	
	private void limTamañoText(KeyEvent evt, int tam) {
		JTextField auxText = (JTextField) evt.getSource();
		if(auxText.getText().length() == tam && evt.getKeyChar() != KeyEvent.VK_ENTER) {
			evt.consume();
			Toolkit.getDefaultToolkit().beep();
			return;
		}
	}

}
