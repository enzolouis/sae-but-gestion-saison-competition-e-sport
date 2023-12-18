package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import classes.Equipe;
import classes.Match;
import classes.EtatTournoi;
import modeles.SaisieResultatModele;
import modeles.TournoiModele;
import vues.SaisieResultatVue;
import DAOs.MatchDAO;

public class SaisieResultatControleur implements ActionListener {
	private SaisieResultatModele modele;
	private SaisieResultatVue vue;
	
	
	public SaisieResultatControleur(SaisieResultatVue vue, TournoiModele tournoi) {
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
			case ("Ouvrir la Finale"):
				Match ma = new Match(0,true);
				Equipe e1 = null;
				Equipe e2 = null;
				int score= -1;
				int score2 = -2;
				Map<Equipe, Integer> equipe = modele.getTournoi().getEquipes();
				for (Map.Entry eq : equipe.entrySet()) {
					if((Integer) eq.getValue() > score) {
						e2 = e1;
						score2 = score;
						e1 = (Equipe) eq.getKey();
						score = (Integer)eq.getKey();
					}
					else {
						if ((Integer) eq.getValue() > score2) {
							e2 = (Equipe) eq.getKey();
							score2 = (Integer)eq.getKey();
						}
					}
				}
				ma.AddEquipe(e1);
				ma.AddEquipe(e2);
				try {
					MatchDAO.getInstance().add(ma);
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				//Ajout au tournoi
				break;
			case ("Fermer le tournoi"):
				modele.getTournoi().setEtatTournoi(EtatTournoi.FERME);				
				break;
			default:
				String[] ids = bouton.getActionCommand().split(",");
				int idmatch= Integer.valueOf(ids[0]);
				int idequipe = Integer.valueOf(ids[1]);
				Match m= new Match(idmatch,false);
				try {
					m = MatchDAO.getInstance().getById(idmatch).get();
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				if ( ! modele.IsFinaleDemarree() || m.IsItFinale()) {
					m.setVainqueur(idequipe);
				}
			}	
		}
	}
	
}
