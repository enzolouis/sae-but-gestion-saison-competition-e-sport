package DAOs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tournoi {
	private enum Etat_Tournoi {
		OUVERT,FERME
	}
	private enum Notoriete{
		LOCAL,
		REGIONAL,
		NATIONAL,
		INTERNATIONAL,
		INTERNATIONAL_CLASSE
	}
	
	private String nomTournoi;
	private Date dateDebut;
	private Date DateFin;
	private Notoriete notoriete;
	private Etat_Tournoi etat;
	private String motDePasse;
	private int idTournoi;
	private List<Match> matches;
	private List<Equipe> equipes;
	
	public Tournoi(String nomTournoi, Date dateDebut,Date DateFin,
					Notoriete notoriete, Etat_Tournoi etat,
					String motDePasse, int idTournoi, List<Equipe> equipes) {
		this.nomTournoi = nomTournoi;
		this.dateDebut =dateDebut;
		this.DateFin = DateFin;
		this.notoriete = notoriete;
		this.etat = etat;
		this.motDePasse = motDePasse;
		this.matches= new ArrayList<>();
		
		this.equipes = new ArrayList<>();
		for(Equipe e : equipes) {
			this.equipes.add(e);
		}
	}
}
