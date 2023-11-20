import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import DAOs.AdministrateurDAO;
import classes.Administrateur;

public class insertValues {

	public static void main(String[] args) throws Exception {

		String dirProjetJava = System.getProperty("user.dir");
		System.setProperty("derby.system.home", dirProjetJava+"/BDDSAEEsport");
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		String urlConnexion = "jdbc:derby:BDDSAEEsport;create=true";
		Connection dbConnection = DriverManager.getConnection(urlConnexion);
		AdministrateurDAO adminDAO = new AdministrateurDAO(dbConnection);
		adminDAO.add(new Administrateur(0, "chat", "bidule", "mdp1"));
		adminDAO.add(new Administrateur(0, "chien", "trucmuche", "mdp2"));
		List<Administrateur> admins = adminDAO.getAll();
		for (Administrateur a : admins) {
			System.out.println(a);
		}

	}

}
