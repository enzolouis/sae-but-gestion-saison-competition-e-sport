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
    
    public CustomJTextField() {
    	setBasicConstructor();
    }

    public CustomJTextField(final Document pDoc, final String pText, final int pColumns) {
        super(pDoc, pText, pColumns);
        setBasicConstructor();
    }


    public CustomJTextField(final String pText) {
        super(pText);
        setBasicConstructor();
    }
    
    public CustomJTextField(final String pText, int[] border) {
        super(pText);
        setBasicConstructor();
        setBorder(new EmptyBorder(border[0], border[1], border[2], border[3]));
    }

    public CustomJTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
        setBasicConstructor();
    }
    
    public CustomJTextField(int size) {
        super(size);
        setOpaque(false);
        setBasicConstructor();
    }
    
    public CustomJTextField(int size, String placeHolder) {
        super(size);
        setOpaque(false);
        setBasicConstructor();
        setPlaceholder(placeHolder);
    }
    
    public CustomJTextField(int size, int[] border) {
        super(size);
        setOpaque(false);
        setBasicConstructor();
        setBorder(new EmptyBorder(border[0], border[1], border[2], border[3]));
    }
    
    public CustomJTextField(int size, int[] border, String placeHolder) {
        super(size);
        setOpaque(false);
        setBasicConstructor();
        setBorder(new EmptyBorder(border[0], border[1], border[2], border[3]));
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
