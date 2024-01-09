package style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import vues.Palette;

public class CustomJComboBoxRenderer extends JLabel implements ListCellRenderer {
    public CustomJComboBoxRenderer() {
    	super();
        setOpaque(true);
    }
     
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    	setText(" " + value.toString() + "  ");
        setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        
        if (isSelected) {
        	setBackground(new Color(102, 173, 221));
        } else {			
        	setBackground(new Color(29, 88, 129));
		}
        
        setForeground(Color.WHITE);
        setFont(Palette.customFont);
        return this;
    }
}
