package controleurs;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import DAOs.TournoiDAO;
import modeles.IdentificationModele;
import modeles.TournoiModele;
import modeles.IdentificationModele.Utilisateur;
import vues.AccueilAdministrateurVue;
import vues.AccueilArbitreVue;
import vues.IdentificationVue;

public class IdentificationControleur implements ActionListener {
	
	private IdentificationVue vue;
	private IdentificationModele modele;
	private boolean isMotDePasseCache;
	
	public IdentificationControleur(IdentificationVue vue) throws Exception {
		this.isMotDePasseCache = true;
		this.vue = vue;
		this.modele = new IdentificationModele();
	}
	
	public void seConnecter() throws Exception {
		
		String login = this.vue.getUtilisateurContenu();
		String mdp = this.vue.getMotDePasseContenu();
		if (this.modele.checkLogins(login, mdp)) {
			if (this.modele.getUtilisateur() == Utilisateur.ADMIN) {
				AccueilAdministrateurVue vueAdmin;
				try {
					vueAdmin = new AccueilAdministrateurVue();
					vueAdmin.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				AccueilArbitreVue vueArbitre;
				try {
					vueArbitre = new AccueilArbitreVue();
					vueArbitre.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		} else {
			this.vue.erreurOuverture.setText("Les informations sont fausses.");
			// compteur de 3 secondes
			int 
			ActionListener action = new ActionListener() {			    
	    		@Override
			    public void actionPerformed(ActionEvent e) {
	    			// do nothing...
			    }
			};
			Timer t = new Timer(1000, action);
			t.start();
			t
			
			
			
			this.vue.erreurOuverture.setText("");
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
			switch (bouton.getText()) {
			case "Quitter":
				this.vue.setVisible(false);
				this.vue.dispose();
				break;
			case "Se connecter":
				try {
					seConnecter();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			default:
				this.inverserIconMotDePasseMasque(bouton);
				break;
			}
		}
		else {
			try {
				seConnecter();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}


}