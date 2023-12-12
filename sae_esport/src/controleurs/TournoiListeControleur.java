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

import modeles.TournoiModele;

public class TournoiListeControleur implements ActionListener {
	private TournoiModele modele;
	
	public TournoiListeControleur() {
		this.modele = new TournoiModele();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		switch (bouton.getText()) {
		case "Ouvrir":
			System.out.println("Ouvrir tournoi");
			break;
		case "Fermer":
			System.out.println("Fermer tournoi");
			break;
		default:
			System.out.println("Autre tournoi");
			break;
		}
	}
	
}
