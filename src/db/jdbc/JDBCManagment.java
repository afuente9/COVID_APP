package db.jdbc;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import db.GUI.Main;
import db.interfaces.Cov_Manager;
import db.pojos.*;
import db.pojos.users.User;

public class JDBCManagment implements Cov_Manager {

	private Connection c;

	// Open database connection
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./database/covid.database");
			c.createStatement().execute("PRAGMA foreign_keys = ON");
			System.out.println("Database connection opened");
			this.creatTables();
		} catch (SQLException E) {
			System.out.println("There was a database exception.");
			E.printStackTrace();
		} catch (Exception e) {
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
					+ "(id       			INTEGER  	 	PRIMARY KEY AUTOINCREMENT,"
					+ " name     			TEXT    	 	NOT NULL,"
					+ " speciality 			TEXT  		 	NOT NULL,"
					+ " birth_date			DATE		 	NOT NULL,"
					+ " collegiate_number	TEXT	 	 	NOT NULL,"
					+ " sex 				TEXT	 	 	NOT NULL,"
					+ " hospital  			TEXT		 	NOT NULL," 
					+ " image 				BLOB   			NULL,"
					+ " user_id				INTEGER			REFERENCES users(id))";
			stmt1.executeUpdate(sql1);
			stmt1.close();

			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE patients " 
					+ "(id       			INTEGER  	 PRIMARY KEY AUTOINCREMENT,"
					+ " name     			TEXT    	 NOT NULL," 
					+ " hos_location 		TEXT  		 NOT NULL,"
					+ " birthday			DATE  		 NOT NULL," 
					+ " social_security   	TEXT  	 	 NOT NULL,"
					+ " height 				float   	 NOT NULL,"
					+ " weight 				float   	 NOT NULL,"
					+ " sex 			    TEXT	   	 NOT NULL," 
					+ " infected 			boolean  	 NOT NULL,"
					+ " alive 				boolean  	 NOT NULL,"
					+ " hospital  			TEXT	 	 NOT NULL,"
					+ " score 				INTEGER		 NOT NULL,"
					+ " id_adm				INTEGER		 REFERENCES administration(id),"
					+ " vaccinated			boolean		 NOT NULL," 
					+ " bloodType			TEXT		 NOT NULL,"
					+ " dateIntroduced      DATE  		 NOT NULL)";
			stmt2.executeUpdate(sql2);
			stmt2.close();

			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE lab " 
					+ "(id       		INTEGER  	 	PRIMARY KEY AUTOINCREMENT,"
					+ " name     		TEXT    	 	NOT NULL," 
					+ " adress	 		TEXT	 	 	NOT NULL,"
					+ " cif			    TEXT  	 	 	NOT NULL," 
					+ " vacciness  		INTEGER	 	 	NOT NULL,"
					+ " image 			BLOB   			NULL,"
					+ " user_id			INTEGER			REFERENCES users(id))";
			stmt3.executeUpdate(sql3);
			stmt3.close();
//We also have to add the name of the lab because tableView only print pojos
			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE shipment " 
					+ "(id       		INTEGER  	PRIMARY KEY AUTOINCREMENT,"
					+ " vacciness  		INTEGER	 	NOT NULL,"
					+ " date			DATE		NOT NULL,"
					+ " id_lab			INTEGER		REFERENCES lab(id)," 
					+ " labName			TEXT		 NOT NULL,"
					+ " id_adm			INTEGER		REFERENCES administration(id))";
			stmt4.executeUpdate(sql4);
			stmt4.close();

			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE administration "
					+ "(id       				INTEGER  		PRIMARY KEY AUTOINCREMENT,"
					+ " total_vacciness  		INTEGER	 		NOT NULL,"
					+ " name					TEXT			NOT NULL,"
					+ " image 				    BLOB            NULL,"
					+ " user_id				INTEGER			REFERENCES users(id))";
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
					+ " name  			TEXT	 	UNIQUE NOT NULL)";
			stmt7.executeUpdate(sql7);
			stmt7.close();

			Statement stmt8 = c.createStatement();
			String sql8 = "CREATE TABLE pat_doc " + "(id_doc       	INTEGER  	REFERENCES doctors(id),"
					+ " id_pat 			INTEGER	 	REFERENCES patients(id)," + " PRIMARY KEY (id_doc, id_pat))";
			stmt8.executeUpdate(sql8);
			stmt8.close();

			Statement stmt9 = c.createStatement();
			String sql9 = "CREATE TABLE pat_patho " + "(id_pat       	INTEGER  	REFERENCES patients(id),"
					+ " id_patho 		INTEGER	 	REFERENCES other_pathologies(id),"
					+ " PRIMARY KEY (id_pat, id_patho))";
			stmt9.executeUpdate(sql9);
			stmt9.close();

			Statement stmt10 = c.createStatement();
			String sql10 = "CREATE TABLE pat_medi " 
					+ "(id_pat       	INTEGER  	REFERENCES patients(id),"
					+ " id_medi 		INTEGER	 	REFERENCES medication(id)," 
					+ " PRIMARY KEY (id_pat, id_medi))";
			stmt10.executeUpdate(sql10);
			stmt10.close();

			Statement stmt11 = c.createStatement();
			String sql11 = "CREATE TABLE pat_lab "
					+ "(id_pat       	INTEGER  	REFERENCES patients(id),"
					+ " id_lab 			INTEGER	 	REFERENCES lab(id)," 
					+ " PRIMARY KEY (id_pat, id_lab))";
			stmt11.executeUpdate(sql11);
			stmt11.close();

			Statement stmt12 = c.createStatement();
			String sql12 = "CREATE TABLE days " 
					+ "(id       	   				INTEGER  	PRIMARY KEY AUTOINCREMENT,"
					+ " deaths 						INTEGER	 	NOT NULL,"
					+ " infectedPatients 			INTEGER	 	NOT NULL," 
					+ " average						FLOAT		NOT NULL,"
					+ " daytime						DATE 		NOT NULL)";
			stmt11.executeUpdate(sql12);
			stmt11.close();
			
		} catch (Exception e) {
			if (!e.getMessage().contains("already exist")) {
				e.printStackTrace();
			}
		}
	}

	// TODO INSERT & SELECT pat_medi, pat_pathologies
	public void addPatient(Patient p) {
		try {
			String sexo;
			if (p.getSex().equals(Sex.Male)) {
				sexo = "M";
			} else {
				sexo = "F";
			}
			String sql = "INSERT INTO patients (name, birthday, social_security, height, weight, sex, infected, alive, hospital, hos_location, score,id_adm, bloodType, vaccinated, dateIntroduced)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, p.getName());
			prep.setDate(2, p.getBirthday());
			prep.setString(3, p.getSocial_security());
			prep.setFloat(4, p.getHeight());
			prep.setFloat(5, p.getWeight());
			prep.setString(6, sexo);
			prep.setBoolean(7, p.isInfected());
			prep.setBoolean(8, p.isAlive());
			prep.setString(9, p.getHospital());
			prep.setString(10, p.getHos_location());
			prep.setInt(11, 0); 
			prep.setInt(12, p.getGovId());

			prep.setString(13, p.getBloodType());
			prep.setBoolean(14, p.getVaccinated());
			prep.setDate(15, p.getDateIntroduced());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * TODO checklist - Cambiar foto (no prioritario) - AddPatients (faltan los
	 * medicamentos y otras enfermedades) - SelectPatients seg�n fechaIntroduccion
	 * - SelectPatients seg�n sus atributos - ModifyPatient todos sus atributos -
	 * ModifyDoc todos sus atributos - ModifyLab datosLab, nVacc - Select nVacc from
	 * Gov - Select patients by score for Gov - Modify nVacc from Gov - Select
	 * Shipment by name
	 * 
	 */

	public void addDoctor(Doctor d) {
		try {
			String sexo;
			if (d.getSex().equals(Sex.Male)) {
				sexo = "M";
			} else {
				sexo = "F";
			}
			String sql = "INSERT INTO doctors (name, speciality, birth_date, collegiate_number, sex, hospital) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, d.getName());
			prep.setString(2, d.getSpeciality());
			prep.setDate(3, d.getBirthday());
			prep.setString(4, d.getCollegiate_number());
			prep.setString(5, sexo);
			prep.setString(6, d.getHospital());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDoctorUser(Doctor d, User u) {
		try {
			String sexo;
			if (d.getSex().equals(Sex.Male)) {
				sexo = "M";
			} else {
				sexo = "F";
			}
			String sql = "INSERT INTO doctors (name, speciality, birth_date, collegiate_number, sex, hospital, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, d.getName());
			prep.setString(2, d.getSpeciality());
			prep.setDate(3, d.getBirthday());
			prep.setString(4, d.getCollegiate_number());
			prep.setString(5, sexo);
			prep.setString(6, d.getHospital());
			prep.setInt(7, u.getId());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addLab(Lab l) {
		try {

			String sql = "INSERT INTO lab (name, adress, cif, vacciness) VALUES (?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, l.getName());
			prep.setString(2, l.getAddress());
			prep.setString(3, l.getCif());
			prep.setInt(4, l.getVaccines_produce());
			prep.executeUpdate();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addLabUser(Lab l, User u) {
		try {

			String sql = "INSERT INTO lab (name, adress, cif, vacciness, user_id) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, l.getName());
			prep.setString(2, l.getAddress());
			prep.setString(3, l.getCif());
			prep.setInt(4, l.getVaccines_produce());
			prep.setInt(5, u.getId());
			prep.executeUpdate();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addShipment(Shipment s, Lab l, Administration a) {
		try {
			String sql = "INSERT INTO Shipment (vacciness, date, id_lab, labName, id_adm) VALUES (?,?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, s.getVaccines());
			prep.setDate(2, s.getDate_ship());
			prep.setInt(3, l.getId());
			prep.setString(4, l.getName());
			prep.setInt(5, a.getId());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addGoverment(Administration a) {
		try {
			String sql = "INSERT INTO Administration (total_vacciness, name) VALUES (?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, a.getVaccines());
			prep.setString(1, a.getName());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addGovermentUser(Administration a, User u) {
		try {
			String sql = "INSERT INTO Administration (total_vacciness, name, user_id) VALUES (?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, a.getVaccines());
			prep.setString(1, a.getName());
			prep.setInt(3, u.getId());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// TODO UPDATE goverment vaccines used, patient (pic from whatsapp group), all
	// doctor including image, all lab
	public void addOtherPathologies(Other_Pathologies op) {
		try {
			String sql = "INSERT INTO Other_Pathologies (name) VALUES (?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, op.getName());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addMedication(Medication m) {
		try {
			String sql = "INSERT INTO medication (name) VALUES (?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, m.getName());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifyDoctor(int iden, String atrib, String value) {
		try {
			String sql;
			Date fecha = null;
			if (atrib.equalsIgnoreCase("birth_date")) {
				fecha = Date.valueOf(value);
				sql = "UPDATE doctors SET " + atrib + " = ? WHERE id = " + iden;
			} else {
				sql = "UPDATE doctors SET " + atrib + " = ? WHERE id = " + iden;
			}
			PreparedStatement prep = c.prepareStatement(sql);
			if (atrib.equalsIgnoreCase("birth_date")) {
				prep.setDate(1, fecha);
			} else {
				prep.setString(1, value);
			}
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modifyPatient(int iden, String atrib, String value) {
		try {
			String sql;
			Date fecha = null;
			if (atrib.equalsIgnoreCase("birthday")) {

				fecha = Date.valueOf(value);
				sql = "UPDATE patients SET " + atrib + " = ? WHERE id = " + iden;
			} else {
				sql = "UPDATE patients SET " + atrib + " = ? WHERE id = " + iden;
			}
			PreparedStatement prep = c.prepareStatement(sql);
			if (atrib.equalsIgnoreCase("birthday")) {
				prep.setDate(1, fecha);
			} else if (atrib == "height" || atrib == "weight") {
				prep.setFloat(1, Float.parseFloat(value));

			} else if (atrib == "infected" || atrib == "alive" || atrib == "vaccinated") {
				prep.setBoolean(1, Boolean.parseBoolean(value));

			}

			else {

				prep.setString(1, value);
			}
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void modifyScore(int id, float value) {
		try {

				String sql = "UPDATE patients SET score = ? WHERE id = " + id;
			
			PreparedStatement prep = c.prepareStatement(sql);
             prep.setFloat(1, value);
			
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modifyLab(int iden, String atrib, String value) {
		try {
			String sql;
			sql = "UPDATE lab SET " + atrib + " = ? WHERE id = " + iden;
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, value);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void ModifyVaccinesFromLab(int amount, int id) {
		try {
			String sql;
			sql = "UPDATE lab SET vacciness = (vacciness + ?) WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, amount);
			prep.setInt(2, id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void ModifyVaccinesAdmin(int amount) {
		try {
			String sql;
			sql = "UPDATE administration SET total_vacciness = (total_vacciness + ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, amount);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Patient> searchPatientByName(String name) {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM  patients WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String patientname = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String social_security = rs.getString("social_security");
				float height = rs.getFloat("height");
				float weight = rs.getFloat("weight");
				Sex sexo;
				if (rs.getString("sex").equalsIgnoreCase("m")) {
					sexo = Sex.Male;
				} else {
					sexo = Sex.Female;
				}
				boolean infec = rs.getBoolean("infected");
				boolean alive = rs.getBoolean("alive");
				String hosp = rs.getString("hospital");
				String loc_hosp = rs.getString("hos_location");
				String blood = rs.getString("bloodType");
				boolean vaccin = rs.getBoolean("vaccinated");
				Date dateIntroduced = rs.getDate("dateIntroduced");
				List<Medication> medicationPatient = Main.getInter().getMedicationfromPatient(id);
				List<Other_Pathologies> Other_Pathologies = Main.getInter().getPathofromPatient(id);

				Patient patient = new Patient(id, loc_hosp, patientname, birthday, social_security, height, weight,
						sexo, infec, alive, hosp, vaccin, blood, dateIntroduced, medicationPatient, Other_Pathologies);
				patients.add(patient);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}

	@Override
	public Patient getPatient(int id) {
		try {
			String sql = "SELECT * FROM patients WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				String patientName = rs.getString("name");
				String hos_loc = rs.getString("hos_location");
				Date bdate = rs.getDate("birthday");
				String socsec = rs.getString("social_security");
				float high = rs.getFloat("height");
				float weig = rs.getFloat("weight");
				String sexo = rs.getString("sex");
				Sex sexoo;
				if (sexo.equalsIgnoreCase("M")) {
					sexoo = Sex.Male;
				} else {
					sexoo = Sex.Female;
				}
				boolean infec = rs.getBoolean("infected");
				boolean vivo = rs.getBoolean("alive");
				String hos = rs.getString("hospital");
				boolean vacc = rs.getBoolean("vaccinated");
				String sangre = rs.getString("bloodType");
				Date dateIntroduced = rs.getDate("dateIntroduced");
				List<Medication> medicationPatient = Main.getInter().getMedicationfromPatient(id);
				List<Other_Pathologies> Other_Pathologies = Main.getInter().getPathofromPatient(id);
				Patient p= new Patient(id, hos_loc, patientName, bdate, socsec, high, weig, sexoo, infec, vivo, hos, vacc,
						sangre, dateIntroduced, medicationPatient, Other_Pathologies);
				return p;
			}
			prep.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Patient getLastPatient() {
		try {
			String sql = "SELECT * FROM patients WHERE id = (SELECT MAX(id) FROM patients)";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				Integer id = rs.getInt("id");

				String patientName = rs.getString("name");
				String hos_loc = rs.getString("hos_location");
				Date bdate = rs.getDate("birthday");
				String socsec = rs.getString("social_security");
				float high = rs.getFloat("height");
				float weig = rs.getFloat("weight");
				String sexo = rs.getString("sex");
				Sex sexoo;
				if (sexo.equalsIgnoreCase("M")) {
					sexoo = Sex.Male;
				} else {
					sexoo = Sex.Female;
				}
				boolean infec = rs.getBoolean("infected");
				boolean vivo = rs.getBoolean("alive");
				String hos = rs.getString("hospital");
				boolean vacc = rs.getBoolean("vaccinated");
				String sangre = rs.getString("bloodType");
				Date dateIntroduced = rs.getDate("dateIntroduced");

				return new Patient(id, hos_loc, patientName, bdate, socsec, high, weig, sexoo, infec, vivo, hos, vacc,
						sangre, dateIntroduced);
			}
			prep.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Patient> getPatientsOfDoctor(int doc_id) {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM pat_doc WHERE id_doc = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, doc_id);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int patientId = rs.getInt("id_pat");
				patients.add(this.getPatient(patientId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}

	@Override
	public Doctor getDoctor(int id) {
		try {
			String sql = "SELECT * FROM doctors WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				String doctorName = rs.getString("name");
				String espe = rs.getString("speciality");
				Date nacido = rs.getDate("birth_date");
				String numCol = rs.getString("collegiate_number");
				String hos = rs.getString("hospital");
				byte[] foto = rs.getBytes("image");
				String sexo = rs.getString("sex");
				Sex sexoo;
				if (sexo.equalsIgnoreCase("M")) {
					sexoo = Sex.Male;
				} else {
					sexoo = Sex.Female;
				}

				return new Doctor(id, espe, doctorName, nacido, numCol, sexoo, hos, foto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Doctor> getDoctorsOfPatient(int pat_id) {
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			String sql = "SELECT * FROM pat_doc WHERE id_pat = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, pat_id);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int doctorId = rs.getInt("id_doc");
				doctors.add(this.getDoctor(doctorId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctors;
	}

	@Override
	public Other_Pathologies getLastPath() {

		try {
			String sql = "SELECT * FROM other_pathologies WHERE id = (SELECT MAX(id) FROM other_pathologies)";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				Integer id_path = rs.getInt("id");
				String name = rs.getString("name");
				return new Other_Pathologies(id_path, name);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void assignPatho(int id, Other_Pathologies op) {
		try {
			String sql = "INSERT INTO pat_patho (id_pat, id_patho) VALUES (?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			prep.setInt(2, op.getId());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void assignMed(int id, Medication m) {
		try {

			String sql = "INSERT INTO pat_medi (id_pat, id_medi) VALUES (?, ?)";

			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);

			prep.setInt(2, m.getId());
			prep.executeUpdate();

			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override  //TODO hay que hacer un modify admin??
	public Administration getAdministration() {
		try {
			String sql = "SELECT * FROM administration WHERE id = 1";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int vacc = rs.getInt("total_vacciness");
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				return new Administration(id, vacc, name);
			}
			prep.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	

	@Override
	public List<Shipment> getAllShipment() {
		List<Shipment> allShips = new ArrayList();
		try {
			String sql = "SELECT * FROM shipment ";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int vacc = rs.getInt("vacciness");
				Date d1 = rs.getDate("date");
				String labName = rs.getString("labName");
				allShips.add(new Shipment(vacc, d1, labName));
			}

			prep.close();
			rs.close();
			return allShips;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return allShips;
	}
	
	@Override
	public List<String> getdifferentHospitals(boolean alive) {
		List<String> Hospitals = new ArrayList();
		try {
			String sql = "SELECT * FROM patients WHERE alive = ? ";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setBoolean(1, alive);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
               String hospital = rs.getString("hospital");
             if (!Hospitals.contains(hospital)) {
            	 Hospitals.add(hospital);
             }
			}

			prep.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Hospitals;
	}
	
	@Override
	public List<String> getdifferentMeds(boolean alive) {
		List<String> Medications = new ArrayList();
		try {
			String sql = "SELECT med.name FROM medication AS med JOIN pat_medi AS pm ON pm.id_medi=med.id JOIN patients AS p ON p.id=pm.id_pat WHERE (p.id>0 AND alive = ?)  ";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setBoolean(1, alive);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
               String name = rs.getString("name");
               Medications.add(name);

              
			}

			prep.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		Medications = Medications.stream().distinct().collect(Collectors.toList());

		return Medications;
	}
	
	@Override
	public int getdifferentMedsCOUNT(String name, boolean alive) {
		int times=0;
		try {
			String sql = "SELECT p.id FROM patients AS p JOIN pat_medi AS pm ON pm.id_pat=p.id JOIN medication AS m ON m.id=pm.id_medi WHERE (m.name = '"+name+"' AND alive = ? )";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setBoolean(1, alive);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				//System.out.println("el nombre del paciente es"+ rs.getInt("id"));
             times++;
			}

			prep.close();
			rs.close();
			return times;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return times;
	}
	
	@Override
	public List<String> getdifferentPaths(boolean alive) {
		List<String> Pathologies = new ArrayList();
		try {
			String sql = "SELECT path.name FROM other_pathologies AS path JOIN pat_patho AS pp ON pp.id_patho=path.id JOIN patients AS p ON p.id=pp.id_pat WHERE (p.id>0 AND alive = ?)  ";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setBoolean(1, alive);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
               String name = rs.getString("name");
             if (!Pathologies.contains(name)) {
            	 Pathologies.add(name);
             }
			}

			prep.close();
			rs.close();
			return Pathologies;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Pathologies;
	}
	
	@Override
	public int getdifferentPathsCOUNT(String name, boolean alive) {
int times=0;
try {
			String sql = "SELECT p.id FROM patients AS p JOIN pat_patho AS pp ON pp.id_pat=p.id JOIN other_pathologies AS path ON path.id=pp.id_patho WHERE (path.name = '"+name+"' AND alive = ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setBoolean(1, alive);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
             times++;
			}

			prep.close();
			rs.close();
			return times;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return times;
	}	
	
	@Override
	public List<Date> getDates(boolean alive) {
		List<Date> dates = new ArrayList();
		try {
			String sql = "SELECT * FROM patients WHERE alive = ? ";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setBoolean(1, alive);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
               Date date = rs.getDate("birthday");
               dates.add(date);
			}

			prep.close();
			rs.close();
			return dates;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dates;
	}

	@Override
	public List<Shipment> getAllShipmentforAdminView() {
		List<Shipment> allShips = new ArrayList();
		try {
			String sql = "SELECT * FROM shipment ";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int vacc = rs.getInt("vacciness");
				Date d1 = rs.getDate("date");
				String labName = rs.getString("labName");
				allShips.add(new Shipment(vacc, d1, labName));
			}

			prep.close();
			rs.close();
			return allShips;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return allShips;
	}

	@Override
	public int getNumberVaccinesAdmin() {
		try {
			String sql = "SELECT total_vacciness FROM administration WHERE id=1 ";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();

			int num = rs.getInt("total_vacciness");
			prep.close();
			rs.close();
			return num;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getNumberVaccinesLab(int id) {
		try {
			String sql = "SELECT vacciness FROM lab WHERE id=? ";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();

			int num = rs.getInt("vacciness");
			prep.close();
			rs.close();
			return num;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Lab getLab(int id) {
		try {
			String sql = "SELECT * FROM lab WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				String lab_name = rs.getString("name");
				String direccion = rs.getString("adress");
				String doc = rs.getString("cif");
				Integer vacc = rs.getInt("vacciness");
				byte[] pic = rs.getBytes("image");
				// TODO test, if doesn't work
				/*
				 * InputStream blobStream = rs.getBinaryStream("photo"); byte[] pic = new
				 * byte[blobStream.available()]; blobStream.read(pic);
				 * 
				 */
				return new Lab(id, vacc, direccion, lab_name, doc, pic);
			}
			prep.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Lab> showLabs() {
		List<Lab> labs = new ArrayList<Lab>();
		try {
			String lab_name = "";
			String sql = "SELECT * FROM lab WHERE name = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, lab_name);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String Lname = rs.getString("name");
				String Ladress = rs.getString("adress");
				String Lcif = rs.getString("cif");
				int Lvacciness = rs.getInt("vacciness");
				byte[] pic = rs.getBytes("image");
				// TODO test, if doesn't work
				/*
				 * InputStream blobStream = rs.getBinaryStream("photo"); byte[] pic = new
				 * byte[blobStream.available()]; blobStream.read(pic);
				 * 
				 */
				Lab lab = new Lab(id, Lvacciness, Ladress, Lname, Lcif, pic);
				labs.add(lab);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return labs;
	}

	@Override
	public void addDay(Day f) {
		try {
			String sql = "INSERT INTO days (deaths,infectedPatients, average, daytime) VALUES (?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, f.getDeaths());
			prep.setInt(2, f.getInfectedPatients());
			prep.setFloat(3, f.getAverage());
			prep.setDate(4, f.getDate());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getNumberofPatients() {
		int number = 0;
		try {
			String sql = "SELECT id FROM patients";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				number++;
			}
			rs.close();

			prep.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return number;

	}	
	
	@Override
	public int getNumberofDays() {
		int number = 0;

		try {
			String sql = "SELECT  id FROM days";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
			number++;
			}
			rs.close();

			prep.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return number;

	}
	
	@Override
	public int getNumberofDeads() {
		int number = 0;
		try {
			String sql = "SELECT id FROM patients WHERE alive = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setBoolean(1, false);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				number++;
			}
			rs.close();

			prep.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return number;

	}
	
	@Override
	public int getNumberofInfecteds() {
		int number = 0;
		try {
			String sql = "SELECT id FROM patients WHERE alive = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setBoolean(1, true);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				number++;
			}
			rs.close();

			prep.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return number;

	}
	
	@Override
	public List<Integer> getNumberofDeadsofEachDay() {
		List<Integer> listDeaths = new ArrayList <>();
		try {
			String sql = "SELECT deaths FROM days ";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
           int deaths = rs.getInt("deaths");
           listDeaths.add(deaths);
			}
			rs.close();

			prep.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return listDeaths;

	}
	
	@Override
	public List<Integer> getNumberofInfectedsofEachDay() {
		List<Integer> infectedList = new ArrayList <>();
		try {
			String sql = "SELECT infectedPatients FROM days ";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
           int infectedPatients = rs.getInt("infectedPatients");
           infectedList.add(infectedPatients);
			}
			rs.close();

			prep.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return infectedList;

	}
	
	@Override
	public List<Day> getLast7Days() {
		List<Day> savedDays = new ArrayList<Day>();
		try {
			String sql = "SELECT * FROM days ORDER BY daytime DESC";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int dayID = rs.getInt("id");
				int falle = rs.getInt("deaths");
				float media = rs.getFloat("average");
				Date dia = rs.getDate("daytime");
				savedDays.add(new Day(dayID, falle, media, dia));
			}

			for(int i =7;i<savedDays.size();i++ ) {

				savedDays.remove(i);
				
			}
			
			


			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savedDays;
	}

	@Override
	public void deleteMedByName(Medication m) {
		String sql = "DELETE FROM medication WHERE name = ?";
		PreparedStatement prep;
		try {
			prep = c.prepareStatement(sql);
			prep.setString(1, m.getName());
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deletePathologyByName(Other_Pathologies op) {
		String sql = "DELETE FROM medication WHERE name = ?";
		PreparedStatement prep;
		try {
			prep = c.prepareStatement(sql);
			prep.setString(1, op.getName());
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Patient> searchPatientGeneric(String feature, String type) {
		List<Patient> patients = new ArrayList<Patient>();
//TODO PARA LA DATE INTRODUCED
		try {
			if (feature == "SS num") {
				feature = "social_security";
			}
			if (feature == "Blood type") {
				feature = "bloodType";
			}
			String sql;
			if (feature == "Birth date") {
				feature = "birthday";
				sql = "SELECT * FROM  patients WHERE " + feature + " = '?' ";

			} else {
				sql = "SELECT * FROM  patients WHERE " + feature + " LIKE ?";
			}

			PreparedStatement prep = c.prepareStatement(sql);

			if (feature == "birthday") {

				Date d = Date.valueOf(type);
				prep.setDate(1, d);
			}

			if (feature == "Infected") {
				boolean newbool = Boolean.parseBoolean(type);
				prep.setBoolean(1, newbool);

			}

			else {
				prep.setString(1, "%" + type + "%");

			}

			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String patientname = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String social_security = rs.getString("social_security");
				float height = rs.getFloat("height");
				float weight = rs.getFloat("weight");
				Sex sexo;
				if (rs.getString("sex").equalsIgnoreCase("m")) {
					sexo = Sex.Male;
				} else {
					sexo = Sex.Female;
				}
				boolean infec = rs.getBoolean("infected");
				boolean alive = rs.getBoolean("alive");
				String hosp = rs.getString("hospital");
				String loc_hosp = rs.getString("hos_location");
				String blood = rs.getString("bloodType");
				boolean vaccin = rs.getBoolean("vaccinated");
				Date dateIntroduced = rs.getDate("dateIntroduced");
				List<Medication> medicationPatient = Main.getInter().getMedicationfromPatient(id);
				List<Other_Pathologies> Other_Pathologies = Main.getInter().getPathofromPatient(id);

				Patient patient = new Patient(id, loc_hosp, patientname, birthday, social_security, height, weight,
						sexo, infec, alive, hosp, vaccin, blood, dateIntroduced, medicationPatient, Other_Pathologies);
				patients.add(patient);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}	
	
	@Override
	public int searchPatientGenericCOUNT(String feature, String type, boolean alive) {
		int count=0;
//TODO PARA LA DATE INTRODUCED
		try {
			if (feature == "SS num") {
				feature = "social_security";
			}
			if (feature == "Blood type") {
				feature = "bloodType";
			}
			String sql;
			if (feature == "Birth date") {
				feature = "birthday";
				sql = "SELECT * FROM  patients WHERE " + feature + " = '?' ";

			} else {
				sql = "SELECT * FROM  patients WHERE( " + feature + " LIKE ? AND alive = ?)";
			}

			PreparedStatement prep = c.prepareStatement(sql);

			if (feature == "birthday") {

				Date d = Date.valueOf(type);
				prep.setDate(1, d);
			}

			if (feature == "Infected") {
				boolean newbool = Boolean.parseBoolean(type);
				prep.setBoolean(1, newbool);

			}

			else {
				prep.setString(1, "%" + type + "%");
				prep.setBoolean(2,alive);

			}

			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				count++;
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}	

	@Override
	public int getNumberPatientsbyRangeofFeature(String feature, float max, float min, boolean alive) {
		int count=0;
		try {
			String sql = "SELECT * FROM  patients WHERE( "+feature+" < "+max+" AND "+feature+" > "+min+" AND alive = ? )" ;

			

			PreparedStatement prep = c.prepareStatement(sql);

			prep.setBoolean(1, alive);

			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				count++;
			}

			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public int getNumberPatientsbyanyString(String feature, String type, boolean alive) {
		int count=0;
		try {
			String sql = "SELECT * FROM  patients WHERE("+feature+" = '" + type + "' AND alive = ?)" ;

			

			PreparedStatement prep = c.prepareStatement(sql);
			prep.setBoolean(1, alive);

			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				count++;
			}

			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Patient> getSimulatedPatients(int availableVaccines, int id0) {
		List<Patient> patients = new ArrayList<Patient>();

		try {
			
			String sql = "SELECT * FROM  patients WHERE alive = ? AND id_adm= ? ORDER BY score DESC ";

			PreparedStatement prep = c.prepareStatement(sql);
			prep.setBoolean(1, true);
			prep.setInt(2, id0);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {

				int id = rs.getInt("id");
				String patientname = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String social_security = rs.getString("social_security");
				float height = rs.getFloat("height");
				float weight = rs.getFloat("weight");
				Sex sexo;
				if (rs.getString("sex").equalsIgnoreCase("m")) {
					sexo = Sex.Male;
				} else {
					sexo = Sex.Female;
				}
				boolean infec = rs.getBoolean("infected");
				boolean alive = rs.getBoolean("alive");
				String hosp = rs.getString("hospital");
				String loc_hosp = rs.getString("hos_location");
				String blood = rs.getString("bloodType");
				boolean vaccin = rs.getBoolean("vaccinated");
				Date dateIntroduced = rs.getDate("dateIntroduced");
				List<Medication> medicationPatient = Main.getInter().getMedicationfromPatient(id);
				List<Other_Pathologies> Other_Pathologies = Main.getInter().getPathofromPatient(id);
				int score = rs.getInt("score");

				Patient patient = new Patient(id, loc_hosp, patientname, birthday, social_security, height, weight,
						sexo, infec, alive, hosp, vaccin, blood, dateIntroduced, medicationPatient, Other_Pathologies,
						score);

				patients.add(patient);

			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//TODO COMPROBAR SI FUNCIONA BIEN CUANDO TENGAMOS PUNTUACIONES

		return getFinalList(patients,availableVaccines);
	}

	private List<Patient> getFinalList(List<Patient> allpatients, int numberVaccines) {
		
	List <Patient> finalList= new ArrayList<>();
		int originalsize=allpatients.size();
		for (int i= 0; i<numberVaccines;i++) {
			finalList.add(allpatients.get(i));
			
			
		}
		return finalList;
	}

	@Override
	public List<Patient> filterPatient(String dateFrom, String dateTo) {
		List<Patient> patients = new ArrayList<Patient>();
		try {

			String sql = "SELECT * FROM  patients WHERE dateIntroduced <= ? AND dateIntroduced >= ?";

			PreparedStatement prep = c.prepareStatement(sql);

			prep.setDate(1, Date.valueOf(dateTo));

			prep.setDate(2, Date.valueOf(dateFrom));

			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String patientname = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String social_security = rs.getString("social_security");
				float height = rs.getFloat("height");
				float weight = rs.getFloat("weight");
				Sex sexo;
				if (rs.getString("sex").equalsIgnoreCase("m")) {
					sexo = Sex.Male;
				} else {
					sexo = Sex.Female;
				}
				boolean infec = rs.getBoolean("infected");
				boolean alive = rs.getBoolean("alive");
				String hosp = rs.getString("hospital");
				String loc_hosp = rs.getString("hos_location");
				String blood = rs.getString("bloodType");
				boolean vaccin = rs.getBoolean("vaccinated");
				Date dateIntroduced = rs.getDate("dateIntroduced");

				List<Medication> medicationPatient = Main.getInter().getMedicationfromPatient(id);
				List<Other_Pathologies> Other_Pathologies = Main.getInter().getPathofromPatient(id);

				Patient patient = new Patient(id, loc_hosp, patientname, birthday, social_security, height, weight,
						sexo, infec, alive, hosp, vaccin, blood, dateIntroduced, medicationPatient, Other_Pathologies);
				patients.add(patient);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}

	@Override
	public List<Patient> getPatientbyBD(String BDdate) {
		List<Patient> patients = new ArrayList<Patient>();
		try {

			String sql = "SELECT * FROM  patients WHERE birthday = ? ";

			PreparedStatement prep = c.prepareStatement(sql);

			prep.setDate(1, Date.valueOf(BDdate));

			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String patientname = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String social_security = rs.getString("social_security");
				float height = rs.getFloat("height");
				float weight = rs.getFloat("weight");
				Sex sexo;
				if (rs.getString("sex").equalsIgnoreCase("m")) {
					sexo = Sex.Male;
				} else {
					sexo = Sex.Female;
				}
				boolean infec = rs.getBoolean("infected");
				boolean alive = rs.getBoolean("alive");
				String hosp = rs.getString("hospital");
				String loc_hosp = rs.getString("hos_location");
				String blood = rs.getString("bloodType");
				boolean vaccin = rs.getBoolean("vaccinated");
				Date dateIntroduced = rs.getDate("dateIntroduced");

				List<Medication> medicationPatient = Main.getInter().getMedicationfromPatient(id);
				List<Other_Pathologies> Other_Pathologies = Main.getInter().getPathofromPatient(id);

				Patient patient = new Patient(id, loc_hosp, patientname, birthday, social_security, height, weight,
						sexo, infec, alive, hosp, vaccin, blood, dateIntroduced, medicationPatient, Other_Pathologies);
				patients.add(patient);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}

	@Override
	public List<Patient> getPatientbyDateIntro(String dateintro) {
		List<Patient> patients = new ArrayList<Patient>();
		try {

			String sql = "SELECT * FROM  patients WHERE dateIntroduced = ? ";

			PreparedStatement prep = c.prepareStatement(sql);

			prep.setDate(1, Date.valueOf(dateintro));

			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String patientname = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				String social_security = rs.getString("social_security");
				float height = rs.getFloat("height");
				float weight = rs.getFloat("weight");
				Sex sexo;
				if (rs.getString("sex").equalsIgnoreCase("m")) {
					sexo = Sex.Male;
				} else {
					sexo = Sex.Female;
				}
				boolean infec = rs.getBoolean("infected");
				boolean alive = rs.getBoolean("alive");
				String hosp = rs.getString("hospital");
				String loc_hosp = rs.getString("hos_location");
				String blood = rs.getString("bloodType");
				boolean vaccin = rs.getBoolean("vaccinated");
				Date dateIntroduced = rs.getDate("dateIntroduced");

				List<Medication> medicationPatient = Main.getInter().getMedicationfromPatient(id);
				List<Other_Pathologies> Other_Pathologies = Main.getInter().getPathofromPatient(id);

				Patient patient = new Patient(id, loc_hosp, patientname, birthday, social_security, height, weight,
						sexo, infec, alive, hosp, vaccin, blood, dateIntroduced, medicationPatient, Other_Pathologies);
				patients.add(patient);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}

	@Override
	public List<Medication> searchMedicationByName(String name) {
		List<Medication> medicaciones = new ArrayList<Medication>();
		try {
			String sql = "SELECT * FROM medication WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int med_id = rs.getInt("id");
				String med_name = rs.getString("name");
				Medication medi = new Medication(med_id, med_name);
				medicaciones.add(medi);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicaciones;
	}
	@Override
	public int searchadminIDByName(String name) {
int id=0;
		try {
			String sql = "SELECT id FROM admin WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				 id = rs.getInt("id");
				
				
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Medication getLastMedication() {
		try {
			String sql = "SELECT * FROM medication WHERE id = (SELECT MAX(id) FROM medication)";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				Integer id_med = rs.getInt("id");
				String nombre = rs.getString("name");
				return new Medication(id_med, nombre);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Day getLastDay() {
		try {
			String sql = "SELECT * FROM days WHERE id = (SELECT MAX(id) FROM days)";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				Integer id = rs.getInt("id");
				int deaths = rs.getInt("deaths");
				float average = rs.getFloat("average");
				Date daytime= rs.getDate("daytime");
				return new Day(id, deaths,average,daytime);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Medication getMedication(String name) {
		try {
			String sql = "SELECT * FROM medication WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				Integer id_med = rs.getInt("id");
				String nombre = rs.getString("name");
				return new Medication(id_med, nombre);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Medication getMedicationId(Integer id) {
		try {
			String sql = "SELECT * FROM medication WHERE id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				Integer id_med = rs.getInt("id");
				String nombre = rs.getString("name");
				return new Medication(id_med, nombre);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Other_Pathologies getPathologyId(Integer id) {
		try {
			String sql = "SELECT * FROM other_pathologies WHERE id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				Integer idpatho = rs.getInt("id");
				String nombre = rs.getString("name");
				return new Other_Pathologies(idpatho, nombre);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Medication> getMedicationfromPatient(int id) {
		List<Medication> medicaciones = new ArrayList<Medication>();

		try {
			String sql = "SELECT m.id, m.name FROM medication AS m JOIN pat_medi AS pm ON pm.id_medi = m.id JOIN patients AS p ON p.id=pm.id_pat WHERE p.id = ? ";

			PreparedStatement prep = c.prepareStatement(sql);

			prep.setInt(1, id);

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				Integer nid = rs.getInt("id");
				String nombre = rs.getString("name");
				Medication medi = new Medication(nid, nombre);

				medicaciones.add(medi);

			}

			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicaciones;
	}
	
	@Override
	public List<String> getMedicationfromPatientNAME(int id) {
		List<String> medicaciones = new ArrayList<String>();

		try {
			String sql = "SELECT  m.name FROM medication AS m JOIN pat_medi AS pm ON pm.id_medi = m.id JOIN patients AS p ON p.id=pm.id_pat WHERE p.id = ? ";

			PreparedStatement prep = c.prepareStatement(sql);

			prep.setInt(1, id);

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				String nombre = rs.getString("name");

				medicaciones.add(nombre);

			}

			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicaciones;
	}

	@Override
	public List<Other_Pathologies> getPathofromPatient(int id) {
		List<Other_Pathologies> paths = new ArrayList<Other_Pathologies>();

		try {
			String sql = "SELECT path.id, path.name FROM other_pathologies AS path JOIN pat_patho AS pp ON pp.id_patho=path.id JOIN patients AS p ON p.id=pp.id_pat WHERE p.id = ?   ";


			PreparedStatement prep = c.prepareStatement(sql);

			prep.setInt(1, id);

			ResultSet rs = prep.executeQuery();
		
            int contador=0;
            while (rs.next()) {
				Integer nid = rs.getInt("id");
				String nombre = rs.getString("name");
				Other_Pathologies pathology = new Other_Pathologies(nid, nombre);
                contador++;
				paths.add(pathology);

			}

			rs.close();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return paths;
	}
	
	@Override
	public List<String> getPathofromPatientNAME(int id) {
		List<String> paths = new ArrayList<String>();

		try {
			String sql = "SELECT  path.name FROM other_pathologies AS path JOIN pat_patho AS pp ON pp.id_patho=path.id JOIN patients AS p ON p.id=pp.id_pat WHERE p.id = ?   ";


			PreparedStatement prep = c.prepareStatement(sql);

			prep.setInt(1, id);

			ResultSet rs = prep.executeQuery();
		
            while (rs.next()) {
				String name = rs.getString("name");
				paths.add(name);

			}

			rs.close();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return paths;
	}

	@Override
	public void deleteAssignmentMedication(Integer idPatient, int idmedication) {
		try {
			String sql = "DELETE FROM pat_medi WHERE (id_pat = ? AND id_medi = ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, idPatient);
			prep.setInt(2, idmedication);
			prep.executeUpdate();
			prep.close();

		} catch (Exception e) {
		}
	}

	@Override
	public void deleteAssignmentPathology(int idPatient, int idpatho) {
		try {
			String sql = "DELETE FROM pat_patho WHERE (id_pat = ? AND id_patho = ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, idPatient);
			prep.setInt(2, idpatho);
			prep.executeUpdate();
			prep.close();

		} catch (Exception e) {
		}
	}

	@Override
	public void changeDocPic(Doctor d, byte[] pic) {
		try {
			String sql;
			sql = "UPDATE doctors SET image = ? WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setBytes(1, pic);
			prep.setInt(2, d.getId() );
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void changeLabPic(Lab l, byte[] pic) {
		try {
			String sql;
			sql = "UPDATE lab SET image = ? WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setBytes(1, pic);
			prep.setInt(2, l.getId() );
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void getPicFromDoc(int id) {
		try {
			String sql;
			sql = "SELECT image FROM doctors WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			prep.executeQuery();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void getPicFromLab(int id) {
		try {
			String sql;
			sql = "SELECT image FROM lab WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			prep.executeQuery();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Day> getDay(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
