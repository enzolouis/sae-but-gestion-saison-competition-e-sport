import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class createTablesTournoiEquipeParticipation {
public static void main(String[] args) {
		
		//à ne pas re-exéucter!! à servi à créer des tables SQL
		//avec Git, la base de données est push aussi 
		
		String dirProjetJava = System.getProperty("user.dir");
		System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
		
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
			Connection dbConnection = DriverManager.getConnection(urlConnexion);
			System.out.println("Connexion createTablesTournoiEquipeParticipation ok");
			
			
			
			String reqSeqTournoi = "CREATE SEQUENCE seqIdTournoi START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqTournoi = dbConnection.prepareStatement(reqSeqTournoi);
			stSeqTournoi.executeUpdate();
			System.out.println("Séquence tournoi créée");
			String reqCreateTournoi = "CREATE TABLE tournoi ("
					+ "idTournoi INT PRIMARY KEY NOT NULL,"
					+ "nom VARCHAR(30),"
					+ "dateDebut DATE,"
					+ "dateFin DATE,"
					+ "notoriete VARCHAR(30),"
					+ "vainqueur INT,"
					+ "ouvert VARCHAR(30))";
			PreparedStatement stCreateTournoi = dbConnection.prepareStatement(reqCreateTournoi);
			stCreateTournoi.executeUpdate();
			System.out.println("Table tournoi créée");
			
			String reqSeqEquipe = "CREATE SEQUENCE seqIdEquipe START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqEquipe = dbConnection.prepareStatement(reqSeqEquipe);
			stSeqEquipe.executeUpdate();
			System.out.println("Séquence equipe créée");
			String reqCreateEquipe = "CREATE TABLE equipe"
					+ "(idEquipe INT PRIMARY KEY NOT NULL,"
					+ "nom VARCHAR(50),"
					+ "nationalite VARCHAR(50),"
					+ "rangSaisonPrecedente INT,"
					+ "pointsSaison INT,"
					+ "disposee VARCHAR(30))";
			PreparedStatement stCreateEquipe = dbConnection.prepareStatement(reqCreateEquipe);
			stCreateEquipe.executeUpdate();
			System.out.println("Table equipe créée");
			
			
			String reqCreateParticipation = "CREATE TABLE participation"
					+ "(resultat INT,"
					+ "idTournoi INT,"
					+ "idEquipe INT,"
					+ "CONSTRAINT fkIdTournoi FOREIGN KEY (idTournoi) REFERENCES Tournoi(idTournoi),"
					+ "CONSTRAINT fkIdEquipe FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe))";
			
			PreparedStatement stCreateParticipation = dbConnection.prepareStatement(reqCreateParticipation);
			stCreateParticipation.executeUpdate();
			System.out.println("Table participation créée");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
