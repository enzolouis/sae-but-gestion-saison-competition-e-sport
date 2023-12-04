package style;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.Document;

import vues.PlaceholderTextField;

@SuppressWarnings("serial")
public class CustomJTextField extends JTextField {
    private Shape shape;
    
    public static void main(final String[] args) {
        final PlaceholderTextField tf = new PlaceholderTextField("");
        tf.setColumns(20);
        tf.setPlaceholder("All your base are belong to us!");
        final Font f = tf.getFont();
        tf.setFont(new Font(f.getName(), f.getStyle(), 30));
        JOptionPane.showMessageDialog(null, tf);
    }

    private String placeholder;

    public CustomJTextField() {
    }

    public CustomJTextField(
        final Document pDoc,
        final String pText,
        final int pColumns)
    {
        super(pDoc, pText, pColumns);
    }


    public CustomJTextField(final String pText) {
        super(pText);
    }

    public CustomJTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }
    
    public CustomJTextField(int size) {
        super(size);
        setOpaque(false);
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
