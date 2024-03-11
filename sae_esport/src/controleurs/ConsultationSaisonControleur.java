package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAOs.EquipeDAO;
import classes.Joueur;
import modeles.TournoiModele;
import vues.ConsultationSaisonVue;

public class ConsultationSaisonControleur implements ListSelectionListener, ActionListener {
	
	private ConsultationSaisonVue vue;
	
	/**
	 * Effectue la construction de la vue
	 * @param vue de la page, permettant l'activation de ActionEvent
	 * */
	public ConsultationSaisonControleur(ConsultationSaisonVue vue) {
		this.vue = vue;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		int idEquipe = (int) vue.table.getModel().getValueAt(vue.table.getSelectedRow(), 1);
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
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		switch (source.getText()) {
		case "Quitter":
			this.vue.closeCurrentWindow();
		}
	}

}
