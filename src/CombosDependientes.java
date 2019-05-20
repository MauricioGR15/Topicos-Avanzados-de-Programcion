import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class CombosDependientes extends JFrame implements ItemListener{
	JComboBox CmbEdos,CmbCds,CmbCols;
	String [][] MCds={{"Tijuana","Mexicali","Tecate","Ensenada","Rosarito"},
			{"La Paz","Los Cabos"},{"Hermosillo","Obregon","Navojoa","gUAYMAS"},
			{"Culiacán","Mazatlán","LosMochis","Guasave"},{}};
	
	String [][][] MCols= { { {"Tij1","Tij2","Tij3","Tij4"},{"Mex1","Mex2"},{"Tec1","Tec2","Tec3"},
		{"Ens1","Ens2","Ens3"},{"Ros1"}}, {{"Paz1","Paz2","Paz3"},{"Cab1","Cab2","Cab3"}        }, {             }      };
	
	public CombosDependientes(){
		super("Combos dependientes");
		HazInterfaz();
		HazEscuchas();
	}
	private void HazInterfaz(){
		setLayout(new GridLayout(0,2,5,5));
		
		String []VEdos={"BC","BCS","SONORA","SINALOA","COLIMA","NAYARIT","JALISCO","MICHOACAN","OAXACA","GUERRERO","CHIAPAS"};
		
		CmbEdos=new JComboBox(VEdos);
		CmbEdos.insertItemAt("Seleccione",0);
	//	CmbEdos.setSelectedIndex(0);
		CmbEdos.setSelectedItem("Seleccione");

		CmbCds=new JComboBox();
		CmbCols=new JComboBox();
		
		add(new JLabel("Estados",SwingConstants.RIGHT));
		add(CmbEdos);

		add(new JLabel("Ciudades",SwingConstants.RIGHT));
		add(CmbCds);
		add(new JLabel("Colonias",SwingConstants.RIGHT));
		add(CmbCols);
		
		setSize(300,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	private void HazEscuchas(){
		CmbEdos.addItemListener(this);
		CmbCds.addItemListener(this);
		CmbCols.addItemListener(this);
	}
	public static void main(String [] a){
		new CombosDependientes();
	}
	@Override
	public void itemStateChanged(ItemEvent Evt) {
		if(Evt.getStateChange()!=ItemEvent.SELECTED)
			return;
		
		
		if(Evt.getSource()==CmbEdos){
			System.out.println("escuchando al combo estados");	
			CmbCds.removeItemListener(this);
			int IdEdo=CmbEdos.getSelectedIndex()-1;	
			CmbCds.removeAllItems();
			CmbCols.removeAllItems();
			
			
			
			for(int i=0 ; i<MCds[IdEdo].length ; i++)
				CmbCds.addItem(MCds[IdEdo][i]);
	//		CmbCds.setModel(new ComboBoxModel<String>(Arrays.asList( MCds[IdEdo])));
			CmbCds.insertItemAt("Seleccione",0);
			CmbCds.setSelectedIndex(0);
			CmbCds.addItemListener(this);
			return;
		}
		if(Evt.getSource()==CmbCds){
			System.out.println("escuchando al combo ciudades");
			int IdEdo=CmbEdos.getSelectedIndex()-1;	
			int IdCd=CmbCds.getSelectedIndex()-1;
			CmbCols.removeAllItems();
			for(int k=0 ; k<MCols[IdEdo][IdCd].length ; k++)
				CmbCols.addItem(MCols[IdEdo][IdCd][k]);
			
			CmbCols.insertItemAt("Seleccione",0);
			CmbCols.setSelectedIndex(0);
			return;
		}
		
	}
}
