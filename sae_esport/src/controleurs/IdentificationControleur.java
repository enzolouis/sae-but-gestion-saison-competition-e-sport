package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modeles.IdentificationModele;
import modeles.IdentificationModele.Utilisateur;
import vues.AccueilAdministrateurVue;
import vues.AccueilArbitreVue;
import vues.IdentificationVue;

public class IdentificationControleur implements ActionListener {
	
	private IdentificationVue vue;
	private IdentificationModele modele;
	private Connection dbConnection;
	
	public IdentificationControleur(IdentificationVue vue, Connection dbConnection) throws Exception {
		this.vue = vue;
		this.dbConnection = dbConnection;
		this.modele = new IdentificationModele(dbConnection);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		if (bouton.getText().equals("Quitter")) {
			this.vue.setVisible(false);
			this.vue.dispose();
		} else {
			String login = this.vue.getUtilisateurContenu();
			String mdp = this.vue.getMotDePasseContentu();
			if (this.modele.checkLogins(login, mdp)) {
				if (this.modele.getUtilisateur() == Utilisateur.ADMIN) {
					AccueilAdministrateurVue vueAdmin;
					try {
						vueAdmin = new AccueilAdministrateurVue(dbConnection);
						vueAdmin.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					AccueilArbitreVue vueArbitre;
					try {
						vueArbitre = new AccueilArbitreVue(dbConnection);
						vueArbitre.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				JFrame jFrame = new JFrame();
				JOptionPane.showMessageDialog(jFrame, "Login faux");
			}
			
		}
	}	

}
