package classes;

import java.util.Map;
import java.util.TimerTask;

import DAOs.EquipeDAO;
import controleurs.SaisieResultatControleur;

public class ClotureDatePassee extends TimerTask {
	
	SaisieResultatControleur c;
	
	/**
	 * Constructeur du controleur de résultats
	 * */
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
					Equipe equipeModif = null;
					try {
						equipeModif = EquipeDAO.getInstance().getById(equipe.getIdEquipe()).get();
					} catch (Exception e1) {
<<<<<<< Updated upstream
=======
						e1.printStackTrace();
					}
					equip.setPointsSaison((Integer)eq.getValue() + equip.getPointsSaison());
					try {
						EquipeDAO.getInstance().update(equip);
					} catch (Exception e1) {
>>>>>>> Stashed changes
						e1.printStackTrace();
					}
					equipeModif.setPointsSaison((Integer)e.getValue() + equipeModif.getPointsSaison());
					updateEquipe(equipeModif);
				}
			}
	  }
	  
	  private void updateEquipe(Equipe e) {
		  try {
				EquipeDAO.getInstance().update(e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	  }

}