package controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import style.CustomJFrame;

public class MenuMotionControleur implements MouseMotionListener, MouseListener {

	private CustomJFrame vue;

	private int offsetX;
	private int offsetY;

	public MenuMotionControleur(CustomJFrame vue) {
		this.vue = vue;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.vue.setLocation(e.getXOnScreen() - offsetX, e.getYOnScreen() - offsetY);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		offsetX = e.getXOnScreen() - this.vue.getX();
		offsetY = e.getYOnScreen() - this.vue.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
