package modeles;

import java.util.List;
import java.util.stream.Collectors;

import DAOs.EquipeDAO;
import classes.Equipe;

public class ConsultationSaisonModele {
	
	public List<Equipe> classement() throws Exception {
		return EquipeDAO.getInstance().getAll()
			.stream()
			.sorted((e1, e2) -> {if (e1.getPointsSaison() > e2.getPointsSaison()) {
									return 1;
								} else if (e1.getPointsSaison() == e2.getPointsSaison()) {
									return 0;
								} else {
									return -1;
								}
			}).collect(Collectors.toList());
	}

}
