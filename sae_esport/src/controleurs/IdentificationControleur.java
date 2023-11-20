package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import modeles.IdentificationModele;
import vues.IdentificationVue;

public class IdentificationControleur implements ActionListener {
	
	private IdentificationVue vue;
	private IdentificationModele modele;
	
	public IdentificationControleur(IdentificationVue vue) throws Exception {
		this.vue = vue;
		this.modele = new IdentificationModele();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		if (bouton.getName() == "boutonQuitter") {
			this.vue.setVisible(false);
			this.vue.dispose();
		} else {
			String login = this.vue.getTextFieldUtilisateurContenu();
			String mdp = this.vue.getTextFieldMotDePasseContenu();
			if (this.modele.checkLogins(login, mdp)) {
				//succes de l'identification gg wp
				//recuperer le type du login pour ouvrir une page en consequence
				//possibilite de faire page de test
			} else {
				//envoi un msg pour dire ntm
			}
		}
	}
	
	

}
