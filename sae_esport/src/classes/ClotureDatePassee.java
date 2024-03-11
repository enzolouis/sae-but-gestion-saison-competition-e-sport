package classes;

import java.util.Date;
import java.util.Map;
import java.util.TimerTask;

import DAOs.EquipeDAO;
import controleurs.SaisieResultatControleur;

public class ClotureDatePassee extends TimerTask {
	
	SaisieResultatControleur c;
	
	public ClotureDatePassee (SaisieResultatControleur c) {
		this.c = c;
	}
	
	

	  @Override
	  public void run() {
		  c.getModele().getTournoi().setEtatTournoi(EtatTournoi.FERME);
			if (c.getModele().isFinaleDemarree()) {
				Map<Equipe, Integer> equipe_list = c.getModele().getTournoi().getParticipants();
				for (Map.Entry e : equipe_list.entrySet()) {
					Equipe equipe =(Equipe) e.getKey();
					Equipe equipe2 = null;
					try {
						equipe2 = EquipeDAO.getInstance().getById(equipe.getIdEquipe()).get();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					equipe2.setPointsSaison((Integer)e.getValue() + equipe2.getPointsSaison());
					try {
						EquipeDAO.getInstance().update(equipe);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
	  }

}