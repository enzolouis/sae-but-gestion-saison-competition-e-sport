package modeles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import DAOs.EquipeDAO;
import DAOs.MatchDAO;
import DAOs.ParticiperDAO;
import DAOs.TournoiDAO;

import classes.Equipe;
import classes.EtatTournoi;
import classes.Match;
import classes.Participer;

public class SaisieResultatModele {
	
	private TournoiModele tournoi;
	
	/**
	 * Constructeur du modèle de Tournoi, basé sur l'instance DAO du Tournoi
	 * */
	public SaisieResultatModele() {
		this.tournoi = TournoiDAO.getInstance().getTournoiOuvert().get();
	}
	
	public TournoiModele getTournoi() {
		return this.tournoi;
	}
	
	/**
	 * renvoie si la finale du tournoi a demarré ou non
	 */
	public boolean isFinaleDemarree() {
		for (Match i : tournoi.getMatchs()) {
			if (i.IsItFinale()) {
				return true;
			}
		}
		return false;
	}	
	
	/**
	 * renvoie si la finale du tournoi peut être ouverte
	 * @return
	 */
	public boolean canOpenFinale() {
		
		boolean canOpenFinal = true;
		List<Match> matchsTournoi = new ArrayList<>();
		try {
			matchsTournoi = TournoiDAO.getInstance().getById(tournoi.getIDTournoi()).get().getMatchs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Match match : matchsTournoi) {
			if ((Integer) match.getVainqueur() == 0) {
				canOpenFinal = false;
			}
		}
		return canOpenFinal;
		
	}
	
	/**
	 * renvoie la liste des finalistes selon les points actuels
	 * @return
	 */
	public List<Equipe> getFinalistes() {
		
		Equipe equipe1 = tournoi.getParticipants().keySet().iterator().next();
		int score1 = tournoi.getParticipants().get(equipe1);
		
		Map<Equipe, Integer> equipes = new HashMap<>();
		equipes.putAll(this.tournoi.getParticipants());
		for (Equipe eq : equipes.keySet()) {
			if(equipes.get(eq) > score1) {
				equipe1 = eq;
				score1 = equipes.get(eq);
			}
		}
		
		equipes.remove(equipe1);
		Equipe equipe2 = tournoi.getParticipants().keySet().iterator().next();
		int score2 = -1;
		
		for (Equipe eq : equipes.keySet()) {
			if(equipes.get(eq) > score2) {
				equipe2 = eq;
				score2 = equipes.get(eq);
			}
		}
		
		List<Equipe> finalistes = new ArrayList<Equipe>();
		finalistes.add(equipe1); finalistes.add(equipe2);
		return finalistes;
		
	}
	
	public Match createFinale() {
		
		Equipe equipe1 = getFinalistes().get(0); 
		Equipe equipe2 = getFinalistes().get(1);
		
		Match matchFinale = new Match(4, tournoi.getIDTournoi(), true);
		matchFinale.addEquipe(equipe1); matchFinale.addEquipe(equipe2);
		try {
			MatchDAO.getInstance().add(matchFinale);
			tournoi.ajouterMatch(matchFinale);
			TournoiDAO.getInstance().update(tournoi);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return matchFinale;
		
	}
	
	/**
	 * Mise à jour des points des équipes en fonction de leurs participation
	 * */
	public void updatePointsSaison() {
		try {
			for (Equipe eq : EquipeDAO.getInstance().getAll()) {
				
				int resultat = 0;
				for (Participer p : ParticiperDAO.getInstance().getByIdEquipe(eq.getIdEquipe())) {
					float facteurNot = TournoiDAO.getInstance().getById(p.getIdTournoi())
							.get().getNotoriete().getBase();
					resultat+=p.getResultat()*facteurNot;
				}
				eq.setPointsSaison(resultat);
				EquipeDAO.getInstance().update(eq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * */
	public void annulerTournoi() {
		
		tournoi.setEtatTournoi(EtatTournoi.ANNULE);
		try {
			TournoiDAO.getInstance().update(tournoi);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	public void finirTournoi() {

		updatePointsSaison();
		tournoi.setEtatTournoi(EtatTournoi.TERMINE);
		try {
			TournoiDAO.getInstance().update(tournoi);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void choixVainqueurMatch(JButton bouton) {
		
		String[] ids = bouton.getActionCommand().split(",");
		int idmatch = Integer.valueOf(ids[0]);
		int idequipe = Integer.valueOf(ids[1]);
		Match match = null;
		int nbPoints = 1;
		if (isFinaleDemarree()) {
			nbPoints = 10;
		}
		
		try {
			
			match = MatchDAO.getInstance().getById(idmatch).get();
			if (match.getVainqueur() == 0) {
				tournoi.addPoints(EquipeDAO.getInstance().getById(idequipe).get(), nbPoints);
			} else {
				tournoi.addPoints(
						EquipeDAO.getInstance().getById(match.getVainqueur()).get(), -nbPoints);
				tournoi.addPoints(EquipeDAO.getInstance().getById(idequipe).get(), nbPoints);
			}
			match. setVainqueur(idequipe);
			MatchDAO.getInstance().update(match);
			Equipe e = EquipeDAO.getInstance().getById(idequipe).get();
			ParticiperDAO.getInstance().update(new Participer(tournoi.getParticipants().get(e),
					tournoi.getIDTournoi(),e.getIdEquipe()));
			TournoiDAO.getInstance().update(tournoi);

		} catch (Exception exception) {
			exception.printStackTrace();
		}	
	}
	
}
