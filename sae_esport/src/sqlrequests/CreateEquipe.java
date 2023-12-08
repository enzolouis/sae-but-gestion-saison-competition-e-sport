package sqlrequests;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.DBConnection;

public class CreateEquipe {
	
public static void main(String[] args) {
		
		try {
			//création de la séquence de l'identifiant d'équipe
			String reqSeqEquipe = "CREATE OR REPLACE SEQUENCE seqIdEquipe START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqEquipe = DBConnection.getInstance().prepareStatement(reqSeqEquipe);
			stSeqEquipe.executeUpdate();
			System.out.println("Séquence equipe créée");
			
			//Creation de la table Equipe
			String reqCreateEquipe = "CREATE OR REPLACE TABLE equipe("
					+ "idEquipe INTEGER PRIMARY KEY NOT NULL,"
					+ "nom VARCHAR(50),"
					+ "nationalite VARCHAR(50),"
					+ "rangSaisonPrecedente INTEGER,"
					+ "pointsSaison INTEGER,"
					+ "disposee VARCHAR(10))";
			PreparedStatement stCreateEquipe = DBConnection.getInstance().prepareStatement(reqCreateEquipe);
			stCreateEquipe.executeUpdate();
			System.out.println("Table equipe créée");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
