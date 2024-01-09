package style;

import java.awt.Color;

import javax.swing.JSeparator;

import vues.Palette;

public class CustomJSeparator extends JSeparator {
	
	/**
	* Ryan GAUNAND <br>
	* Création d'un JSeparator avec l'application automatique de la charte graphique de l'application.
	*
	*/
	public CustomJSeparator() {
		super();
		
        setBackground(Palette.BLUE);
        setForeground(Palette.BLUE);
	}
}
