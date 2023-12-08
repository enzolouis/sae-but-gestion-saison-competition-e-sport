package style;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

public class CustomJTextField extends JTextField {
    private Shape shape;
    private String placeholder;
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JTextField l'application automatique de la charte graphique de l'application.
	*
	*/
    public CustomJTextField() {
    	setBasicConstructor();
    }

	/**
	* Ryan GAUNAND <br>
	* Création d'un JTextField avec la définition d'un texte de renseignement et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Texte de renseignement
	*/
    public CustomJTextField(final String pText) {
        super(pText);
        setBasicConstructor();
    }
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JTextField avec la définition d'un texte de renseignement et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Texte de renseignement
	*/
    public CustomJTextField(final String pText, int[] border) {
        super(pText);
        setBasicConstructor();
        setBorder(new EmptyBorder(border[0], border[1], border[2], border[3]));
    }

	/**
	* Ryan GAUNAND <br>
	* Création d'un JTextField avec la définition d'un texte de renseignement, 
	* la définition du nombre de colonne pour le calcul de la taille voulus et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Texte de renseignement
	* @param Colonnes
	*/
    public CustomJTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
        setBasicConstructor();
    }
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JTextField avec la définition de la taile et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Taille
	*/
    public CustomJTextField(int size) {
        super(size);
        setOpaque(false);
        setBasicConstructor();
    }
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JTextField avec la définition de la taile, un texte de renseignement et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Taille
	* @param Texte de renseignement
	*/
    public CustomJTextField(int size, String placeHolder) {
        super(size);
        setOpaque(false);
        setBasicConstructor();
        setPlaceholder(placeHolder);
    }
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JTextField avec la définition de la taile, de bordure vide et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Taille
	* @param Bordure vide
	*/
    public CustomJTextField(int size, EmptyBorder border) {
        super(size);
        setOpaque(false);
        setBasicConstructor();
        setBorder(border);
    }
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JTextField avec la définition de la taile, 
	* de bordure vide, d'un texte de renseignement et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Taille
	* @param Bordure vide
	* @param Texte de renseignement
	*/
    public CustomJTextField(int size, EmptyBorder border, String placeHolder) {
        super(size);
        setOpaque(false);
        setBasicConstructor();
        setBorder(border);
        setPlaceholder(placeHolder);
    }
    
    private void setBasicConstructor() {
        setForeground(Color.WHITE);
        setBackground(new Color(29, 88, 129));
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }
    
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         super.paintComponent(g);

         if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
             return;
         }

         final Graphics2D g1 = (Graphics2D) g;
         g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         g1.setColor(getDisabledTextColor());
         g1.drawString(placeholder, getInsets().left, g1.getFontMetrics().getMaxAscent() + getInsets().top);
    }
    
    protected void paintBorder(Graphics g) {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         }
         
         return shape.contains(x, y);
    }
}
