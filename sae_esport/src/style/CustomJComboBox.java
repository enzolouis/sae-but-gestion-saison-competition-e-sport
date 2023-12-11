package style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;

public class CustomJComboBox<E> extends JComboBox<E>{
	Shape shape;
	
	public CustomJComboBox() {
		super();
		
		setBackground(new Color(44, 47, 51));
		setForeground(new Color(102, 173, 221));
		setOpaque(false);
		setBorder(null);
	}
	
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 0, 0);
        super.paintComponent(g);

        final Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   }
   
   protected void paintBorder(Graphics g) {
        g.setColor(getBackground());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 0, 0);
   }
   
   public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 0, 0);
        }
        
        return shape.contains(x, y);
   }
   
   public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {	
		paintComponent(g);
   }
}
