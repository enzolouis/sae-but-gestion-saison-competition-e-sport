package controleurs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import style.CustomJButton;

public class ControleurAnimation extends MouseAdapter {
	
	@Override
	public void mouseEntered(MouseEvent e) {
		CustomJButton btn = (CustomJButton) e.getComponent();
		
		btn.setBackground(new Color(
				Math.min(255, btn.getBackground().getRed() + 10), 
				Math.min(255, btn.getBackground().getGreen() + 10), 
				Math.min(255, btn.getBackground().getBlue() + 10)), false);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		CustomJButton btn = (CustomJButton) e.getComponent();
		
		btn.setBackground(btn.getInitialBackground(), false);
	}
}
