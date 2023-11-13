import java.sql.*;

public class test {

	public static void main(String[] args) {
		
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			String urlConnexion = "jdbc:derby:BDDSaeEsport:create=true";
			Connection dbConnection = DriverManager.getConnection(urlConnexion);
			System.out.println("Connexion ok");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
