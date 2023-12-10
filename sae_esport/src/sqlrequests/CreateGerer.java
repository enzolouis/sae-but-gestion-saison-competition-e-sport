package sqlrequests;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.DBConnection;

public class CreateGerer {
	public static void main(String[] args) {
		
		try {
			
			//création de la séquence de l'identifiant d'équipe
			String reqSeqEquipe = "CREATE SEQUENCE seqGerer START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqEquipe = DBConnection.getInstance().prepareStatement(reqSeqEquipe);
			stSeqEquipe.executeUpdate();
			System.out.println("Séquence equipe créée");
			
			//Creation de table gerer
			String reqCreateEquipe = "CREATE TABLE Gérer("
					+ "idTournoi INT,"
					+ "idArbitre INT,"
					+ "PRIMARY KEY(idTournoi, idArbitre),"
					+ "FOREIGN KEY(idTournoi) REFERENCES Tournoi(idTournoi),"
					+ "FOREIGN KEY(idArbitre) REFERENCES Arbitre(idArbitre))"
					;
			PreparedStatement stCreateEquipe = DBConnection.getInstance().prepareStatement(reqCreateEquipe);
			stCreateEquipe.executeUpdate();
			System.out.println("Table equipe créée");
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
