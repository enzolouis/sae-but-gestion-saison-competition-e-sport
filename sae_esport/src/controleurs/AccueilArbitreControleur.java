package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import DAOs.TournoiDAO;
import modeles.TournoiModele;
import style.CustomJFrame;
import vues.AccueilArbitreVue;
import vues.ConsultationTournoiVue;
import vues.CreationTournoiVue;
import vues.IdentificationVue;

public class AccueilArbitreControleur implements ActionListener {
	
	private AccueilArbitreVue vue;
	
	public AccueilArbitreControleur(AccueilArbitreVue vue) {
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {
			
			JButton bouton = (JButton) e.getSource();
			CustomJFrame frame;
			
			switch (bouton.getText()) {
			
			case ("Quitter"):
				this.vue.setVisible(false);
				this.vue.dispose();
				break;
			case "Se déconnecter":
				this.vue.setVisible(false);
				this.vue.dispose();
				frame = new IdentificationVue();
				frame.setVisible(true);
				break;
			case ("Accès au tournoi"):
				break;
			
			case ("Statistiques du tournoi"):
				TournoiModele t = null;
				try {
					t = TournoiDAO.getInstance().getTournoiOuvert().get();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				frame = new ConsultationTournoiVue(t);
				frame.setVisible(true);
				break;
				
			default:
				break;
				
			}
		}
	}

}
