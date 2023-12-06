package style;

import java.awt.Dimension;

import javax.swing.JFrame;

public class CustomJFrame extends JFrame {
	CustomJPanel contentPanel;
	
	public CustomJFrame() {
		super();
		
		this.contentPanel = new CustomJPanel(new int[]{30, 30, 30, 30});
    	setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setContentPane(this.contentPanel);
	}
	
	public CustomJPanel getContentPanel( ) {
		return this.contentPanel;
	}
}
