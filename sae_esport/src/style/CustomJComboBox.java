package style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import vues.Palette;

public class CustomJComboBox extends JComboBox {
    private DefaultComboBoxModel model;
    Shape shape;
    
    public CustomJComboBox() {
    	super();
    	setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        setRenderer(new CustomJComboBoxRenderer());
        setBackground(new Color(44, 47, 51));
        setForeground(Color.white);
        setUI(new CustomJComboBoxUI());
        setFont(Palette.customFont);
    }
}
