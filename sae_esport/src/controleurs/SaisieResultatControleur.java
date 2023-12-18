package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import classes.Match;
import modeles.SaisieResultatModele;
import modeles.TournoiModele;
import vues.TournoiListeVue;
import DAOs.MatchDAO;

public class SaisieResultatControleur implements ActionListener {
	private SaisieResultatModele modele;
	private TournoiListeVue vue;
	
	
	public SaisieResultatControleur(TournoiListeVue vue, TournoiModele tournoi) {
		this.vue = vue; 
		this.modele = new SaisieResultatModele(tournoi);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			switch (bouton.getText()) {
			case ("Quitter"):
				this.vue.dispose();
				break;
			default:
				String[] ids = bouton.getActionCommand().split(",");
				int idmatch= Integer.valueOf(ids[0]);
				int idequipe = Integer.valueOf(ids[1]);
				Match m= new Match(idequipe,false);
				try {
					m = MatchDAO.getInstance().getById(idmatch).get();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if ( ! modele.IsFinaleDemarree() || m.IsItFinale()) {
					m.setVainqueur(idequipe);
				}
			}	
		}
	}
	
}
