package controleurs;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import DAOs.TournoiDAO;
import modeles.IdentificationModele;
import modeles.TournoiModele;
import modeles.IdentificationModele.Utilisateur;
import vues.AccueilAdministrateurVue;
import vues.AccueilArbitreVue;
import vues.IdentificationVue;
import vues.Palette;

public class IdentificationControleur implements ActionListener {
	
	private IdentificationVue vue;
	private IdentificationModele modele;
	private boolean isMotDePasseCache;
	private Timer animationErreur;
	
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
			this.vue.erreurOuverture.setText("Login et/ou mot de passe faux.");
			this.vue.panelErreur.setBackground(Palette.REDERRORBACKGROUND);
			this.vue.panelErreur.setBorder(new LineBorder(Palette.REDERRORBORDER, 1));
			// compteur de 3 secondes
			
			ActionListener action = new ActionListener() {
				int compteur = 1;
	    		@Override
			    public void actionPerformed(ActionEvent e) {
	    			compteur++;
	    			if (compteur == 3) {
	    				animationErreur.stop();
	    				vue.erreurOuverture.setText("");
	    				vue.panelErreur.setBackground(null);
	    				vue.panelErreur.setBorder(null);
	    			}
			    }
			};
			animationErreur = new Timer(2000, action);
			animationErreur.start();
			
			
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