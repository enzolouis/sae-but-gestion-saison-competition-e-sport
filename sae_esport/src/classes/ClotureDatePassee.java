package classes;

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
				Map<Equipe, Integer> equipe = c.getModele().getTournoi().getParticipants();
				for (Map.Entry eq : equipe.entrySet()) {
					Equipe equi =(Equipe) eq.getKey();
					Equipe equip = null;
					try {
						equip = EquipeDAO.getInstance().getById(equi.getIdEquipe()).get();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					equip.setPointsSaison((Integer)eq.getValue() + equip.getPointsSaison());
					try {
						EquipeDAO.getInstance().update(equip);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
	  }

}