package sqlrequests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.DBConnection;

public class CreateArbitre {

	public static void main(String[] args) {
	
		try {
			
			//création de la séquence de l'identifiant arbitre
			String reqSeqArbitre = "CREATE SEQUENCE seqIdArbitre START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqArbitre = DBConnection.getInstance().prepareStatement(reqSeqArbitre);
			stSeqArbitre.executeUpdate();
			System.out.println("Séquence arbitre créée");
			
			//création de la table arbitre
			String reqCreateArbitre = "CREATE TABLE arbitre ("
					+ "idArbitre INT PRIMARY KEY NOT NULL,"
					+ "nom VARCHAR(50),"
					+ "prenom VARCHAR(50),"
					+ "nationalite VARCHAR(50))";
			PreparedStatement stCreateArbitre = DBConnection.getInstance().prepareStatement(reqCreateArbitre);
			stCreateArbitre.executeUpdate();
			System.out.println("Table arbitre créée");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
