package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import modeles.IdentificationModele;
import vues.IdentificationVue;

public class IdentificationControleur implements ActionListener {
	
	private IdentificationVue vue;
	private IdentificationModele modele;
	
	public IdentificationControleur(IdentificationVue vue) {
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
			String id = this.vue.textField.getText();
			
		}
	}
	
	

}
