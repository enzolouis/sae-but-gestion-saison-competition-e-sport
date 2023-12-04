package sqlrequests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.DBConnection;

public class CreateAdmin {

	public static void main(String[] args) { 
				
	
		try {
			
	//création de la séquence de l'identifiant admin
			String reqSeqAdmin = "CREATE OR REPLACE SEQUENCE seqIdAdmin START WITH 1 INCREMENT BY 1";
			PreparedStatement stSeqAdmin = DBConnection.getInstance().prepareStatement(reqSeqAdmin);
			stSeqAdmin.executeUpdate();
			System.out.println("Séquence admin créée");
			
			//création de la table admin
			String reqCreateAdmin = "CREATE OR REPLACE TABLE admin"
							+ "(idAdmin INT PRIMARY KEY NOT NULL,"
							+ "nom VARCHAR(50),"
							+ "login VARCHAR(50),"
							+ "motDePasse VARCHAR(50))";
			PreparedStatement stCreateAdmin = DBConnection.getInstance().prepareStatement(reqCreateAdmin);
			stCreateAdmin.executeUpdate();
			System.out.println("Table admin créée");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
