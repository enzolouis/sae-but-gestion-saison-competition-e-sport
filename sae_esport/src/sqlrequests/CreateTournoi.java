package sqlrequests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.DBConnection;

public class CreateTournoi {

	public static void main(String[] args) {
				
		try {
			
			//création de la séquence de l'id tournoi				
			String reqSeqTournoi = "CREATE SEQUENCE seqIdTournoi START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqTournoi = DBConnection.getInstance().prepareStatement(reqSeqTournoi);
			stSeqTournoi.executeUpdate();
			System.out.println("Séquence tournoi créée");
			
			//création de la table tournoi
			String reqCreateTournoi = "CREATE TABLE tournoi ("
					+ "idTournoi INT PRIMARY KEY NOT NULL,"
					+ "nom VARCHAR(30),"
					+ "dateDebut DATE,"
					+ "dateFin DATE,"
					+ "notoriete VARCHAR(30),"
					+ "ouvert VARCHAR(30),"
					+ "idVainqueur INT)";
			PreparedStatement stCreateTournoi = DBConnection.getInstance().prepareStatement(reqCreateTournoi);
			stCreateTournoi.executeUpdate();
			System.out.println("Table tournoi créée");
					
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
