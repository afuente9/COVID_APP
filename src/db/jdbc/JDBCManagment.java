package db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.pojos.Patient;

public class JDBCManagment {
	
	private Connection c;
	
	// Open database connection
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./database/covid.database");
			c.createStatement().execute("PRAGMA foreign_keys = ON");
			System.out.println("Database connection opened");
		}
		catch(SQLException E) {
			System.out.println("There was a database exception.");
			E.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("There was a general exception.");
			e.printStackTrace();
		}
		
	}
	
	// Close database connection
	public void disconnect() {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("There was a problem while closing the database connection.");
			e.printStackTrace();
		}
	}
	
	// Create tables:
	public void creatTables() {
		try {
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE doctors " 
					+ "(id       			INTEGER  	 PRIMARY KEY AUTOINCREMENT,"
					+ " name     			TEXT    	 NOT NULL," 
					+ " speciality 			TEXT  		 NOT NULL,"
					+ " collegiate_number	int 		 NOT NULL,"
					+ " social_security 	TEXT  		 NOT NULL,"
					+ " heigh 				float  		 NOT NULL," 
					+ " weight 				float  		 NOT NULL,"
					+ " sex 				Sex  		 NOT NULL," 
					+ " infected 			boolean  	 NOT NULL,"
					+ " alive 				boolean  	 NOT NULL," 
					+ " hospital  			TEXT		 NOT NULL)";
			stmt1.executeUpdate(sql1);
			stmt1.close();

			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE patients " 
					+ "(id       			INTEGER  	 PRIMARY KEY AUTOINCREMENT,"
					+ " name     			TEXT    	 NOT NULL," 
					+ " hos_location 		TEXT  		 NOT NULL,"
					+ " birthday			DATE  		 NOT NULL," 
					+ " social_security   	TEXT  	 	 NOT NULL,"
					+ " heigh 				float   	 NOT NULL," 
					+ " weight 				float   	 NOT NULL,"
					+ " sex 				Sex   		 NOT NULL," 
					+ " infected 			boolean  	 NOT NULL,"
					+ " alive 				boolean  	 NOT NULL," 
					+ " hospital  			TEXT	 	 NOT NULL,"
					+ " score 				int			 NOT NULL,"
					+ " id_adm				INTEGER		 REFERENCES administration(id),"
					+ " vaccinated			boolean		 NOT NULL,"
					+ " bloodType			TEXT		 NOT NULL)";
			stmt2.executeUpdate(sql2);
			stmt2.close();

			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE lab " 
					+ "(id       		INTEGER  	 PRIMARY KEY AUTOINCREMENT,"
					+ " name     		TEXT    	 NOT NULL," 
					+ " adress	 		TEXT	 	 NOT NULL,"
					+ " cif			    TEXT  	 	 NOT NULL," 
					+ " vacciness  		TEXT	 	 NOT NULL)";
			stmt3.executeUpdate(sql3);
			stmt3.close();

			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE shipment " 
					+ "(id       		INTEGER  	PRIMARY KEY AUTOINCREMENT,"
					+ " vacciness  		int	 	 	NOT NULL,"
					+ " id_lab			INTEGER		REFERENCES lab(id),"
					+ " id_adm			INTEGER		REFERENCES administration(id))";
			stmt4.executeUpdate(sql4);
			stmt4.close();

			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE administration "
					+ "(id       				INTEGER  	PRIMARY KEY AUTOINCREMENT,"
					+ " total_vacciness  		int	 	 	NOT NULL)";
			stmt5.executeUpdate(sql5);
			stmt5.close();

			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE other_pathologies " 
					+ "(id       		INTEGER  	 PRIMARY KEY AUTOINCREMENT,"
					+ " name  			TEXT	 	 NOT NULL)";
			stmt6.executeUpdate(sql6);
			stmt6.close();

			Statement stmt7 = c.createStatement();
			String sql7 = "CREATE TABLE medication " 
					+ "(id       		INTEGER  	PRIMARY KEY AUTOINCREMENT,"
					+ " name  			TEXT	 	NOT NULL)";
			stmt7.executeUpdate(sql7);
			stmt7.close();
			
			Statement stmt8 = c.createStatement();
			String sql8 = "CREATE TABLE pat_doc " 
					+ "(id_doc       	INTEGER  	REFERENCES doctors(id),"
					+ " id_pat 			INTEGER	 	REFERENCES pathient(id),"
					+ " PRIMARY KEY (id_doc, id_pat))";
			stmt8.executeUpdate(sql8); //TODO syntax error
			stmt8.close();
			
			Statement stmt9 = c.createStatement();
			String sql9 = "CREATE TABLE pat_patho " 
					+ "(id_pat       	INTEGER  	REFERENCES pathient(id),"
					+ " id_patho 		INTEGER	 	REFERENCES other_pathologies(id),"
					+ " PRIMARY KEY (id_pat, id_patho))";
			stmt9.executeUpdate(sql9);
			stmt9.close();

			Statement stmt10 = c.createStatement();
			String sql10 = "CREATE TABLE pat_medi " 
					+ "(id_pat       	INTEGER  	REFERENCES pathient(id),"
					+ " id_medi 		INTEGER	 	REFERENCES medication(id),"
					+ " PRIMARY KEY (id_pat, id_medi))";
			stmt10.executeUpdate(sql10);
			stmt10.close();
			
			Statement stmt11 = c.createStatement();
			String sql11 = "CREATE TABLE pat_lab " 
					+ "(id_pat       	INTEGER  	REFERENCES pathient(id),"
					+ " id_lab 			INTEGER	 	REFERENCES lab(id),"
					+ " PRIMARY KEY (id_pat, id_lab))";
			stmt11.executeUpdate(sql11);
			stmt11.close();
			
		} catch (Exception e) {
			if(!e.getMessage().contains("already exist")) {
				e.printStackTrace();
			}
		}
	}
	
	public void addPatient(Patient p) {
		Statement stmt;
		try {
			stmt = c.createStatement();
			String sql = "INSERT INTO Patient (name, birthday, social_security, height, weight, sex, infected, alive, hospital, hos_location "
					+ "VALUES ('" + p.getName() + ", " + p.getBirthday() + ", " + p.getSocial_security() + ", " + p.getheight() + ", " + p.getSex() + ", " 
					+ p.isInfected() + ", " + p.isAlive() + ", " + p.getHospital() + ", " + p.getHos_location() + "')";
			stmt.executeUpdate(sql);
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Patient> searchPatientByName(String name){
		List<Patient> patients = new ArrayList<Patient>();
		Statement stmt;
		try {
			stmt = c.createStatement();
			String sql = "SELECT * FROM  patients WHERE name LIKE '%" + name + "%'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String patientname = rs.getString("name");
				Patient patient = new Patient(id, patientname); //TODO add all atributes
				patients.add(patient); 
			}
			rs.close();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return patients;
	}

	
	

}
