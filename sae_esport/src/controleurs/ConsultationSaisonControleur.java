package controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import DAOs.EquipeDAO;
import DAOs.TournoiDAO;
import classes.Equipe;
import classes.Joueur;
import modeles.TournoiModele;
import vues.ConsultationSaisonVue;

public class ConsultationSaisonControleur implements MouseListener {
	
	private ConsultationSaisonVue vue;
	
	public ConsultationSaisonControleur(ConsultationSaisonVue vue) {
		this.vue = vue;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() instanceof JTable) {
			JTable valeur = (JTable) e.getSource();
			int idEquipe = (int) valeur.getValueAt(valeur.getSelectedRow(), 1);
			try {
				vue.listejoueursmodele.clear();
				vue.listetournoismodele.clear();
				for (TournoiModele t : EquipeDAO.getInstance().getTournoisEquipe(EquipeDAO.getInstance().getById(idEquipe).get())) {
					vue.listetournoismodele.addElement(t);
				}
				for (Joueur j : EquipeDAO.getInstance().getById(idEquipe).get().getListeJoueurs()) {
					vue.listejoueursmodele.addElement(j);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
