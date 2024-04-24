package style;

import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import javax.swing.JComboBox;

@SuppressWarnings("rawtypes")
public class CustomJComboBox extends JComboBox {

    Shape shape;

    @SuppressWarnings("unchecked")
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
