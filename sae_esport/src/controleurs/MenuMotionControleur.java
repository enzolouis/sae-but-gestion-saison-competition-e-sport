package controleurs;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.annotation.Target;

import javax.swing.JButton;

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
		System.out.println(target.getLocationOnScreen().getX() + " " + (target.getLocationOnScreen().getX() + target.getWidth()));
		//System.out.println(this.view.getContentPane().getLocation());
		//System.out.println(this.vue.getMousePosition());
		//System.out.println(MouseInfo.getPointerInfo().getLocation());
		
		//target.setLocation((int)MouseInfo.getPointerInfo().getLocation().getX()-target.getWidth()/2, (int)MouseInfo.getPointerInfo().getLocation().getY()-target.getHeight()/2);
		//target.setLocation((int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY());
		//System.out.println((int)this.vue.getMousePosition().getX() + "::"+ (int)this.vue.getMousePosition().getY());
		
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
