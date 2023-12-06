package style;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomJPanel extends JPanel {
	
    public CustomJPanel() {
    	super();
    	
    	this.setBackground(new Color(44, 47, 51));
    	this.setBorder(new EmptyBorder(0, 0, 0, 0));
    	this.setLayout(new BorderLayout(0, 0));
    }
    
    public CustomJPanel(int[] border) {
    	super();
    	
    	this.setBackground(new Color(44, 47, 51));
    	this.setBorder(new EmptyBorder(border[0], border[1], border[2], border[3]));
    	this.setLayout(new BorderLayout(0, 0));
    }
}
