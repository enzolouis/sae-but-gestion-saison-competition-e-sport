import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class createTablesAdminArbitre {

	public static void main(String[] args) {
		
		//à ne pas re-exéucter!! à servi à créer des tables SQL
		//avec Git, la base de données est push aussi 
		
		String dirProjetJava = System.getProperty("user.dir");
		System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
		
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
			Connection dbConnection = DriverManager.getConnection(urlConnexion);
			System.out.println("Connexion ok");
			String reqSeqArbitre = "CREATE SEQUENCE seqIdArbitre START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqArbitre = dbConnection.prepareStatement(reqSeqArbitre);
			stSeqArbitre.executeUpdate();
			System.out.println("Séquence arbitre créée");
			String reqCreateArbitre = "CREATE TABLE arbitre ("
					+ "idArbitre INT PRIMARY KEY NOT NULL,"
					+ "nom VARCHAR(50),"
					+ "prenom VARCHAR(50),"
					+ "nationalite VARCHAR(50))";
			PreparedStatement stCreateArbitre = dbConnection.prepareStatement(reqCreateArbitre);
			stCreateArbitre.executeUpdate();
			System.out.println("Table arbitre créée");
			String reqSeqAdmin = "CREATE SEQUENCE seqIdAdmin START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqAdmin = dbConnection.prepareStatement(reqSeqAdmin);
			stSeqAdmin.executeUpdate();
			System.out.println("Séquence admin créée");
			String reqCreateAdmin = "CREATE TABLE admin"
					+ "(idAdmin INT PRIMARY KEY NOT NULL,"
					+ "nom VARCHAR(50),"
					+ "login VARCHAR(50),"
					+ "motDePasse VARCHAR(50))";
			PreparedStatement stCreateAdmin = dbConnection.prepareStatement(reqCreateAdmin);
			stCreateAdmin.executeUpdate();
			System.out.println("Table admin créée");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
