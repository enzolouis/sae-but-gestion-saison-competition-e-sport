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
		  NOT_FINALE, IS_FINALE, IS_OVER
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
				this.vue.closeCurrentWindow();
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
						
						Map<Equipe, Integer> equipes = modele.getTournoi().getParticipants();
						for (Equipe eq : equipes.keySet()) {
							System.out.println(equipes.get(eq));
							if(equipes.get(eq) > score1) {
								equipe2 = equipe1;
								score2 = score1;
								equipe1 = eq;
								score1 = equipes.get(eq);
							} else {
								if (equipes.get(eq) > score2) {
									equipe2 = eq;
									score2 = equipes.get(eq);
								}
							}
						}
						
						Match matchFinale = new Match(4, modele.getTournoi().getIDTournoi(), true);
						matchFinale.AddEquipe(equipe1);
						matchFinale.AddEquipe(equipe2);
						MatchDAO.getInstance().add(matchFinale);
						
						this.modele.getTournoi().ajouterMatch(matchFinale);
						
						this.vue.OpenButtonFinal(matchFinale.getIDMatch(), equipe1, equipe2);
						this.stateTournoi = STATE.IS_FINALE;
						
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
			
				if (stateTournoi == STATE.IS_OVER) {
					
					Map<Equipe, Integer> equipes = modele.getTournoi().getParticipants();
					for (Equipe eq : modele.getTournoi().getParticipants().keySet()) {
						try {
							eq.setPointsSaison(eq.getPointsSaison() + equipes.get(eq));
							System.out.println(eq.getPointsSaison());
							EquipeDAO.getInstance().update(eq);
							TournoiDAO.getInstance().update(modele.getTournoi());
							EquipeDAO.getInstance().getById(eq.getIdEquipe()).get().getPointsSaison();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				
					}
				}
				
				this.vue.disableButtons();
				
				timer.cancel();
				break;
				
			default:
				
				System.out.println("a");
				String[] ids = bouton.getActionCommand().split(",");
				int idmatch = Integer.valueOf(ids[0]);
				int idequipe = Integer.valueOf(ids[1]);
				Match match = null;
				Equipe equipe = null;
				
				try {
					match = MatchDAO.getInstance().getById(idmatch).get();
					equipe = EquipeDAO.getInstance().getById(idequipe).get();
					if (match.getVainqueur() == 0) {
						this.modele.getTournoi().addPoints(equipe, 1);
						match.setVainqueur(idequipe);
					} else {
						this.modele.getTournoi().addPoints(
								EquipeDAO.getInstance().getById(match.getVainqueur()).get(), -1);
						this.modele.getTournoi().addPoints(equipe, 1);
						match.setVainqueur(idequipe);
					}
					match.setVainqueur(idequipe);
					equipe = EquipeDAO.getInstance().getById(idequipe).get();
					MatchDAO.getInstance().update(match);
					this.vue.RefreshMatch((CustomJButton) bouton);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				
				if (stateTournoi == STATE.IS_FINALE) {
					stateTournoi = STATE.IS_OVER;
				}
				
			}	
		}
	}
	
	public SaisieResultatModele getModele() {
		return this.modele;
	}
}

