package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import classes.Match;
import modeles.SaisieResultatModele;
import modeles.TournoiModele;
import vues.ResultatMatchVue;

public class SaisieResultatControleur implements ActionListener {
	private SaisieResultatModele modele;
	private ResultatMatchVue vue;
	
	
	public SaisieResultatControleur(ResultatMatchVue vue) {
		this.vue = vue; 
		this.modele = new SaisieResultatModele(new Match(0,false),new TournoiModele());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			switch (bouton.getText()) {
			case("Annuler"):
				
				break;
			case("Valider"):
				
				break;
			case ("Quitter"):
				this.vue.dispose();
				break;
			}
		}
	}
	
}
