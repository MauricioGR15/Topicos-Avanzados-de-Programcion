package Componentes;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.RandomAccessFile;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class JComboBoxEMC extends JPanel implements ItemListener {
	
	private class IndexLugar {
		int id;
		String nombre;
		
		public IndexLugar(int id, String nombre) {
			this.id = id;
			this.nombre = nombre;
		}
		
		public int getId() {
			return id;
		}
		
		public String toString() {
			return nombre;
		}
	}
	
	public final static int HORIZONTAL = 0, VERTICAL = 1;
	public final static int ESTADO = 0, MUNICIPIO = 1, CIUDAD = 2;
	
	private final File FILE_ESTADOS = new File("estados.dat"),
			FILE_MUNICIPIOS = new File("municipios.dat"),
			FILE_CIUDADES = new File("ciudades.dat");
	
	private final int LONG_ESTADO = 104, LONG_MUNICIPIO = 108, LONG_CIUDAD = 112;
	private JComboBox<IndexLugar> cbEstados, cbMunicipios, cbCiudades;
	
	
	/**
	 * El componente JComboBoxEMC es un panel que contiene tres etiquetas y tres combos, cada uno respectivamente
	 * con el tipo de información que contiene (Estado, Municipios, Ciudades). El combo Estado es el padre y de éste 
	 * dependerá la información desplegada en el combo Municipios, el combo Ciudades es dependiente del combo Municipios.<br>
	 * 
	 * Constructor por defecto del componente JComboBoxEMC. Se crea por defecto sin ningún Item seleccionado 
	 * en el combo padre Estado. La alineación por defecto es Horizontal.
	 */
	public JComboBoxEMC() {
		this("", "", 0);
	}
	
	
	/**
	 * Constructor sobrecargado para crear el componente con una alineación deseada, ya sea horizontal o vertical.
	 * @param alineacion - Constante para alinear los combos en horizontal o vertical. Las constantes son las siguientes
	 *  <i>HORIZONTAL</i>, <i>VERTICAL</i>.
	 */
	public JComboBoxEMC(int alineacion) {
		this("", "", alineacion);
	}
	
	/**
	 * Constructor sobrecargado para crear el componente con un Estado seleccionado en el combo Estado.
	 * @param estado - Estado para mostrarse como seleccionado en el combo Estado.
	 */
	public JComboBoxEMC(String estado) {
		this(estado, "", 0);
	}
	
	/**
	 * Constructor sobrecargado para crear el componente con un Estado seleccionado en el combo Estado y 
	 * con una alineación ya sea horizontal o vertical.
	 * @param estado - Estado para mostrarse como seleccionado en el combo Estado.
	 * @param alineacion - Constante para alinear los combos en horizontal o vertical. Las constante son las siguientes
	 * <i>HORIZONTAL</i>, <i>VERTICAL</i>.
	 */
	public JComboBoxEMC(String estado, int alineacion) {
		this(estado, "", alineacion);
	}
	
	/**
	 * 
	 * Constructor sobrecargado para crear el componente con un Estado seleccionado en el combo Estado y también 
	 * para que el combo Municipios tenga una Municipio seleccionado.<br>
	 * <p>
	 * <b>Preacaución</b>: Se debe seleccionar un municipio válido que exista para el Estado.
	 * 
	 * @param estado - Estado para mostrarse como seleccionado en el combo Estado.
	 * @param municipio - Municipio para mostrarse como seleccionado en el combo Municipio.
	 */
	public JComboBoxEMC(String estado, String municipio) {
		this(estado, municipio, 0);
	}
	
	
	/**
	 * Constructor sobrecargado más general con las opciones de crear el componente con un Estado seleccionado en el
	 * combo Estado, un Municipio seleccionado en el combo Municipio y una alineación para mostrar los combos y etiquetas.
	 * 
	 * <p><b>Precaución:</b> Se debe seleccionar un municipio válido que exista para el el Estado.
	 * 
	 * @param estado - Estado para mostrarse como seleccionado en el combo Estado.
	 * @param municipio - Municipio para mostrarse como seleccionado en el combo Municipio.
	 * @param alineacion - Constante para alinear los combos en horizontal o vertical. Las constante son las siguientes
	 * <i>HORIZONTAL</i>, <i>VERTICAL</i>.
	 */
	public JComboBoxEMC(String estado, String municipio, int alineacion) {
		if(alineacion == HORIZONTAL)
			setLayout(new GridLayout(1, 6));
		else 
			setLayout(new GridLayout(3, 2));
		
		add(new JLabel("Estados: ", JLabel.CENTER));
		add(cbEstados = new JComboBox<IndexLugar>());
		add(new JLabel("Municipios: ", JLabel.CENTER));
		add(cbMunicipios = new JComboBox<IndexLugar>());
		add(new JLabel("Ciudades: ", JLabel.CENTER));
		add(cbCiudades = new JComboBox<IndexLugar>());

		int idEstado = llenarEstados(estado);
		if(idEstado > 0) {
			int idMunicipio = llenarMunicipios(idEstado, municipio);
			if(idMunicipio > 0) 
				llenarCiudades(idEstado, idMunicipio);
		}
		
		cbEstados.addItemListener(this);
		cbMunicipios.addItemListener(this);
		cbCiudades.addItemListener(this);
	}
	
	/**
	 * Retorna el toString() del objeto actual seleccionado del JComboBox.
	 * Se tienen que usar las constantes:
	 * <p>
	 * <i><b>ESTADO:</i></b> Obtiene el Item seleccionado del combo Estado.<br>
	 * <i><b>MUNICIPIO:</i></b> Obtiene el Item seleccionado del combo Municipio.<br>
	 * <i><b>CIUDAD:</i></b> Obtiene el Item seleccionado del combo Ciudad.<br>
	 * 
	 * @param lugar - La constante respectiva del combo del cual se va a obtener el toString() del Item seleccionado.
	 * 
	 * @return Retorna el toString() del objeto seleccionado.
	 */
	public String getSelected(int lugar) {
		IndexLugar il = null;
		switch(lugar) {
			case ESTADO: 
				il = (IndexLugar) cbEstados.getSelectedItem();
				break;
			case MUNICIPIO: 
				il = (IndexLugar) cbMunicipios.getSelectedItem();
				break;
			case CIUDAD:
				il = (IndexLugar) cbCiudades.getSelectedItem();
				break;
		}
		
		
		if(il != null && il.id !=0)
			return il.toString();
		
		return null;
	}
	

	private int llenarEstados(String est)  {
		int idEstado = 0;
		try {
			RandomAccessFile estadosFile = new RandomAccessFile(FILE_ESTADOS, "r");
			int nEstados = (int)((estadosFile.length())/LONG_ESTADO);
			char[] temp;
			int id;
			String estado;
			IndexLugar il;
			
			cbEstados.addItem(new IndexLugar(0, "Seleccione: "));
			for(int i = 0; i < nEstados; i++) {
				id = estadosFile.readInt();
				temp = new char[50];
				for (int j = 0; j < temp.length; j++)
					temp[j] = estadosFile.readChar();
				estado = new String(temp);
				
				il = new IndexLugar(id, estado);
				cbEstados.addItem(il);
				if(est.equalsIgnoreCase(estado.trim())) {
					cbEstados.setSelectedItem(il);
					idEstado = id;
				}
			}
			
			estadosFile.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return idEstado;
	}
	
	private int llenarMunicipios(int estado, String mun) {
		int idMunicipio = 0;
		cbMunicipios.removeAllItems();

		try {
			RandomAccessFile municipiosFile = new RandomAccessFile(FILE_MUNICIPIOS, "r");
			int nMunicipios = (int)(municipiosFile.length()/LONG_MUNICIPIO);
			System.out.println("municipios: " +nMunicipios);
			char[] temp;
			int idEstado, id;
			String municipio;
			IndexLugar il;
			
			cbMunicipios.addItem(new IndexLugar(0, "Seleccione: "));
			for(int i = 0; i < nMunicipios; i++) {
				idEstado = municipiosFile.readInt();
				id = municipiosFile.readInt();
				temp = new char[50];
				for (int j = 0; j < temp.length; j++)
					temp[j] = municipiosFile.readChar();
				municipio = new String(temp);
				
				il = new IndexLugar(id, municipio);
				if(estado == idEstado) {
					cbMunicipios.addItem(il);
					if(mun.equalsIgnoreCase(municipio.trim())) {
						cbMunicipios.setSelectedItem(il);
						idMunicipio = id;
					}
				}
			}

			municipiosFile.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return idMunicipio;
	}
	
	private void llenarCiudades(int estado, int municipio) {
		cbCiudades.removeAllItems();
		
		try {
			RandomAccessFile ciudadesFile = new RandomAccessFile(FILE_CIUDADES, "r");
			int nCiudades = (int)((ciudadesFile.length())/LONG_CIUDAD);
			char[] temp;
			int idEstado, idMunicipio, id;
			String ciudad;
			
			cbCiudades.addItem(new IndexLugar(0, "Seleccione: "));
			for(int i = 0; i < nCiudades; i++) {
				idEstado = ciudadesFile.readInt();
				idMunicipio = ciudadesFile.readInt();
				id = ciudadesFile.readInt();
				temp = new char[50];
				for (int j = 0; j < temp.length; j++)
					temp[j] = ciudadesFile.readChar();
				ciudad = new String(temp);
				
				if(estado == idEstado && municipio == idMunicipio)
					cbCiudades.addItem(new IndexLugar(id, ciudad));
			}

			ciudadesFile.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void itemStateChanged(ItemEvent evt) {
		if(evt.getStateChange() != ItemEvent.SELECTED)
			return;
		
		JComboBox<IndexLugar> cb = (JComboBox<IndexLugar>) evt.getSource();
		IndexLugar il = (IndexLugar)cb.getSelectedItem();
		
		if(cb == cbEstados) {
			cbMunicipios.removeAllItems();
			cbCiudades.removeAllItems();
			if(il.id > 0) {
				llenarMunicipios(il.id, "");
			}
		}
		else if(cb == cbMunicipios) {
			cbCiudades.removeAllItems();
			if(il.id > 0) 
				llenarCiudades(((IndexLugar)cbEstados.getSelectedItem()).id, il.id);
		}
	}
}