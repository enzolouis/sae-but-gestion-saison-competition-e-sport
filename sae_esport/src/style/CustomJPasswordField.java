package style;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class CustomJPasswordField extends JPasswordField {
    private Shape shape;
    private String placeholder;
    private Color borderColor;
    private int radius;
    
    /**
     * Changement de la couleur des bordures d'une fenêtre d'une couleur à une autre
     * @param valeur couleur avec lequel remplacer
     * */
    public void setBorderColor(Color newColor) {
    	this.borderColor = newColor;
    }
    
    /**
     * Retourne la valeur de la couleur de des bordures de la fenêtre
     * */
    public Color getBorderColor() {
    	return this.borderColor;
    }
    
    /**
     * Changement de l'épaisseur des bordures de la fenêtre 
     * @param valeur d'épaisseur de la fonction
     * */
    public void setBorderRadius(int radius) {
    	this.radius = radius;
    }
    
    /**
     * Retourne la valeur d'épaisseur de la fenêtre
     * */
    public int getBorderRadius() {
    	return radius;
    }
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JTextField l'application automatique de la charte graphique de l'application.
	*
	*/
    public CustomJPasswordField() {
    	super();
    	setBasicConstructor();
    }

	/**
	* Ryan GAUNAND <br>
	* Création d'un JTextField avec la définition d'un texte de renseignement et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Texte de renseignement
	*/
    public CustomJPasswordField(final String pText) {
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
    public CustomJPasswordField(final String pText, int[] border) {
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
    public CustomJPasswordField(final String pText, final int pColumns) {
        super(pText, pColumns);
        setBasicConstructor();
    }
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JTextField avec la définition d'un texte de renseignement, 
	* la définition du nombre de colonne pour le calcul de la taille voulus, de bordure vide et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Texte de renseignement
	* @param Colonnes
	* @param Bordure vide
	*/
    public CustomJPasswordField(final String pText, final int pColumns, EmptyBorder border) {
        super(pText, pColumns);
        setBasicConstructor();
        setBorder(border);
    }
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JTextField avec la définition de la taile et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Taille
	*/
    public CustomJPasswordField(int size) {
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
    public CustomJPasswordField(int size, String placeHolder) {
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
    public CustomJPasswordField(int size, EmptyBorder border) {
        super(size);
        setOpaque(false);
        setBasicConstructor();
        setBorder(border);
    }
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JTextField avec la définition de la taille, 
	* de bordure vide, d'un texte de renseignement et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Taille
	* @param Bordure vide
	* @param Texte de renseignement
	*/
    public CustomJPasswordField(int size, EmptyBorder border, String placeHolder) {
        super(size);
        setOpaque(false);
        setBasicConstructor();
        setBorder(border);
        setPlaceholder(placeHolder);
    }
    
    private void setBasicConstructor() {
    	this.borderColor = getForeground();
    	this.radius = 15;
        setForeground(Color.WHITE);
        setBackground(Palette.BLUE);
        setFont(Palette.customTextFont);
    }

    /**
     * Retourne le texte de renseignement
     * */
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * Change la valeur du texte de renseignement
     * @param le texte de remplacement
     * */
    public void setPlaceholder(final String s) {
        placeholder = s;
    }
    
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, getBorderRadius(), getBorderRadius());
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
         g.setColor(this.getBorderColor());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, getBorderRadius(), getBorderRadius());
    }
    
    /**
     * Vérifie si la taille de la fenêtre corresponds aux valeurs de largeur et hauteur donné
     * 
     * @param largeur
     * @param hauteur
     * */
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, getBorderRadius(), getBorderRadius());
         }
         
         return shape.contains(x, y);
    }
}
