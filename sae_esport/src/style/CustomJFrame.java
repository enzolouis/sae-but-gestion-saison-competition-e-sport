package style;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class CustomJFrame extends JFrame {
	CustomJPanel contentPanel;
	
	/**
	* Ryan GAUNAND <br>
	* Création d'un JFrame avec l'application automatique 
	* de la charte graphique de l'application.
	*/
	public CustomJFrame() {
		super();
		
		this.contentPanel = new CustomJPanel(new EmptyBorder(30, 30, 30, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(this.contentPanel);
        setResizable(false);
	}
	
	/**
	* Ryan GAUNAND <br>
	* Création d'un JFrame avec l'ajout d'un titre de fenêtre et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param  Titre de la fenêtre
	*/
	public CustomJFrame(String frameTitle) {
		super(frameTitle);
		
		this.contentPanel = new CustomJPanel(new EmptyBorder(30, 30, 30, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(this.contentPanel);
        setResizable(false);
	}
	
	/**
	* Ryan GAUNAND <br>
	* Création d'un JFrame avec la définition d'une dimension et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Dimension de la fenêtre
	*/
	public CustomJFrame(Dimension dimension) {
		super();
		
		this.contentPanel = new CustomJPanel(new EmptyBorder(30, 30, 30, 30));
    	//setMinimumSize(dimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(100, 100, dimension.height, dimension.width);
        setContentPane(this.contentPanel);
        setResizable(false);
	}
	
	/**
	* Ryan GAUNAND <br>
	* Création d'un JFrame avec l'ajout d'un titre de fenêtre, la définition d'une dimension et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param  Dimension de la fenêtre
	* @param  Titre de la fenêtre
	*/
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
