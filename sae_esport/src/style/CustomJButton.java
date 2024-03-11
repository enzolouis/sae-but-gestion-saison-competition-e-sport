package style;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import controleurs.ControleurAnimation;

public class CustomJButton extends JButton {
	Shape shape;
	int round;
	ControleurAnimation anim = new ControleurAnimation();
	Color initialBackground;
	
	/**
	 * retourne la valeur initiale de l'arrière plan (background)
	 * */
	public Color getInitialBackground() {
		return this.initialBackground;
	}
	
	
	
	/**
	 * Mise en place d'une nouvelle couleur d'arrière-plan
	 * @param c : couleur auquel changer l'arrière-plan
	 * @param valeur initiale de couleur d'arrière-plan 
	 * */
	public void setBackground(Color c, boolean changeInitialBackground) {
		if (changeInitialBackground) {
			this.initialBackground = c;
		}
		super.setBackground(c);
	}
	
	@Override
	public void setBackground(Color c) {
		this.initialBackground = c;
		super.setBackground(c);
	}
	
	/**
	* Ryan GAUNAND <br>
	* Création d'un JButton avec la fonctionnalités graphique d'arrondi, 
	* l'ajout d'un texte à l'intérieur et à l'application automatique 
	* de la charte graphique de l'application.
	*
	* @param  Texte affiché dans le bouton
	* @param  Arrondi du bouton
	*/
	public CustomJButton(String label, int round) {
		super(label);
		this.round = round;
		this.initialBackground = this.getBackground();
		this.addMouseListener(anim);
    	setBackground(new Color(29, 88, 129));
    	setForeground(Palette.WHITE);
		setOpaque(false);
		setFont(Palette.customFont);
		setFocusPainted(false);
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
	public CustomJButton(String label, int round, EmptyBorder border) {
		super(label);
		this.round = round;
		this.initialBackground = this.getBackground();
		this.addMouseListener(anim);
    	setBackground(new Color(29, 88, 129));
    	setForeground(Palette.WHITE);
    	setBorder(border);
		setOpaque(false);
		setFont(Palette.customFont);
		setFocusPainted(false);
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
