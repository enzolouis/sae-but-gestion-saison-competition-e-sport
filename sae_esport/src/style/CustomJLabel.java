package style;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import vues.Palette;

public class CustomJLabel extends JLabel {
	
	/**
	* Ryan GAUNAND <br>
	* Création d'un JLabel avec la définition d'un texte, la taille du texte et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Texte principale
	* @param Taille de la police du texte
	*/
	public CustomJLabel(String label, int fontScale) {
		super(label);
		
		setFont(Palette.customTextFont);
		
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(Palette.WHITE);
        setBorder(new EmptyBorder(0, 0, 0, 0));
	}
	
	/**
	* Ryan GAUNAND <br>
	* Création d'un JLabel avec la définition d'un texte, la taille du texte, de bordure vide et
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Texte principale
	* @param Taille de la police du texte
	* @param Bordure vide
	*/
	public CustomJLabel(String label, int fontScale, EmptyBorder border) {
		super(label);

		setFont(Palette.customTextFont);
		
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(Palette.WHITE);
        setBorder(border);
	}
}
