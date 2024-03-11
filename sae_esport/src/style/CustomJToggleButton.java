package style;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class CustomJToggleButton extends JToggleButton {
	Shape shape;
	int round;
	
	/**
	* Ryan GAUNAND <br>
	* Création d'un JButton avec la fonctionnalités graphique d'arrondi, 
	* l'ajout d'un texte à l'intérieur et à l'application automatique 
	* de la charte graphique de l'application.
	*
	* @param  Texte affiché dans le bouton
	* @param  Arrondi du bouton
	*/
	public CustomJToggleButton(String label, int round) {
		super(label);
		this.round = round;
    	setBackground(new Color(29, 88, 129));
    	setForeground(new Color(255, 255, 255));
		setOpaque(false);
		setFont(Palette.customFont);
	}
	
	/**
	* Ryan GAUNAND <br>
	* Création d'un JButton avec la fonctionnalités graphique d'arrondi, 
	* l'ajout d'un texte à l'intérieur, l'ajout de bordure vide et à 
	* l'application automatique de la charte graphique de l'application.
	*
	* @param  Texte affiché dans le bouton
	* @param  Arrondi du bouton
	* @param  Bordure vide
	*/
	public CustomJToggleButton(String label, int round, EmptyBorder border) {
		super(label);
		this.round = round;
    	setBackground(new Color(29, 88, 129));
    	setForeground(new Color(255, 255, 255));
    	setBorder(border);
		setOpaque(false);
		setFont(Palette.customFont);
	}

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, round, round);
        super.paintComponent(g);

        final Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   }
   
   protected void paintBorder(Graphics g) {
        g.setColor(getBackground());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, round, round);
   }
   
   public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, round, round);
        }
        
        return shape.contains(x, y);
   }
}
