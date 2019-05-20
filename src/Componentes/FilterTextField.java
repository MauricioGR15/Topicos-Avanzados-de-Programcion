package Componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FilterTextField extends JPanel{
	
	private JComboBox combo;
	private JButton asc, desc;
	
	public FilterTextField() {
		setLayout(new GridLayout(0,3,5,5));
	}
	
	public void  hacerPanel() {
		combo = new JComboBox();
		asc = new JButton("asc");
		desc = new JButton("desc");
		
		combo.setEditable(true);
		
		add(combo);
		add(asc);
		add(desc);
		
	}
	
	
	
	
	
	
	
	

}
