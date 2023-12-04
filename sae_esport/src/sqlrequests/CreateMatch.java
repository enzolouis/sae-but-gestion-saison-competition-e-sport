package sqlrequests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.DBConnection;

public class CreateMatch {
	public static void main(String[] args) {

		try {
			
			//création de la séquence de l'identifiant arbitre
			String reqSeqArbitre = "CREATE SEQUENCE seqIdMatch START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqJoueur = DBConnection.getInstance().prepareStatement(reqSeqArbitre);
			stSeqJoueur.executeUpdate();
			System.out.println("Séquence arbitre créée");
			
			//création de la table arbitre
			String reqCreateJoueur = "CREATE TABLE match ("
					+ "idMatch INT PRIMARY KEY NOT NULL,"
					+ "finale LOGICAL"
					+ "idEquipe INT NOT NULL"
					+ "FIREIGN KEY(IdEquipe) REFERENCES Equipe(idEquipe)";
			PreparedStatement stCreateJoueur= DBConnection.getInstance().prepareStatement(reqCreateJoueur);
			stCreateJoueur.executeUpdate();
			System.out.println("Table joueur créée");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
