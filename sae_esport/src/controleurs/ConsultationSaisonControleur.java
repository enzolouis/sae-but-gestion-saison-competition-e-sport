package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAOs.EquipeDAO;
import DAOs.TournoiDAO;
import classes.Equipe;
import classes.Joueur;
import modeles.TournoiModele;
import vues.ConsultationSaisonVue;

public class ConsultationSaisonControleur implements ListSelectionListener {
	
	private ConsultationSaisonVue vue;
	
	public ConsultationSaisonControleur(ConsultationSaisonVue vue) {
		this.vue = vue;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		String strSource = e.getSource().toString();
		int start = strSource.indexOf("{")+1, stop  = strSource.length()-1;
		int iSelectedIndex = Integer.parseInt(strSource.substring(start, stop));
		int idEquipe = (int) vue.table.getValueAt(iSelectedIndex, 1);
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
