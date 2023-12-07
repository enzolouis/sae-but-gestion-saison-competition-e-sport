package style;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomJPanel extends JPanel {
	
    public CustomJPanel() {
    	super();
    	
    	this.setBackground(new Color(44, 47, 51));
    	this.setBorder(new EmptyBorder(0, 0, 0, 0));
    	this.setLayout(new BorderLayout(0, 0));
    }
    
	public CustomJPanel(FlowLayout layout) {
    	super();
    	
    	this.setBackground(new Color(44, 47, 51));
    	this.setBorder(new EmptyBorder(0, 0, 0, 0));
    	this.setLayout(layout);
	}
    
    public CustomJPanel(EmptyBorder border) {
    	super();
    	
    	this.setBackground(new Color(44, 47, 51));
    	this.setLayout(new BorderLayout(0, 0));
    	this.setBorder(border);
    }
    
    public CustomJPanel(EmptyBorder border, GridLayout layout) {
    	super();
    	
    	this.setBackground(new Color(44, 47, 51));
    	this.setBorder(border);
    	this.setLayout(layout);
    }
    
    public CustomJPanel(EmptyBorder border, FlowLayout layout) {
    	super();
    	
    	this.setBackground(new Color(44, 47, 51));
    	this.setBorder(border);
    	this.setLayout(layout);
    }
    
    public CustomJPanel(EmptyBorder border, GridBagLayout layout) {
    	super();
    	
    	this.setBackground(new Color(44, 47, 51));
    	this.setBorder(border);
    	this.setLayout(layout);
    }
}
