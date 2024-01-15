package modeles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import DAOs.EquipeDAO;
import classes.Equipe;

public class ConsultationSaisonModele {
	
	/**
	 * renvoie la liste du classement de la saison par ordre de points
	 * */
	public List<Equipe> classementRanked() throws Exception {
		
		return EquipeDAO.getInstance().getAll()
			.stream()
			.sorted((e1, e2) -> {if (e1.getPointsSaison() < e2.getPointsSaison()) {
									return 1;
								} else if (e1.getPointsSaison() == e2.getPointsSaison()) {
									return 0;
								} else {
									return -1;
								}
			}).collect(Collectors.toList());
		
	}
	
	/**
	 * renvoie le dictionnaire des équipes de la saison
	 * avec en clé l'équipe et en valeur leur rang exact
	 * */
	public Map<Equipe,Integer> classementWithRank() throws Exception {
		
		HashMap<Equipe,Integer> classement = new HashMap<Equipe,Integer>();
		
		int rang = 1;
		int nbPointsPerRank = this.classementRanked().get(0).getPointsSaison();
		int nbTeamPerRank = 1;
		
		classement.put(this.classementRanked().get(0), 1);
		
		for (Equipe e : this.classementRanked().subList(1, this.classementRanked().size())) {
			if (e.getPointsSaison() == nbPointsPerRank) {
				nbTeamPerRank++;
				classement.put(e, rang);
			} else {
				rang+=nbTeamPerRank;
				nbPointsPerRank=e.getPointsSaison();
				nbTeamPerRank=1;
				classement.put(e, rang);
			}
		}
		
		return classement;
		
	}

}
