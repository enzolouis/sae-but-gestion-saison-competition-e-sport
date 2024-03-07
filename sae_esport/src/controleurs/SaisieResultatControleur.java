package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Timer;

import javax.security.auth.Destroyable;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import classes.ClotureDatePassee;
import classes.Equipe;
import classes.Match;
import classes.EtatTournoi;
import modeles.SaisieResultatModele;
import modeles.TournoiModele;
import style.CustomJButton;
import vues.SaisieResultatVue;
import DAOs.MatchDAO;
import DAOs.TournoiDAO;
import DAOs.EquipeDAO;

public class SaisieResultatControleur implements ActionListener {
	
	private SaisieResultatModele modele;
	private SaisieResultatVue vue;
	public Timer timer;
	private FINALESTATE stateTournoi;
	public enum FINALESTATE {
		  NOT_FINALE, IS_FINALE, FINALE_OVER
	}
	
	/*
	 * Mise en place de la vue, pour permettre les réactions avec Action Listener 
	 * @param Valeur retourné
	 * @param les paramètres de tournoi à manipuler dans cette vue
	 * */
	public SaisieResultatControleur(SaisieResultatVue vue, TournoiModele tournoi) {
		
		this.stateTournoi = FINALESTATE.NOT_FINALE;
		this.vue = vue; 
		this.modele = new SaisieResultatModele();
		
		Date dt = null;
		try {
			dt = tournoi.getDateFin();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		
	    timer = new Timer();
		timer.schedule(new ClotureDatePassee(this), dt);
		
		
		if (modele.isFinaleDemarree()) {
			this.stateTournoi = FINALESTATE.IS_FINALE;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {
			
			JButton bouton = (JButton) e.getSource();
			switch (bouton.getText()) {
			
			case ("Quitter"):
				this.vue.closeCurrentWindow();
				break;
				
			case ("Ouvrir la finale"):
				
				try {
					
					if (modele.canOpenFinale()) {
						
						Match finale = modele.createFinale();
						MatchDAO.getInstance().add(finale);
						this.vue.OpenButtonFinal(finale.getIDMatch(), 
								finale.getEquipes().get(0), finale.getEquipes().get(1));
						this.stateTournoi = FINALESTATE.IS_FINALE;
						
						TournoiDAO.getInstance().update(this.modele.getTournoi());
						
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				break;
				
			case ("Fermer le tournoi"):
			
				if (stateTournoi == FINALESTATE.FINALE_OVER) {
					modele.finirTournoi();
				} else {
					modele.annulerTournoi();
				}
				this.vue.disableButtons();
				timer.cancel();
				break;
				
			default:
				
				modele.choixVainqueurMatch(bouton);
				
				this.vue.RefreshMatch((CustomJButton) bouton);
				if (stateTournoi == FINALESTATE.IS_FINALE) {
					stateTournoi = FINALESTATE.FINALE_OVER;
				}
				
			}	
		}
	}
	
	
	/*
	 * Retourne le résultat 
	 * */
	public SaisieResultatModele getModele() {
		return this.modele;
	}
}

