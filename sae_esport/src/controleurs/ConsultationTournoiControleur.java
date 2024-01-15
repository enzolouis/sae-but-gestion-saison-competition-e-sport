package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vues.ConsultationTournoiVue;

public class ConsultationTournoiControleur implements ActionListener {

	private ConsultationTournoiVue vue;
	//private ConsultationTournoiModele modele;
	
	public ConsultationTournoiControleur(ConsultationTournoiVue vue) {
		//this.modele = new ConsultationTournoiModele();
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		if (bouton.getText().equals("Quitter")) {
			this.vue.closeCurrentWindow();
		}
	}
	
}
