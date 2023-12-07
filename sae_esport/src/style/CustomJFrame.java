package style;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class CustomJFrame extends JFrame {
	CustomJPanel contentPanel;
	
	public CustomJFrame() {
		super();
		
		this.contentPanel = new CustomJPanel(new EmptyBorder(30, 30, 30, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(this.contentPanel);
        setResizable(false);
	}
	
	public CustomJFrame(String frameTitle) {
		super(frameTitle);
		
		this.contentPanel = new CustomJPanel(new EmptyBorder(30, 30, 30, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(this.contentPanel);
        setResizable(false);
	}
	
	public CustomJFrame(Dimension dimension) {
		super();
		
		this.contentPanel = new CustomJPanel(new EmptyBorder(30, 30, 30, 30));
    	setMinimumSize(dimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, dimension.height, dimension.width);
        setContentPane(this.contentPanel);
        setResizable(false);
	}
	
	public CustomJFrame(Dimension dimension, String frameTitle) {
		super(frameTitle);
		
		this.contentPanel = new CustomJPanel(new EmptyBorder(30, 30, 30, 30));
    	setMinimumSize(dimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, dimension.height, dimension.width);
        setContentPane(this.contentPanel);
        setResizable(false);
	}
	
	public CustomJPanel getContentPanel( ) {
		return this.contentPanel;
	}
}
