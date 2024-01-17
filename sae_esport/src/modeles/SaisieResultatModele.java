package modeles;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import DAOs.EquipeDAO;
import DAOs.MatchDAO;
import DAOs.TournoiDAO;
import classes.DBConnection;
import classes.Equipe;
import classes.EtatTournoi;
import classes.Match;
import controleurs.SaisieResultatControleur.FINALESTATE;
import modeles.TournoiModele;
import style.CustomJButton;

public class SaisieResultatModele {
	
	private TournoiModele tournoi;
	
	public SaisieResultatModele(TournoiModele tournoi) {
		this.tournoi = tournoi;
	}
	
	public TournoiModele getTournoi() {
		return this.tournoi;
	}
	
	public boolean isFinaleDemarree() {
		for (Match i : tournoi.getMatchs()) {
			if (i.IsItFinale()) {
				return true;
			}
		}
		return false;
	}	
	
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
	
	public List<Equipe> getFinalistes() {
		
		Equipe equipe1 = null;
		Equipe equipe2 = null;
		int score1 = -1;
		int score2 = -1;
		
		Map<Equipe, Integer> equipes = tournoi.getParticipants();
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
		
		List<Equipe> finalistes = new ArrayList<Equipe>();
		finalistes.add(equipe1); finalistes.add(equipe2);
		return finalistes;
		
	}
	
	public Match createFinale() {

		List<Equipe> finalistes = getFinalistes();
		Equipe equipe1 = finalistes.get(0); Equipe equipe2 = finalistes.get(1);
		
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
	
	public void updatePointsSaison() {
		Map<Equipe, Integer> equipes = tournoi.getParticipants();
		for (Equipe eq : tournoi.getParticipants().keySet()) {
			try {
				eq.setPointsSaison(eq.getPointsSaison() + equipes.get(eq));
				EquipeDAO.getInstance().update(eq);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void annulerTournoi() {
		
		updatePointsSaison();
		tournoi.setEtatTournoi(EtatTournoi.ANNULE);
		try {
			TournoiDAO.getInstance().update(tournoi);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void finirTournoi() {
		
		tournoi.setEtatTournoi(EtatTournoi.TERMINE);
		try {
			TournoiDAO.getInstance().update(tournoi);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void choixVainqueurMatch(JButton bouton) {
		
		String[] ids = bouton.getActionCommand().split(",");
		int idmatch = Integer.valueOf(ids[0]);
		int idequipe = Integer.valueOf(ids[1]);
		Match match = null;
		Equipe equipe = null;
		
		try {
			match = MatchDAO.getInstance().getById(idmatch).get();
			equipe = EquipeDAO.getInstance().getById(idequipe).get();
			if (match.getVainqueur() == 0) {
				tournoi.addPoints(equipe, 1);
				match.setVainqueur(idequipe);
			} else {
				tournoi.addPoints(
						EquipeDAO.getInstance().getById(match.getVainqueur()).get(), -1);
				tournoi.addPoints(equipe, 1);
				match.setVainqueur(idequipe);
			}
			match.setVainqueur(idequipe);
			equipe = EquipeDAO.getInstance().getById(idequipe).get();
			MatchDAO.getInstance().update(match);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		
	}
	
}
