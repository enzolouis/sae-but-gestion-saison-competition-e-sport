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
	private Timer timer;
	private STATE stateTournoi;
	public enum STATE {
		  NOT_FINALE, IS_FINALE
	}
	
	public SaisieResultatControleur(SaisieResultatVue vue, TournoiModele tournoi) {
		this.stateTournoi = STATE.NOT_FINALE;
		this.vue = vue; 
		this.modele = new SaisieResultatModele(tournoi);
		
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
		
		if (modele.IsFinaleDemarree()) {
			this.stateTournoi = STATE.IS_FINALE;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			switch (bouton.getText()) {
			case ("Quitter"):
				this.vue.dispose();
				break;
			case ("Ouvrir la finale"):
				try {
					boolean canOpenFinal = true;
					List<Match> matchsTournoi;
					matchsTournoi = TournoiDAO.getInstance().getById(modele.getTournoi().getIDTournoi()).get().getMatchs();
					for (Match match : matchsTournoi) {
						if ((Integer) match.getVainqueur() == 0) {
							canOpenFinal = false;
						}
					}
					
					if (canOpenFinal) {			
						Equipe equipe1 = null;
						Equipe equipe2 = null;
						int score1 = -1;
						int score2 = -1;
						
						Map<Equipe, Integer> equipe = modele.getTournoi().getEquipes();
						for (Map.Entry eq : equipe.entrySet()) {
							if((Integer) eq.getValue() > score1) {
								equipe1 = (Equipe) eq.getKey();
								score1 = (Integer)eq.getValue();
								equipe2 = equipe1;
								score2 = score1;
							}
							else {
								if ((Integer) eq.getValue() > score2) {
									equipe2 = (Equipe) eq.getKey();
									score2 = (Integer) eq.getValue();
								}
							}
						}
						
						Match matchFinale = new Match(4, modele.getTournoi().getIDTournoi(), true);
						matchFinale.AddEquipe(equipe1);
						matchFinale.AddEquipe(equipe2);
						
						this.modele.getTournoi().ajouterMatch(matchFinale);
						
						this.vue.OpenButtonFinal();
						this.stateTournoi = STATE.IS_FINALE;
						
						MatchDAO.getInstance().add(matchFinale);
						TournoiDAO.getInstance().update(this.modele.getTournoi());
					}
					else {
						System.out.println("Tous les matchs ne sont pas finis");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				break;
			case ("Fermer le tournoi"):
				modele.getTournoi().setEtatTournoi(EtatTournoi.FERME);
			
				if (modele.IsFinaleDemarree()) {
					Map<Equipe, Integer> equipe = modele.getTournoi().getEquipes();
					for (Map.Entry eq : equipe.entrySet()) {
						Equipe equi =(Equipe) eq.getKey();
						Equipe equip = null;
						try {
							equip = EquipeDAO.getInstance().getById(equi.getIdEquipe()).get();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						equip.setPointsSaison((Integer) eq.getValue() + equip.getPointsSaison());
						try {
							EquipeDAO.getInstance().update(equip);
							TournoiDAO.getInstance().update(modele.getTournoi());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				
				timer.cancel();
				break;
			default:
				if (this.stateTournoi == STATE.NOT_FINALE) {
					String[] ids = bouton.getActionCommand().split(",");
					int idmatch = Integer.valueOf(ids[0]);
					int idequipe = Integer.valueOf(ids[1]);
					Match match = null;
					
					try {
						match = MatchDAO.getInstance().getById(idmatch).get();
						match.setVainqueur(idequipe);
						MatchDAO.getInstance().update(match);
						this.vue.RefreshMatch((CustomJButton) bouton);
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				} else if (this.stateTournoi == STATE.IS_FINALE) {
					
				}
			}	
		}
	}
	
	public SaisieResultatModele getModele() {
		return this.modele;
	}
}

