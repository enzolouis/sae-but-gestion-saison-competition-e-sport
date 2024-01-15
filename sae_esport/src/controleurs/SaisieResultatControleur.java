package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import classes.ClotureDatePassee;
import classes.Equipe;
import classes.Match;
import classes.EtatTournoi;
import modeles.SaisieResultatModele;
import modeles.TournoiModele;
import vues.SaisieResultatVue;
import DAOs.MatchDAO;
import DAOs.TournoiDAO;
import DAOs.EquipeDAO;

public class SaisieResultatControleur implements ActionListener {
	private SaisieResultatModele modele;
	private SaisieResultatVue vue;
	private Timer timer;
	
	public SaisieResultatControleur(SaisieResultatVue vue, TournoiModele tournoi) {
		this.vue = vue; 
		this.modele = new SaisieResultatModele(tournoi);
		Date dt=null;
		try {
			dt = tournoi.getDateFin();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
	    timer = new Timer();
		timer.schedule(new ClotureDatePassee(this) , dt );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton bouton = (JButton) e.getSource();
			switch (bouton.getText()) {
			case ("Quitter"):
				this.vue.closeCurrentWindow();
				break;
			case ("Ouvrir la Finale"):
				boolean b = true;
				List<Match> mats = modele.getTournoi().getMatchs();
				for (Match mat : mats) {
					if (Integer.valueOf(mat.getVainqueur()) == null ) {
						b = false;
					}
				}
				if (b) {
					Match ma = new Match(0,TournoiDAO.getInstance().getTournoiOuvert().get().getIDTournoi(),false);
					Equipe e1 = null;
					Equipe e2 = null;
					int score= -1;
					int score2 = -2;
					Map<Equipe, Integer> equipe = modele.getTournoi().getEquipes();
					for (Map.Entry eq : equipe.entrySet()) {
						if((Integer) eq.getValue() > score) {
							e2 = e1;
							score2 = score;
							e1 = (Equipe) eq.getKey();
							score = (Integer)eq.getKey();
						}
						else {
							if ((Integer) eq.getValue() > score2) {
								e2 = (Equipe) eq.getKey();
								score2 = (Integer)eq.getKey();
							}
						}
					}
					ma.AddEquipe(e1);
					ma.AddEquipe(e2);
					try {
						MatchDAO.getInstance().add(ma);
					} catch (Exception e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					modele.getTournoi().nouveauMatch(ma.getIDMatch(), true);
				}
				else {
					System.out.print("Tous les matchs ne sont pas finis");
				}
				break;
			case ("Fermer le tournoi"):
				modele.getTournoi().setEtatTournoi(EtatTournoi.FERME);
				if (modele.IsFinaleDemarree()) {
					Map<Equipe, Integer> equipe = modele.getTournoi().getEquipes();
					for (Map.Entry eq : equipe.entrySet()) {
						Equipe equi =(Equipe) eq.getKey();
						Equipe equip = null;
						try {
							equip = EquipeDAO.getInstance().getById(equi.getIdEquipe()).get();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						equip.setPointsSaison((Integer)eq.getValue() + equip.getPointsSaison());
						try {
							EquipeDAO.getInstance().update(equip);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				timer.cancel();
				break;
			default:
				String[] ids = bouton.getActionCommand().split(",");
				int idmatch= Integer.valueOf(ids[0]);
				int idequipe = Integer.valueOf(ids[1]);
				Match m= new Match(idmatch,TournoiDAO.getInstance().getTournoiOuvert().get().getIDTournoi(),false);
				try {
					m = MatchDAO.getInstance().getById(idmatch).get();
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				if ( ! modele.IsFinaleDemarree() || m.IsItFinale()) {
					m.setVainqueur(idequipe);
				}
			}	
		}
	}
	
	public SaisieResultatModele getModele() {
		return this.modele;
	}
}

