package style;

import java.awt.Dimension;

import javax.swing.JFrame;

public class CustomJFrame extends JFrame {
	CustomJPanel contentPanel;
	
	public CustomJFrame(int[] dimension) {
		super();
		
		this.contentPanel = new CustomJPanel(new int[]{30, 30, 30, 30});
    	setMinimumSize(new Dimension(dimension[0], dimension[1]));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, dimension[0], dimension[1]);
        setContentPane(this.contentPanel);
	}
	
	public CustomJPanel getContentPanel( ) {
		return this.contentPanel;
	}
}
