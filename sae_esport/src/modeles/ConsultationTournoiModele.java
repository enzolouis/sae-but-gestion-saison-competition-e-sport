package modeles;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import DAOs.EquipeDAO;
import DAOs.ParticiperDAO;
import DAOs.TournoiDAO;
import classes.Equipe;
import classes.Match;
import classes.Participer;

public class ConsultationTournoiModele {
	public ConsultationTournoiModele() {
		
	}
	public List<Object[]> classementTournoiCourant() throws Exception {
		TournoiModele tournoiCourant = TournoiDAO.getInstance().getTournoiOuvert().get();
		
		List<Object[]> listStats = new ArrayList<>();
		
		for (Participer p : ParticiperDAO.getInstance().getAll().stream().filter(p -> p.getIdTournoi() == tournoiCourant.getIDTournoi()).sorted().collect(Collectors.toList())) {
			Equipe e = EquipeDAO.getInstance().getById(p.getIdEquipe()).get();
			
			int matchsJoues = 0;
			int victoire = 0;
			int defaite = 0;
			
			for (Match m : tournoiCourant.getMatchs()) {
				if (m.getVainqueur() == 0) {
					continue;
				}
				if (m.getEquipes().stream().map(eq -> eq.getIdEquipe()).collect(Collectors.toList()).contains(e.getIdEquipe())) {
					matchsJoues++;
					if (m.getVainqueur() == p.getIdEquipe()) {
						victoire++;
					} else {
						defaite++;
					}
				}
			}
			/* p.getResultat() */
			listStats.add(new Object[] {null, e.getNom(), matchsJoues, victoire*3+defaite, victoire, defaite});
		}
		listStats.sort(new Comparator<Object[]>() {
			@Override
			public int compare(Object[] o1, Object[] o2) {
				if ((int)o1[3] > (int)o2[3]) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		return listStats;
	}
}
