package style;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CustomJLabel extends JLabel {
	public CustomJLabel(String label, int fontScale) {
		super(label);
		
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(new Color(102, 173, 221));
        setFont(new Font("Tahoma", Font.BOLD, fontScale));
        setBorder(new EmptyBorder(0, 0, 0, 0));
	}
	
	public CustomJLabel(String label, int fontScale, EmptyBorder border) {
		super(label);
		
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(new Color(102, 173, 221));
        setFont(new Font("Tahoma", Font.BOLD, fontScale));
        setBorder(border);
	}
}
