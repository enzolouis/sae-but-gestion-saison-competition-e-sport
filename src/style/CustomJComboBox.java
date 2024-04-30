package style;

import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import javax.swing.JComboBox;

public class CustomJComboBox extends JComboBox {

    Shape shape;

    public CustomJComboBox() {
        super();
        setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        setRenderer(new CustomJComboBoxRenderer());
        setBackground(new Color(44, 47, 51));
        setForeground(Color.white);
        setUI(new CustomJComboBoxUI());
        setFont(Palette.FONT);
    }
}
