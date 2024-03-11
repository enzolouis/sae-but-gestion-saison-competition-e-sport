package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import modeles.IdentificationModele;
import modeles.IdentificationModele.Utilisateur;
import style.Palette;
import vues.AccueilAdministrateurVue;
import vues.AccueilArbitreVue;
import vues.IdentificationVue;

public class IdentificationControleur implements ActionListener {
	
	private IdentificationVue vue;
	private IdentificationModele modele;
	private boolean isMotDePasseCache;
	private Timer animationErreur;
	
	/**
	 * Effectue la construction de la vue
	 * @param vue de la page, permettant l'activation de ActionEvent
	 * */
	public IdentificationControleur(IdentificationVue vue) {
		this.isMotDePasseCache = true;
		this.vue = vue;
		this.modele = new IdentificationModele();
	}
	
	/**
	 * tente de se connecter à l'application avec les logins dans les fields
	 * */
	public void seConnecter() throws Exception {
		
		String login = this.vue.getUtilisateurContenu();
		String mdp = this.vue.getMotDePasseContenu();
		if (this.modele.checkLogins(login, mdp)) {
			this.vue.closeCurrentWindow();
			if (this.modele.getUtilisateur() == Utilisateur.ADMIN) {
				AccueilAdministrateurVue vueAdmin;
				try {
					vueAdmin = new AccueilAdministrateurVue();
					vueAdmin.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				AccueilArbitreVue vueArbitre;
				try {
					vueArbitre = new AccueilArbitreVue();
					vueArbitre.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		} else {
			this.vue.erreurOuverture.setText("Login et/ou mot de passe faux.");
			this.vue.panelErreur.setBackground(Palette.REDERRORBACKGROUND);
			this.vue.panelErreur.setBorder(new LineBorder(Palette.REDERRORBORDER, 1));
			// compteur de quelques secondes
			
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
	
	/**
	 * masque le mot de passe ou le démasque selon son état actuel
	 * @param le bouton de l'oeil, permettant d'afficher ou de cacher le mot de passe
	 * */
	public void inverserIconMotDePasseMasque(JButton btn) {
		this.isMotDePasseCache = !this.isMotDePasseCache;
		if (isMotDePasseCache) {
			btn.setIcon(Palette.OEIL_INVISIBLE_ICON);
			this.vue.getMotDePasse().setEchoChar('•');
		} else {
			btn.setIcon(Palette.OEIL_VISIBLE_ICON);
			this.vue.getMotDePasse().setEchoChar((char) 0);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			
			switch (bouton.getText()) {
			
			case "Quitter":
				this.vue.closeCurrentWindow();
				break;
			case "Se connecter":
				try {
					seConnecter();
				} catch (Exception e1) {
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
				e1.printStackTrace();
			}
		}
		
	}


}