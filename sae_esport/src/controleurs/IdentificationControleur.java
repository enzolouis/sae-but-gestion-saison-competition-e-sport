package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	private boolean isMotDePasseCache;
	private Character mdpValeur;
	private Character lastMdpValeur;
	
	public IdentificationControleur(IdentificationVue vue, Connection dbConnection) throws Exception {
		this.isMotDePasseCache = true;
		this.mdpValeur = ' ';
		this.lastMdpValeur = ' ';
		this.vue = vue;
		this.dbConnection = dbConnection;
		this.modele = new IdentificationModele(dbConnection);
	}
	
	public void seConnecter() {
		String login = this.vue.getUtilisateurContenu();
		String mdp = this.vue.getMotDePasseContenu();
		System.out.println(login);
		System.out.println(mdp);
		if (this.modele.checkLogins(login, mdp)) {
			if (this.modele.getUtilisateur() == Utilisateur.ADMIN) {
				System.out.println("ADMIN");
				AccueilAdministrateurVue vueAdmin;
				try {
					vueAdmin = new AccueilAdministrateurVue(dbConnection);
					vueAdmin.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				System.out.println("ARBITRE");
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
	
	public void inverserIconMotDePasseMasque(JButton btn) {
		this.isMotDePasseCache = !this.isMotDePasseCache;
		
		if (isMotDePasseCache) {
			btn.setIcon(IdentificationVue.OEIL_INVISIBLE_ICON);
			
			this.vue.getMotDePasse().setEchoChar('•');
		} else {
			btn.setIcon(IdentificationVue.OEIL_VISIBLE_ICON);
			this.vue.getMotDePasse().setEchoChar((char) 0);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			if (bouton.getText().equals("Quitter")) {
				this.vue.setVisible(false);
				this.vue.dispose();
			} else if (bouton.getText().equals("Se connecter")) {
				seConnecter();
			} else {
				this.inverserIconMotDePasseMasque(bouton);
			}
			
		} else {
			seConnecter();
		}
		
	}


}