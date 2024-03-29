package controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;

import DAOs.ArbitreDAO;
import DAOs.TournoiDAO;
import classes.Arbitre;
import classes.Nationalite;
import modeles.TournoiModele;
import style.CustomJButton;
import style.Palette;
import vues.AccueilAdministrateurVue;
import vues.ChoixArbitreVue;

public class ChoixArbitreControleur implements ActionListener {

	private ChoixArbitreVue vue;
	
	private Arbitre arbSelec;
	
	public ChoixArbitreControleur(ChoixArbitreVue vue) {
		this.vue = vue;
		this.arbSelec = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		switch (bouton.getText()) {
		case ("Quitter"):
			this.vue.closeCurrentWindow();
			break;
		case ("Ajouter cet arbitre"):
			if (this.vue.textFieldNom.getText().equals("") || this.vue.textFieldPrenom.getText().equals("") 
					|| this.vue.comboBoxNationalite.getSelectedIndex() == -1) {
				this.vue.erreur.setText("Champs de saisie manquant.");
	            this.vue.panelErreur.setBackground(Palette.REDERRORBACKGROUND);
	            this.vue.erreur.setForeground(Palette.REDERRORFOREGROUND);
	            this.vue.panelErreur.setBorder(new LineBorder(Palette.REDERRORBORDER, 1));
			} else {
				Arbitre a = new Arbitre(0, this.vue.textFieldNom.getText(), 
						this.vue.textFieldPrenom.getText(), (Nationalite) this.vue.comboBoxNationalite.getSelectedItem());
				try {
					ArbitreDAO.getInstance().add(a);
					CustomJButton boutonNew = new CustomJButton(a.toString(),15);
					boutonNew.addActionListener(this);
					boutonNew.setActionCommand(""+a.getIdArbitre());
					this.vue.boutonsArbitres.add(boutonNew);
					this.vue.panelArbitreList.add(boutonNew);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.vue.erreur.setText("Arbitre créé!");
				this.vue.erreur.setForeground(Palette.GREEN);
	            this.vue.panelErreur.setBackground(Palette.GREENLIGHTER);
	            this.vue.panelErreur.setBorder(new LineBorder(Palette.GREEN, 1));
	            this.vue.textFieldNom.setText(""); this.vue.textFieldPrenom.setText("");
	            this.vue.comboBoxNationalite.setSelectedIndex(-1);
			}
			break;
		case ("Supprimer cet arbitre"): 
			try {
				if (checkArbitreDeletable(this.arbSelec)) {
					for (TournoiModele t : ArbitreDAO.getInstance().getTournoisOfArbitre(this.arbSelec)) {
						t.retirerArbitre(this.arbSelec);
						TournoiDAO.getInstance().update(t);
					}
					
					ArbitreDAO.getInstance().delete(this.arbSelec);
					for (JButton b : this.vue.boutonsArbitres) {
						if (b.getActionCommand().equals(""+this.arbSelec.getIdArbitre())) {
							b.setEnabled(false);
						}
					}
					this.vue.erreur.setText("Cet arbitre a été supprimé.");
					this.vue.erreur.setForeground(Palette.GREEN);
		            this.vue.panelErreur.setBackground(Palette.GREENLIGHTER);
		            this.vue.panelErreur.setBorder(new LineBorder(Palette.GREEN, 1));
				} else {
					this.vue.erreur.setText("Cet arbitre n'est pas supprimable!");
		            this.vue.panelErreur.setBackground(Palette.REDERRORBACKGROUND);
		            this.vue.erreur.setForeground(Palette.REDERRORFOREGROUND);
		            this.vue.panelErreur.setBorder(new LineBorder(Palette.REDERRORBORDER, 1));
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
		default:
			int id2 = Integer.valueOf(bouton.getActionCommand());
			try {
				this.arbSelec = ArbitreDAO.getInstance().getById(id2).get();
				this.vue.lbNomArbitre.setText(arbSelec.toString());
				this.vue.listTournoisModele.clear();
				for (TournoiModele t : ArbitreDAO.getInstance().getTournoisOfArbitre(this.arbSelec)) {
					this.vue.listTournoisModele.addElement(t);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.vue.btnSupprimer.setEnabled(true);
			
		}
		
	}
	
	public boolean checkArbitreDeletable(Arbitre a) {
		try {
			for (TournoiModele t : ArbitreDAO.getInstance().getTournoisOfArbitre(a)) {
				if (t.getArbitres().size() == 1) {
					return false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
}
