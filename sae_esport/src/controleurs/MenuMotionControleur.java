package controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import style.CustomJFrame;
import style.CustomJPanel;

public class MenuMotionControleur implements MouseMotionListener, MouseListener {
	
	private CustomJFrame vue;
	
	private double positionXinitiale;
	private double positionYinitiale;
	
	private int offsetX;
	private int offsetY;
	
	public MenuMotionControleur(CustomJFrame vue) {
		this.vue = vue;
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		CustomJPanel target = (CustomJPanel) e.getSource();
		this.vue.setLocation(e.getXOnScreen() - offsetX, e.getYOnScreen() - offsetY);
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		offsetX = e.getXOnScreen() - this.vue.getX();
        offsetY = e.getYOnScreen() - this.vue.getY();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
