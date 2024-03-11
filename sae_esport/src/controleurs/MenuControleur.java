package controleurs;
import java.awt.Color;
import java.awt.Frame;
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
import style.CustomJFrame;
import style.Palette;
import modeles.IdentificationModele.Utilisateur;
import vues.AccueilAdministrateurVue;
import vues.AccueilArbitreVue;
import vues.IdentificationVue;

public class MenuControleur implements ActionListener {
	
	private CustomJFrame vue;
	
	/**
	 * Construction de la vue,pour permettre les réactions avec Action Listener
	 * @param Jframe ou la vue sera placé
	 * */
	public MenuControleur(CustomJFrame vue) {
		this.vue = vue;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			if (bouton.getIcon() == Palette.CLOSE) {
				this.vue.closeCurrentWindow();
			}
			if (bouton.getIcon() == Palette.MINIMIZE) {
				this.vue.setState(Frame.ICONIFIED);
			}
		}
	}
}