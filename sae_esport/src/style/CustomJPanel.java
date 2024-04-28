package style;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomJPanel extends JPanel {
    Shape shape;
    private Color borderColor = null;
    private int radius = 0;

    public void setBorderColor(Color newColor) {
        this.borderColor = newColor;
    }

    public Color getBorderColor() {
        return this.borderColor;
    }

    public void setBorderRadius(int radius) {
        this.radius = radius;
    }

    public int getBorderRadius() {
        return radius;
    }

    /**
     * Ryan GAUNAND <br>
     * Création d'un JPanel avec l'application automatique de la charte graphique de
     * l'application.
     *
     */
    public CustomJPanel() {
        super();
        setOpaque(false);


        this.setBackground(Palette.BLACK);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setLayout(new BorderLayout(0, 0));
    }

    /**
     * Ryan GAUNAND <br>
     * Création d'un JPanel avec la définition d'un layout responsive et
     * l'application automatique de la charte graphique de l'application.
     *
     * @param Layout dynamique (FlowLayout)
     */
    public CustomJPanel(FlowLayout layout) {
        super();
        setOpaque(false);

        this.setBackground(Palette.BLACK);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setLayout(layout);
    }

    public CustomJPanel(GridLayout layout) {
        super();
        setOpaque(false);

        this.setBackground(Palette.BLACK);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setLayout(layout);
    }

    /**
     * Ryan GAUNAND <br>
     * Création d'un JPanel avec la définition de bordure vide et
     * l'application automatique de la charte graphique de l'application.
     *
     * @param Bordure vide
     */
    public CustomJPanel(EmptyBorder border) {
        super();
        setOpaque(false);

        this.setBackground(Palette.BLACK);
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(border);
    }

    /**
     * Ryan GAUNAND <br>
     * Création d'un JPanel avec la définition de bordure vide, d'un layout de
     * grille et
     * l'application automatique de la charte graphique de l'application.
     *
     * @param Bordure vide
     * @param Layout  en grille
     */
    public CustomJPanel(EmptyBorder border, GridLayout layout) {
        super();
        setOpaque(false);

        this.setBackground(Palette.BLACK);
        this.setBorder(border);
        this.setLayout(layout);
    }

    /**
     * Ryan GAUNAND <br>
     * Création d'un JPanel avec la définition de bordure vide, d'un layout de
     * border et
     * l'application automatique de la charte graphique de l'application.
     *
     * @param Bordure vide
     * @param Layout  en border
     */
    public CustomJPanel(EmptyBorder border, BorderLayout layout) {
        super();
        setOpaque(false);

        this.setBackground(Palette.BLACK);
        this.setBorder(border);
        this.setLayout(layout);
    }

    /**
     * Ryan GAUNAND <br>
     * Création d'un JPanel avec la définition de bordure vide, d'un layout de
     * grille et
     * l'application automatique de la charte graphique de l'application.
     *
     * @param Bordure vide
     * @param Layout  en grille
     */
    public CustomJPanel(EmptyBorder border, FlowLayout layout) {
        super();
        setOpaque(false);

        this.setBackground(Palette.BLACK);
        this.setBorder(border);
        this.setLayout(layout);
    }

    /**
     * Ryan GAUNAND <br>
     * Création d'un JPanel avec la définition de bordure vide, d'un layout de
     * grille dynamique et
     * l'application automatique de la charte graphique de l'application.
     *
     * @param Bordure vide
     * @param Layout  en grille dynamique
     */
    public CustomJPanel(EmptyBorder border, GridBagLayout layout) {
        super();
        setOpaque(false);

        this.setBackground(Palette.BLACK);
        this.setBorder(border);
        this.setLayout(layout);
    }

    
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getBorderRadius(), getBorderRadius());
        super.paintComponent(g);

        final Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getBorderColor() == null ? getBackground() : getBorderColor());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getBorderRadius(), getBorderRadius());
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, getBorderRadius(), getBorderRadius());
        }

        return shape.contains(x, y);
    }
}
