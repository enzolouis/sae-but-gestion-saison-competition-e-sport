package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modeles.ConsultationTournoiModele;
import vues.ConsultationTournoiVue;

public class ConsultationTournoiControleur implements ActionListener {

	private ConsultationTournoiVue vue;
	private ConsultationTournoiModele modele;
	
	public ConsultationTournoiControleur(ConsultationTournoiVue vue) {
		this.modele = new ConsultationTournoiModele();
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		if (bouton.getText().equals("Quitter")) {
			this.vue.closeCurrentWindow();
		}
	}
	
	public void setUpTableModel() {
		try {
			int classement = 0;
			int classementEgalite = 0;
			int lastPoints = -1;
			for (Object[] o : this.modele.classementTournoiCourant()) {
				if ((int)o[3] == lastPoints) {
					o[0] = classementEgalite;
				} else {
					o[0] = classement;
					classement++;
				}
				lastPoints = (int)o[3];
				
				o[0] = classement;
				
				classementEgalite++;
				this.vue.tableModel.addRow(o);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
