package AppContabilidad;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.*;


public class Vista extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public static final int NO_EXISTE = 0, INCOMPLETO = 1, GUARDADO = 2, MODIFICADO = 3, BAJA = 4, EXPRESION = 5,
			YA_EXISTE = 6, NO_CUENTA_MAYOR = 7, POLIZA_EXISTE = 8, DESBALANCEADA= 9;
	private JMenuItem agrega,modificaItm,baja,consulta,poliza;
	private JPanel pnlAgregar,pnlModificar,pnlBaja,pnlPoliza;
	private JDialog tabla;
	private JPanel contenido;

	//Panel agregar
	private JButton btnGrabar,btnLimpiar;
	private JTextField tfCuenta,tfNombre,tfSaldo;
	//Panel modificar
	private JComboBox <String>cbCuentas;
	private JTextField tfNombreMod;
	private JLabel lblCuenta,lblSaldo;
	private JButton btnEfectuarCambio;
	private ArrayList<String> listaC;
	//Panel baja
	private JComboBox <String>cbCuentasBaja;
	private JButton btnBaja;
	private JLabel lblNombreBaja,lblCuentaBaja,lblSaldoBaja;
	//Panel poliza
	private JRadioButton rCargo,rAbono;
	private JTextField ssCuenta, nomPoliza,cant,tfNoPol;
	private JButton btnPol,btnAgrega,btnLimpiarPol;
	private ButtonGroup grupo;
	private JLabel lblSuma;
	private ArrayList<Cuenta> arrayCuentas;
	private Font buF;

	public Vista() {
		super("Programa de Contabilidad");
		try {
			setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception e) {}
		hazInterfaz();
	}

	public void hazInterfaz() {
		setSize(600,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		hacerMenu();
		panelAgregar();
		panelModificar();
		panelBaja();

	}

	public void hacerMenu() {
		JMenuBar barra = new JMenuBar();
		setJMenuBar(barra);

		JMenu catalogo = new JMenu("Catálogo");
		agrega = new JMenuItem("Alta");
		baja = new JMenuItem("Baja");
		modificaItm = new JMenuItem("Modifica");
		consulta = new JMenuItem("Consulta");
		catalogo.add(agrega);
		catalogo.add(baja);
		catalogo.add(modificaItm);
		catalogo.add(consulta);

		JMenu polizas = new JMenu("Pólizas");
		poliza = new JMenuItem("Capturar");
		polizas.add(poliza);

		barra.add(catalogo);
		barra.add(polizas);
	}

	public JPanel panelAgregar() {
		pnlAgregar = new JPanel();
		pnlAgregar.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		btnGrabar = new JButton("Grabar");
		btnLimpiar = new JButton("Limpiar");
		tfCuenta = new JTextField(9);
		tfNombre = new JTextField(20);
		tfSaldo = new JTextField(20);

		c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.LINE_END; c.insets = new Insets(2,2,2,2);
		pnlAgregar.add(new JLabel("Cuenta: "),c);
		c.gridy++;
		pnlAgregar.add(new JLabel("Nombre: "),c);
		c.gridy++;
		pnlAgregar.add(new JLabel("Saldo: "),c);
		c.gridx = 1; c.gridy=0; c.anchor = GridBagConstraints.LINE_START;
		pnlAgregar.add(tfCuenta,c);
		c.gridy++;
		pnlAgregar.add(tfNombre,c);
		c.gridy++; c.gridx = 1;
		pnlAgregar.add(tfSaldo,c);
		c.gridx = 0;c.gridy = 3; c.gridwidth = 1;c.anchor = GridBagConstraints.CENTER;
		pnlAgregar.add(btnGrabar,c);
		c.gridx++;
		pnlAgregar.add(btnLimpiar,c);
		
		return pnlAgregar;
	}
	
	public JPanel panelBaja() {

		pnlBaja = new JPanel();
		pnlBaja.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		cbCuentasBaja = new JComboBox<String>();
		
		c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.insets = new Insets(2,2,2,2);
		pnlBaja.add(cbCuentasBaja,c);
		c.gridy++; c.gridwidth = 1; c.anchor = GridBagConstraints.LINE_END;
		pnlBaja.add(new JLabel("Cuenta: "),c);
		c.gridy++;
		pnlBaja.add(new JLabel("Nombre: "),c);
		c.gridy++;
		pnlBaja.add(new JLabel("Saldo: "),c);
		c.gridx = 1; c.gridy = 1; c.anchor = GridBagConstraints.LINE_START;
		pnlBaja.add(lblCuentaBaja = new JLabel("0"),c);
		c.gridy++;
		pnlBaja.add(lblNombreBaja = new JLabel("0"),c);
		c.gridy++;
		pnlBaja.add(lblSaldoBaja = new JLabel("0"),c);
		c.gridx = 0; c.gridy++; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
		pnlBaja.add(btnBaja = new JButton("Dar de baja"),c);

		return pnlBaja;
	}

	public JPanel panelModificar() {

		pnlModificar = new JPanel();
		pnlModificar.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		cbCuentas = new JComboBox<String>();
		listaC = new ArrayList<String>();
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		pnlModificar.add(cbCuentas,c);
		c.gridwidth = 1;
		c.gridy = 1;
		c.ipadx = 10;
		c.ipady = 5;
		c.anchor = GridBagConstraints.LINE_END;
		pnlModificar.add(new JLabel("Cuenta: "),c);
		c.gridy = 2;
		pnlModificar.add(new JLabel("Nombre: "),c);
		c.gridy = 3;
		pnlModificar.add(new JLabel("Saldo: "),c);
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 2;
		c.gridy = 1;
		pnlModificar.add(lblCuenta = new JLabel("0"),c);
		c.gridy = 2;
		pnlModificar.add(tfNombreMod = new JTextField(15),c);
		c.gridy = 3;
		pnlModificar.add(lblSaldo = new JLabel("0"),c);
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 4;
		pnlModificar.add(btnEfectuarCambio = new JButton("Modificar"),c);

		return pnlModificar;
	}
	
	public JPanel panelPoliza() {
		pnlPoliza = new JPanel();
		pnlPoliza.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		rCargo = new JRadioButton("Cargo",true);
		rAbono = new JRadioButton("Abono");
		ssCuenta = new JTextField(9);
		nomPoliza = new JTextField(20);
		cant = new JTextField(15);
	    tfNoPol = new JTextField(5);
		grupo = new ButtonGroup();
		grupo.add(rCargo);
		grupo.add(rAbono);
		
		JPanel norte = new JPanel(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.anchor = GridBagConstraints.LINE_END; c.weighty = 1;
		con.gridx = 0; con.gridy = 0; con.insets = new Insets(2,2,2,2);
		norte.add(new JLabel("NoPoliza: "),con);
		con.gridy++;
		norte.add(new JLabel("SSCuenta: "),con);
		con.gridy++;
		norte.add(new JLabel("Nombre: "),con);
		con.anchor = GridBagConstraints.LINE_START;
		con.gridx = 1; con.gridy = 0;
		norte.add(tfNoPol,con);
		con.gridy++;
		norte.add(ssCuenta,con);
		con.gridy++;
		norte.add(nomPoliza,con);
		con.gridx = 2; con.gridy = 0;
		con.anchor = GridBagConstraints.CENTER;
		norte.add(rCargo,con);
		con.gridx++;
		norte.add(rAbono,con);
		con.gridx = 2; con.gridy = 2; con.anchor = GridBagConstraints.LINE_END;
		norte.add(new JLabel("Importe: "),con);
		con.gridx++; 
		norte.add(cant,con);
		
		c.gridx = 0; c.gridy = 0; c.gridheight = 1; c.gridwidth = 4;
		c.fill = GridBagConstraints.BOTH; c.anchor = GridBagConstraints.NORTH; 
		pnlPoliza.add(norte,c);
		
		JPanel sur = new JPanel(new GridLayout(1,5,5,5));
		sur.add(new JLabel("SSCuenta"));
		sur.add(new JLabel("NOMBRE"));
		sur.add(new JLabel("IMPORTE"));
		sur.add(new JLabel("TIPO"));
		sur.add(new JLabel("ACTIVA"));
		c.gridy = 1; c.gridheight = 1; c.gridwidth = 4; c.anchor = GridBagConstraints.NORTH;
		pnlPoliza.add(sur,c);
		
		contenido = new JPanel(new GridLayout(0,5,5,5));
		JScrollPane scroll = new JScrollPane(contenido);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		c.gridy = 2; c.gridheight = 1; c.gridwidth = 4; c.fill = GridBagConstraints.BOTH;
		pnlPoliza.add(scroll,c);
		
		c.gridx =2; c.gridy = 7; c.weighty = 1;  c.fill = GridBagConstraints.NONE;
		pnlPoliza.add(new JLabel("Suma: "),c);
		c.gridx++; 
		pnlPoliza.add(lblSuma = new JLabel("0"),c);
		
		c.gridx = 2; c.gridy = 8; c.gridheight =1; c.gridwidth = 1; c.anchor = GridBagConstraints.SOUTH;
		c.fill = GridBagConstraints.NONE;
		pnlPoliza.add(btnAgrega = new JButton("Agregar"),c);
		c.gridx = 3;
		pnlPoliza.add(setBtnLimpiarPol(new JButton("Limpiar")),c);
		c.gridx = 4;
		pnlPoliza.add(btnPol = new JButton("Efectuar"),c);
		
		return pnlPoliza;
	}
	
	public void actualizaLblSuma(double cant,char tipo) {
		double actual = Double.parseDouble(lblSuma.getText());
		double suma;
		if(tipo == 'A')
			suma = actual - cant;
		else
			suma = actual + cant;
		lblSuma.setText(suma+"");
	}
	
	public void panelCosultas() {
		tabla = new JDialog();
		tabla.setTitle("Consulta");
		tabla.setLayout(new GridLayout(0,6,5,5));
		JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6;
		tabla.add(lbl1 = new JLabel("Cuenta"));
		tabla.add(lbl2 = new JLabel("Nombre"));
		tabla.add(lbl3 = new JLabel("Saldo"));
		tabla.add(lbl4 = new JLabel("Cargo"));
		tabla.add(lbl5 = new JLabel("Abono"));
		tabla.add(lbl6 = new JLabel("Activa"));
		
		lbl1.setFont(new Font("Arial",Font.BOLD,14));
		lbl2.setFont(new Font("Arial",Font.BOLD,14));
		lbl3.setFont(new Font("Arial",Font.BOLD,14));
		lbl4.setFont(new Font("Arial",Font.BOLD,14));
		lbl5.setFont(new Font("Arial",Font.BOLD,14));
		lbl6.setFont(new Font("Arial",Font.BOLD,14));
		
		
		for(int i=0; i<arrayCuentas.size(); i++) {
			Cuenta aux = arrayCuentas.get(i);
			tabla.add(new JLabel(aux.getNumCue()));
			tabla.add(new JLabel(aux.getNombre()));
			tabla.add(new JLabel(aux.getSaldo()+""));
			tabla.add(new JLabel(aux.getCargo()+""));
			tabla.add(new JLabel(aux.getAbono()+""));
			tabla.add(new JLabel(aux.isActivo()?"Activa":"Inactiva"));
		}
		
		tabla.pack();
		tabla.setLocationRelativeTo(null);
		tabla.setModal(true);
		tabla.setVisible(true);
	}

	public void llenaComboCuentas(ArrayList <String>list) {
		cbCuentas.addItem("Seleccione");
		for(int i=0;i<list.size();i++) 
			cbCuentas.addItem(list.get(i));
	}

	public void llenaComboCuentasBaja(ArrayList <String>list) {
		cbCuentasBaja.addItem("Seleccione");
		for(int i=0;i<list.size();i++)
			cbCuentasBaja.addItem(list.get(i));
	}

	public void agregarContenido(Cuenta ssC,double cant ,char tipo) {
		contenido.add(new JLabel(ssC.getNumCue().trim()));
		contenido.add(new JLabel(ssC.getNombre().trim()));
		contenido.add(new JLabel(cant+""));
		contenido.add(new JLabel(tipo+""));
		contenido.add(new JLabel(ssC.isActivo()?"Activa":"Inactiva"));
		contenido.updateUI();
	}
	
	public void limpiaFrame() {
		this.getContentPane().removeAll();
		this.validate();
		this.update(getGraphics());
	}

	public void limpiaTF() {
		getTfNombre().setText("");
		getTfCuenta().setText("");
		getTfSaldo().setText("");
	}
	
	public void limpiaPol() {
		ssCuenta.setText("");
		cant.setText("");
	}

	public void actualizaInfoMod(String noC, String nom, double sal) {
		lblCuenta.setText(noC);
		tfNombreMod.setText(nom);
		lblSaldo.setText(sal+"");
	}
	
	public void actualizaInfoBaja(String noC,String nom, double sal) {
		lblCuentaBaja.setText(noC);
		lblNombreBaja.setText(nom);
		lblSaldoBaja.setText(sal+"");
	}
	
	public int pregunta() {
		return JOptionPane.showConfirmDialog(this, "¿Está seguro?", "¡ALERTA!", JOptionPane.YES_NO_OPTION);
	}
	
	public boolean verificaTextFields () {
		String aux1,aux2,aux3;
		aux1 = tfNombre.getText().trim();
		aux2 = tfCuenta.getText().trim();
		aux3 = tfSaldo.getText().trim();
		if(aux1.isEmpty() || aux2.isEmpty() || aux3.isEmpty()) {
			JOptionPanes(INCOMPLETO);
			return false;
		}
		if(!(aux2.length() == 6 || aux2.length() == 3 || aux2.length() == 9)) {
			JOptionPanes(INCOMPLETO);
			return false;
		}
		return true;
	}
	
	public boolean verificaTFPolizas() {
		String aux,aux1,aux2,aux3;
		aux = tfNoPol.getText().trim();
		aux1 = ssCuenta.getText().trim();
		aux2 = nomPoliza.getText().trim();
		aux3 = cant.getText().trim();
		
		if(aux.isEmpty() || aux1.isEmpty() || aux2.isEmpty() || aux3.isEmpty()) {
			JOptionPanes(INCOMPLETO);
			return false;
		}
		
		if(aux1.length() != 9) {
			JOptionPanes(INCOMPLETO);
			return false;
		}
		
		return true;
	}
	
	public void JOptionPanes(int c) {
		switch(c) {
		case NO_EXISTE:
			JOptionPane.showMessageDialog(this, "No existe ningún dato registrado", "¡ALERTA!", JOptionPane.WARNING_MESSAGE);
			break;
		case INCOMPLETO:
			JOptionPane.showMessageDialog(this, "Por favor, ingrese bien los datos","",JOptionPane.WARNING_MESSAGE);
			break;
		case GUARDADO:
			JOptionPane.showMessageDialog(this, "Cuenta guardada con éxito","",JOptionPane.INFORMATION_MESSAGE);
			break;
		case MODIFICADO:
			JOptionPane.showMessageDialog(this, "Cuenta modficada con éxito","",JOptionPane.INFORMATION_MESSAGE);
			break;
		case BAJA:
			JOptionPane.showMessageDialog(this,"Se hizo la baja con éxito","",JOptionPane.INFORMATION_MESSAGE);
			break;
		case EXPRESION:
			JOptionPane.showMessageDialog(this,"Carácterer inválido", "", JOptionPane.ERROR_MESSAGE);
			break;
		case YA_EXISTE:
			JOptionPane.showMessageDialog(this, "Esta cuenta ya existe o no tiene cuenta mayor, intente de nuevo", "", JOptionPane.ERROR_MESSAGE);
			break;
		case NO_CUENTA_MAYOR: 
			JOptionPane.showMessageDialog(this, "No existen cuentas de mayor o la SubSubCuenta ya existe", "", JOptionPane.ERROR_MESSAGE);
			break;
		case POLIZA_EXISTE:
			JOptionPane.showMessageDialog(this, "Este número de Póliza ya existe, intente de nuevo", "", JOptionPane.ERROR_MESSAGE);
			break;
		case DESBALANCEADA: 
			JOptionPane.showMessageDialog(this, "La póliza no está balanceada", "", JOptionPane.WARNING_MESSAGE);
			break;
		}
	}
	
	public boolean verificaTF() {
		String aux = tfNombreMod.getText().trim();
		if(aux.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, ingrese bien los datos");
			return false;
		}
		return true;
	}

	public JMenuItem getAgrega() {
		return agrega;
	}

	public JMenuItem getModifica() {
		return modificaItm;
	}

	public JMenuItem getBaja() {
		return baja;
	}

	public JMenuItem getConsulta() {
		return consulta;
	}

	public JMenuItem getCaptura() {
		return poliza;
	}

	public JPanel getPnlAgregar() {
		return pnlAgregar;
	}

	public JPanel getPnlModificar() {
		return pnlModificar;
	}

	public JPanel getPnlBaja() {
		return pnlBaja;
	}

	public JButton getBtnGrabar() {
		return btnGrabar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public JTextField getTfCuenta() {
		return tfCuenta;
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JTextField getTfSaldo() {
		return tfSaldo;
	}

	public JComboBox<String> getCbCuentas() {
		return cbCuentas;
	}

	public JTextField getTfNombreMod() {
		return tfNombreMod;
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public JLabel getLblSaldo() {
		return lblSaldo;
	}

	public JButton getBtnEfectuarCambio() {
		return btnEfectuarCambio;
	}

	public JComboBox<String> getCbCuentasBaja() {
		return cbCuentasBaja;
	}

	public JButton getBtnBaja() {
		return btnBaja;
	}

	public ArrayList<String> getListaC() {
		return listaC;
	}

	public void setListaC(ArrayList<String> listaC) {
		this.listaC = listaC;
	}

	public JLabel getLblNombreBaja() {
		return lblNombreBaja;
	}

	public JLabel setLblNombreBaja(JLabel lblNombreBaja) {
		this.lblNombreBaja = lblNombreBaja;
		return lblNombreBaja;
	}

	public Font getBuF() {
		return buF;
	}

	public void setBuF(Font buF) {
		this.buF = buF;
	}

	public JMenuItem getPoliza() {
		return poliza;
	}

	public void setPoliza(JMenuItem poliza) {
		this.poliza = poliza;
	}

	public JPanel getPnlPoliza() {
		return pnlPoliza;
	}

	public JRadioButton getrCarga() {
		return rCargo;
	}

	public JRadioButton getrAbono() {
		return rAbono;
	}

	public JTextField getSsCuenta() {
		return ssCuenta;
	}

	public JTextField getNomPoliza() {
		return nomPoliza;
	}

	public JTextField getCant() {
		return cant;
	}

	public JButton getBtnPol() {
		return btnPol;
	}
	
	public JLabel getlblCuentaBaja() {
		return lblCuentaBaja;
	}

	public JTextField getTfNoPol() {
		return tfNoPol;
	}

	public JButton getBtnAgrega() {
		return btnAgrega;
	}

	public void setBtnAgrega(JButton btnAgrega) {
		this.btnAgrega = btnAgrega;
	}
	public JPanel getContenido() {
		return contenido;
	}

	public void setContenido(JPanel contenido) {
		this.contenido = contenido;
	}
	public ArrayList<Cuenta> getArrayCuentas() {
		return arrayCuentas;
	}

	public void setArrayCuentas(ArrayList<Cuenta> arrayCuentas) {
		this.arrayCuentas = arrayCuentas;
	}

	public JButton getBtnLimpiarPol() {
		return btnLimpiarPol;
	}

	public JButton setBtnLimpiarPol(JButton btnLimpiarPol) {
		this.btnLimpiarPol = btnLimpiarPol;
		return btnLimpiarPol;
	}

	public JLabel getLblSuma() {
		return lblSuma;
	}

	public void setLblSuma(JLabel lblSuma) {
		this.lblSuma = lblSuma;
	}



}
