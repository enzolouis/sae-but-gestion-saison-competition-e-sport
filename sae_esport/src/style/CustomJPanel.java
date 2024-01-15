package style;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomJPanel extends JPanel {
	
	/**
	* Ryan GAUNAND <br>
	* Création d'un JPanel avec l'application automatique de la charte graphique de l'application.
	*
	*/
    public CustomJPanel() {
    	super();
    	
    	this.setBackground(Palette.BLACK);
    	this.setBorder(new EmptyBorder(0, 0, 0, 0));
    	this.setLayout(new BorderLayout(0, 0));
    }
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JPanel avec la définition d'un layout responsive et 
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Layout dynamique (FlowLayout)
	*/
	public CustomJPanel(FlowLayout layout) {
    	super();
    	
    	this.setBackground(Palette.BLACK);
    	this.setBorder(new EmptyBorder(0, 0, 0, 0));
    	this.setLayout(layout);
	}
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JPanel avec la définition de bordure vide et 
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Bordure vide
	*/
    public CustomJPanel(EmptyBorder border) {
    	super();
    	
    	this.setBackground(Palette.BLACK);
    	this.setLayout(new BorderLayout(0, 0));
    	this.setBorder(border);
    }
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JPanel avec la définition de bordure vide, d'un layout de grille et 
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Bordure vide
	* @param Layout en grille
	*/
    public CustomJPanel(EmptyBorder border, GridLayout layout) {
    	super();
    	
    	this.setBackground(Palette.BLACK);
    	this.setBorder(border);
    	this.setLayout(layout);
    }
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JPanel avec la définition de bordure vide, d'un layout de grille et 
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Bordure vide
	* @param Layout en grille
	*/
    public CustomJPanel(EmptyBorder border, FlowLayout layout) {
    	super();
    	
    	this.setBackground(Palette.BLACK);
    	this.setBorder(border);
    	this.setLayout(layout);
    }
    
	/**
	* Ryan GAUNAND <br>
	* Création d'un JPanel avec la définition de bordure vide, d'un layout de grille dynamique et 
	* l'application automatique de la charte graphique de l'application.
	*
	* @param Bordure vide
	* @param Layout en grille dynamique
	*/
    public CustomJPanel(EmptyBorder border, GridBagLayout layout) {
    	super();
    	
    	this.setBackground(Palette.BLACK);
    	this.setBorder(border);
    	this.setLayout(layout);
    }
}
