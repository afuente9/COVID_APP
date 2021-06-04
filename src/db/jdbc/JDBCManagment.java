package db.jdbc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.text.html.ImageView;

import db.GUI.ImageWindow;
import db.GUI.Main;
import db.interfaces.Cov_Manager;
import db.pojos.*;
import db.pojos.users.User;

public class JDBCManagment implements Cov_Manager {

	private Connection c;
	private static boolean showImage = true;

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
	
	public void connectWithNoPrint() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./database/covid.database");
			c.createStatement().execute("PRAGMA foreign_keys = ON");
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
			int a;
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE doctors " + "(id       			INTEGER  	 	PRIMARY KEY AUTOINCREMENT,"
					+ " name     			TEXT    	 	NOT NULL,"
					+ " speciality 			TEXT  		 	NOT NULL,"
					+ " birth_date			DATE		 	NOT NULL,"
					+ " collegiate_number	TEXT	 	 	NOT NULL," + " sex 				TEXT	 	 	NOT NULL,"
					+ " hospital  			TEXT		 	NOT NULL," + " image 				BLOB   			NULL,"
					+ " user_id				INTEGER			REFERENCES users(id))";
			stmt1.executeUpdate(sql1);
			stmt1.close();

			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE patients " + "(id       			INTEGER  	 PRIMARY KEY AUTOINCREMENT,"
					+ " name     			TEXT    	 NOT NULL," + " hos_location 		TEXT  		 NOT NULL,"
					+ " birthday			DATE  		 NOT NULL," + " social_security   	TEXT  	 	 NOT NULL,"
					+ " height 				float   	 NOT NULL," + " weight 				float   	 NOT NULL,"
					+ " sex 			    TEXT	   	 NOT NULL," + " infected 			boolean  	 NOT NULL,"
					+ " alive 				boolean  	 NOT NULL," + " hospital  			TEXT	 	 NOT NULL,"
					+ " score 				INTEGER		 NOT NULL,"
					+ " id_adm				INTEGER		 REFERENCES administration(id),"
					+ " vaccinated			boolean		 NOT NULL," + " bloodType			TEXT		 NOT NULL,"
					+ " dateIntroduced      DATE  		 NOT NULL)";
			stmt2.executeUpdate(sql2);
			stmt2.close();

			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE lab " + "(id       		INTEGER  	 	PRIMARY KEY AUTOINCREMENT,"
					+ " name     		TEXT    	 	NOT NULL," + " adress	 		TEXT	 	 	NOT NULL,"
					+ " cif			    TEXT  	 	 	NOT NULL," + " vacciness  		INTEGER	 	 	NOT NULL,"
					+ " image 			BLOB   			NULL,"
					+ " user_id			INTEGER			REFERENCES users(id))";
			stmt3.executeUpdate(sql3);
			stmt3.close();
//We also have to add the name of the doc because tableView only print pojos
			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE shipment " + "(id       		INTEGER  	PRIMARY KEY AUTOINCREMENT,"
					+ " vacciness  		INTEGER	 	NOT NULL," + " date			DATE		NOT NULL,"
					+ " id_lab			INTEGER		REFERENCES lab(id)," + " labName			TEXT		 NOT NULL,"
					+ " id_adm			INTEGER		REFERENCES administration(id))";
			stmt4.executeUpdate(sql4);
			stmt4.close();

			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE administration "
					+ "(id       				INTEGER  		PRIMARY KEY AUTOINCREMENT,"
					+ " total_vacciness  		INTEGER	 		NOT NULL,"
					+ " nameCountry					TEXT			NOT NULL,"
					+ " image 				    BLOB            NULL,"
					+ " user_id				INTEGER			REFERENCES users(id))";
			stmt5.executeUpdate(sql5);
			stmt5.close();

			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE other_pathologies " + "(id       		INTEGER  	 PRIMARY KEY AUTOINCREMENT,"
					+ " name  			TEXT	 	 NOT NULL)";
			stmt6.executeUpdate(sql6);
			stmt6.close();

			Statement stmt7 = c.createStatement();
			String sql7 = "CREATE TABLE medication " + "(id       		INTEGER  	PRIMARY KEY AUTOINCREMENT,"
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
			String sql10 = "CREATE TABLE pat_medi " + "(id_pat       	INTEGER  	REFERENCES patients(id),"
					+ " id_medi 		INTEGER	 	REFERENCES medication(id)," + " PRIMARY KEY (id_pat, id_medi))";
			stmt10.executeUpdate(sql10);
			stmt10.close();

			Statement stmt11 = c.createStatement();
			String sql11 = "CREATE TABLE pat_lab " + "(id_pat       	INTEGER  	REFERENCES patients(id),"
					+ " id_lab 			INTEGER	 	REFERENCES lab(id)," + " PRIMARY KEY (id_pat, id_lab))";
			stmt11.executeUpdate(sql11);
			stmt11.close();

			Statement stmt12 = c.createStatement();
			String sql12 = "CREATE TABLE days " + "(id       	   				INTEGER  	PRIMARY KEY AUTOINCREMENT,"
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

	// PARA AÑADIR LOS PACIENTES DEL CSV
	public void addPatientCSV(Patient p) {
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
			prep.setFloat(11, p.getScore());
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

			String sql = "INSERT INTO  lab (name, adress, cif, vacciness, user_id) VALUES (?, ?, ?, ?, ?)";
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
			String sql = "INSERT INTO Administration (total_vacciness, nameCountry) VALUES (?, ?)";
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
			String sql = "INSERT INTO Administration (total_vacciness, nameCountry, user_id) VALUES (?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, a.getVaccines());
			prep.setString(2, a.getName());
			prep.setInt(3, u.getId());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TODO UPDATE goverment vaccines used, patient (pic from whatsapp group), all
	// doctor including image, all doc
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
	public void ModifyVaccinesAdmin(int amount, int id) {
		try {
			String sql;
			sql = "UPDATE administration SET total_vacciness = (total_vacciness + ?) WHERE id= ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, amount);
			prep.setInt(2, id);
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
				List<Medication> medicationPatient = getMedicationfromPatient(id);
				List<Other_Pathologies> Other_Pathologies = getPathofromPatient(id);
				//List<Medication> medicationPatient = Main.getInter().getMedicationfromPatient(id);
				//List<Other_Pathologies> Other_Pathologies = Main.getInter().getPathofromPatient(id);

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
				Patient p = new Patient(id, hos_loc, patientName, bdate, socsec, high, weig, sexoo, infec, vivo, hos,
						vacc, sangre, dateIntroduced, medicationPatient, Other_Pathologies);
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
	public List<Patient> getPatientsOfLab(int lab_id) {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM pat_lab WHERE id_lab = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, lab_id);
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
	public Doctor getLastDoctor() {
		try {
			String sql = "SELECT * FROM doctors WHERE id = (SELECT MAX(id) FROM doctors)";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
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
	public Doctor getDoctorbyUser(User u) {
		try {
			String sql = "SELECT * FROM doctors WHERE user_id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, u.getId());
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
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
	public String getUserMailbydoctor(Doctor d) {
		try {
			String sql = "SELECT u.EMAIL FROM users AS u JOIN doctors AS d ON u.id= d.user_id WHERE d.id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, d.getId());
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				String mail = rs.getString("EMAIL");
				
				

				return mail;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}@Override
	public String getUserMailbylab(Lab l) {
		try {
			String sql = "SELECT u.EMAIL FROM users AS u JOIN lab AS l ON u.id= l.user_id WHERE l.id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, l.getId());
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				String mail = rs.getString("EMAIL");
				
				

				return mail;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}@Override
	public String getUserMailbyadmin(Administration admin) {
		try {
			String sql = "SELECT u.EMAIL FROM users AS u JOIN administration AS ad ON u.id= ad.user_id WHERE ad.id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, admin.getId());
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				String mail = rs.getString("EMAIL");
				
				

				return mail;
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
	}	@Override
	public void assignPattoDoc(int idPati, Doctor doc) {
		try {
			String sql = "INSERT INTO pat_doc (id_doc, id_pat) VALUES (?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, doc.getId());
			prep.setInt(2, idPati);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void assignPattoLab(int idPati, Lab l) {
		try {
			String sql = "INSERT INTO pat_lab (id_pat, id_lab) VALUES (?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, idPati);
			prep.setInt(2, l.getId());
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

	@Override
	public Administration getAdministration(int id0) {
		try {
			String sql = "SELECT * FROM administration WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id0);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int vacc = rs.getInt("total_vacciness");
				Integer id = rs.getInt("id");
				String name = rs.getString("nameCountry");
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
	public String getAdministrationOnlyName(int id0) {
		try {
			String sql = "SELECT nameCountry FROM administration WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id0);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {

				String name = rs.getString("nameCountry");
				return name;
			}
			prep.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Shipment> getAllShipment(int id) {
		List<Shipment> allShips = new ArrayList();
		try {
			String sql = "SELECT * FROM shipment WHERE id_lab= ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int vacc = rs.getInt("vacciness");
				Date d1 = rs.getDate("date");
				int idAD = rs.getInt("id_adm");
				String labName = rs.getString("labName");
				allShips.add(new Shipment(vacc, d1, labName, idAD));
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
	public List<String> getdifferentCountries(boolean alive) {
		List<String> countries = new ArrayList();
		countries.add("");
		try {
			String sql = "SELECT * FROM patients WHERE alive = ? ";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setBoolean(1, alive);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int idcountry = rs.getInt("id_adm");
				String nameCountry = Main.getInter().getAdministrationOnlyName(idcountry);
				if (!countries.contains(nameCountry)) {
					countries.add(nameCountry);
				}
			}

			prep.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return countries;
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
		int times = 0;
		try {
			String sql = "SELECT p.id FROM patients AS p JOIN pat_medi AS pm ON pm.id_pat=p.id JOIN medication AS m ON m.id=pm.id_medi WHERE (m.name = '"
					+ name + "' AND alive = ? )";
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
		int times = 0;
		try {
			String sql = "SELECT p.id FROM patients AS p JOIN pat_patho AS pp ON pp.id_pat=p.id JOIN other_pathologies AS path ON path.id=pp.id_patho WHERE (path.name = '"
					+ name + "' AND alive = ?)";
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
	public List<Shipment> getAllShipmentforAdminView(int id) {
		List<Shipment> allShips = new ArrayList();
		try {
			String sql = "SELECT s.vacciness, s.date, s.labName FROM shipment AS s WHERE id_adm=?  ";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int vacc = rs.getInt("vacciness");
				Date d1 = rs.getDate("date");
				String labName = rs.getString("labName");
				allShips.add(new Shipment(vacc, d1,labName));
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
	public int getNumberVaccinesAdmin(int id) {
		int num = 0;
		try {
			String sql = "SELECT total_vacciness FROM administration WHERE id=? ";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();

			num = rs.getInt("total_vacciness");
			prep.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
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
				String cif = rs.getString("cif");
				Integer vacc = rs.getInt("vacciness");
				byte[] pic = rs.getBytes("image");
				// TODO test, if doesn't work
				/*
				 * InputStream blobStream = rs.getBinaryStream("photo"); byte[] pic = new
				 * byte[blobStream.available()]; blobStream.read(pic);
				 * 
				 */
				return new Lab(id, vacc, direccion, lab_name, cif, pic);
			}
			prep.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	@Override
	public Lab getLastLab() {
		try {
			String sql = "SELECT * FROM lab WHERE id = (SELECT MAX(id) FROM lab)";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int id= rs.getInt("id");
				String lab_name = rs.getString("name");
				String direccion = rs.getString("adress");
				String cif = rs.getString("cif");
				Integer vacc = rs.getInt("vacciness");
				byte[] pic = rs.getBytes("image");
				// TODO test, if doesn't work
				/*
				 * InputStream blobStream = rs.getBinaryStream("photo"); byte[] pic = new
				 * byte[blobStream.available()]; blobStream.read(pic);
				 * 
				 */
				return new Lab(id, vacc, direccion, lab_name, cif, pic);
			}
			prep.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Lab getLabByUser(User u) {
		try {
			String sql = "SELECT * FROM lab WHERE user_id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, u.getId());
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String lab_name = rs.getString("name");
				String direccion = rs.getString("adress");
				String cif = rs.getString("cif");
				Integer vacc = rs.getInt("vacciness");
				byte[] pic = rs.getBytes("image");
				// TODO test, if doesn't work
				/*
				 * InputStream blobStream = rs.getBinaryStream("photo"); byte[] pic = new
				 * byte[blobStream.available()]; blobStream.read(pic);
				 * 
				 */
				return new Lab(id, vacc, direccion, lab_name, cif, pic);
			}
			prep.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		List<Integer> listDeaths = new ArrayList<>();
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
		List<Integer> infectedList = new ArrayList<>();
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

			for (int i = 7; i < savedDays.size(); i++) {

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
		int count = 0;
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
				prep.setBoolean(2, alive);

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
		int count = 0;
		try {
			String sql = "SELECT * FROM  patients WHERE( " + feature + " < " + max + " AND " + feature + " > " + min
					+ " AND alive = ? )";

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
		int count = 0;
		try {
			String sql = "SELECT * FROM  patients WHERE(" + feature + " = '" + type + "' AND alive = ?)";

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
	public int getNumberPatientsbyGOVID(int govId, boolean alive) {
		int count = 0;
		try {
			String sql = "SELECT * FROM  patients WHERE( id_adm= '" + govId + "' AND alive = ?)";

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
	public boolean checkAdminName(String name) {
		boolean is = false;
		try {
			String sql = "SELECT id FROM  administration WHERE nameCountry LIKE ?";

			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				is = true;

			}

			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
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

		return getFinalList(patients, availableVaccines);
	}

	private List<Patient> getFinalList(List<Patient> allpatients, int numberVaccines) {

		List<Patient> finalList = new ArrayList<>();
		int originalsize = allpatients.size();
		int mini = min(numberVaccines, allpatients.size());
		for (int i = 0; i < mini; i++) {

			finalList.add(allpatients.get(i));

		}
		return finalList;
	}

	private int min(int n1, int n2) {
		int min = 0;
		if (n1 <= n2) {
			min = n1;
		} else
			min = n2;

		return min;
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
		int id = 0;
		try {
			String sql = "SELECT id FROM administration WHERE nameCountry LIKE ?";
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
	public boolean adminRegisteredByName(String name) {
		boolean registered = false;
		try {
			String sql = "SELECT id FROM administration WHERE nameCountry LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, name);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				registered = true;

			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Country not registered. Please, contact to the Ministry of Health ");

		}
		return registered;
	}

	@Override
	public boolean MedicationRegisteredByName(String name) {
		boolean registered = false;
		try {
			String sql = "SELECT id FROM medication WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, name);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				registered = true;

			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registered;
	}

	@Override
	public boolean PathologyRegisteredByName(String name) {
		boolean registered = false;
		try {
			String sql = "SELECT id FROM Other_Pathologies WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, name);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				registered = true;

			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registered;
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
				Date daytime = rs.getDate("daytime");
				return new Day(id, deaths, average, daytime);
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
	public Other_Pathologies getPatByName(String name) {
		try {
			String sql = "SELECT * FROM other_pathologies WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, name);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				Integer id_med = rs.getInt("id");
				String nombre = rs.getString("name");
				return new Other_Pathologies(id_med, nombre);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getIdMedicationbyName(String name) {
		Integer id_med = 0;

		try {
			String sql = "SELECT * FROM medication WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				id_med = rs.getInt("id");

			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id_med;
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
	public List<Medication> getMedicationfromPatientwithoutID(int id) {
		List<Medication> medicaciones = new ArrayList<Medication>();

		try {
			String sql = "SELECT m.name FROM medication AS m JOIN pat_medi AS pm ON pm.id_medi = m.id JOIN patients AS p ON p.id=pm.id_pat WHERE p.id = ? ";

			PreparedStatement prep = c.prepareStatement(sql);

			prep.setInt(1, id);

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				String nombre = rs.getString("name");
				Medication medi = new Medication(nombre);

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
	public List<Other_Pathologies> getPatfromPatientwithoutID(int id) {
		List<Other_Pathologies> OtherPats = new ArrayList<Other_Pathologies>();

		try {
			String sql = "SELECT  path.name FROM other_pathologies AS path JOIN pat_patho AS pp ON pp.id_patho=path.id JOIN patients AS p ON p.id=pp.id_pat WHERE p.id = ?   ";

			PreparedStatement prep = c.prepareStatement(sql);

			prep.setInt(1, id);

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				String nombre = rs.getString("name");
				Other_Pathologies pat = new Other_Pathologies(nombre);

				OtherPats.add(pat);

			}

			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return OtherPats;
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

			int contador = 0;
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
			prep.setInt(2, d.getId());
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
			prep.setInt(2, l.getId());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public byte[] getPicFromDoc(int id) {
		try {
			String sql;
			sql = "SELECT image FROM doctors WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				
				byte[] photo = rs.getBytes("image");
			
		        return photo;

				
			}
			
			rs.close();
			prep.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;


	}
	
	@Override
	public void openpicturedoctor(Doctor d) {
		try {
		String sql = "SELECT * FROM doctors WHERE id LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, d.getId());
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			
			byte[] photo = rs.getBytes("image");
			// Note that department is going to show null, even if the
			// employee is assigned to one, that's because we didn't
			// retrieve the department from the database. We should!!
			
			// Process the photo
			if (photo!=null) {
				ByteArrayInputStream blobIn = new ByteArrayInputStream(photo);
				// Show the photo
				if (showImage) {
				         	ImageWindow window = new ImageWindow();
					window.showBlob(blobIn);
				}
				// Write the photo in a file
				else {
						File outFile = new File("./photos/Output.png");
					OutputStream blobOut = new FileOutputStream(outFile);
					byte[] buffer = new byte[blobIn.available()];
					blobIn.read(buffer);
					blobOut.write(buffer);
					blobOut.close();
					
				
				
					
				}
				
			}
		}
		
			rs.close();
			prep.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void openpicturelab(Lab d) {
		try {
		String sql = "SELECT * FROM lab WHERE id LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, d.getId());
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			
			byte[] photo = rs.getBytes("image");
			// Note that department is going to show null, even if the
			// employee is assigned to one, that's because we didn't
			// retrieve the department from the database. We should!!
			
			// Process the photo
			if (photo!=null) {
				ByteArrayInputStream blobIn = new ByteArrayInputStream(photo);
				// Show the photo
				if (showImage) {
				         	ImageWindow window = new ImageWindow();
					window.showBlob(blobIn);
				}
				// Write the photo in a file
				else {
						File outFile = new File("./photos/Output.png");
					OutputStream blobOut = new FileOutputStream(outFile);
					byte[] buffer = new byte[blobIn.available()];
					blobIn.read(buffer);
					blobOut.write(buffer);
					blobOut.close();
					
				
				
					
				}
				
			}
		}
		
			rs.close();
			prep.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*@Override
	public byte[] getPicFromLab(int id) {
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

	}*/

	@Override
	public List<Day> getDay(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Administration getAdministrationbyUser(User u) {
		try {
			String sql = "SELECT * FROM administration WHERE user_id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, u.getId());
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int vacc = rs.getInt("total_vacciness");
				Integer id = rs.getInt("id");
				String name = rs.getString("nameCountry");
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
	public boolean checkAdmin(Administration admin) {
		List<Administration> admins = getAllAdmins();
		Administration tmp = null;
		try {
			for (int i = 0; i <= admins.size(); i++) {
				tmp = admins.get(i);
				if (tmp.getName().equalsIgnoreCase(tmp.getName()) && tmp.getVaccines() == admin.getVaccines()) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkLab(Lab labo) {
		List<Lab> labs = showLabs();
		Lab tmp = null;
		try {
			for (int i = 0; i < labs.size(); i++) {
				tmp = labs.get(i);
				if (tmp.getAddress().equalsIgnoreCase(labo.getAddress()) && tmp.getCif().equalsIgnoreCase(labo.getCif())
						&& tmp.getName().equalsIgnoreCase(labo.getName())
						&& tmp.getVaccines_produce() == labo.getVaccines_produce()
						&& tmp.getPatients().equals(labo.getPatients())) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Lab> showLabs() {
		List<Lab> labs = new ArrayList<Lab>();
		try {
			String sql = "SELECT * FROM lab WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, '%'+""+'%');
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String Lname = rs.getString("name");
				String Ladress = rs.getString("adress");
				String Lcif = rs.getString("cif");
				int Lvacciness = rs.getInt("vacciness");
				Lab lab = new Lab(id, Lvacciness, Ladress, Lname, Lcif);
				labs.add(lab);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return labs;
	}
	
	@Override
	public List<Administration> getAllAdmins() {
		List<Administration> admins = new ArrayList<Administration>();
		try {
			String sql = "SELECT * FROM administration WHERE nameCountry LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, '%'+""+'%');
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("nameCountry");
				int vacciness = rs.getInt("total_vacciness");
				byte[] pic = rs.getBytes("image");
				Administration gov = new Administration(id, vacciness, name, pic);
				admins.add(gov);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admins;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		List<Doctor> docs = new ArrayList<Doctor>();
		try {
		
			String sql = "SELECT * FROM doctors WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, '%'+""+'%');
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");

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
				Doctor doc = new Doctor(id, espe, doctorName, nacido, numCol, sexoo, hos, foto);
				docs.add(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docs;
	}

	@Override
	public List<Medication> getAllMedication() {
		List<Medication> meds = new ArrayList<Medication>();
		try {
			String sql = "SELECT * FROM doctors WHERE name = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, '%'+""+'%');
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Medication med = new Medication(id, name);
				meds.add(med);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return meds;
	}

	@Override
	public List<Patient> getAllPatient() {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM  patients WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, '%'+""+'%');
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
	public List<Other_Pathologies> getAllPatho() {
		List<Other_Pathologies> patos = new ArrayList<Other_Pathologies>();
		try {
			String sql = "SELECT * FROM doctors WHERE name = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, '%'+""+'%');
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Other_Pathologies pato = new Other_Pathologies(id, name);
				patos.add(pato);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return patos;
	}

	@Override
	public boolean checkMeds(Medication med) {
		List<Medication> meds = getAllMedication();
		Medication tmp = null;
		try {
			for (int i = 0; i <= meds.size(); i++) {
				tmp = meds.get(i);
				if (tmp.getName().equalsIgnoreCase(med.getName())) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkOther_Pat(Other_Pathologies other) {
		List<Other_Pathologies> others = getAllPatho();
		Other_Pathologies tmp = null;
		try {
			for (int i = 0; i <= others.size(); i++) {
				tmp = others.get(i);
				if (tmp.getName().equalsIgnoreCase(other.getName())) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean checkPatient(Patient pat) {
		List<Patient> pats = getAllPatient();
		Patient tmp = null;
		try {
			for (int i = 0; i <= pats.size(); i++) {
				tmp = pats.get(i);
				if (tmp.getBirthday().equals(pat.getBirthday())
						&& tmp.getSocial_security().equalsIgnoreCase(pat.getSocial_security())
						&& tmp.getName().equalsIgnoreCase(pat.getName()) && tmp.getVaccinated() == pat.getVaccinated()
						&& tmp.getBloodType().equalsIgnoreCase(pat.getBloodType())
						&& tmp.getDoctors().equals(pat.getDoctors())
						&& tmp.getDateIntroduced().equals(pat.getDateIntroduced()) && tmp.getHeight() == pat.getHeight()
						&& tmp.getWeight() == pat.getWeight()
						&& tmp.getHos_location().equalsIgnoreCase(pat.getHos_location())
						&& tmp.getHospital().equalsIgnoreCase(pat.getHospital())
						&& tmp.getMedication().equals(pat.getMedication())
						&& tmp.getOther_pathologies().equals(pat.getOther_pathologies())
						&& tmp.getScore() == pat.getScore() && tmp.getSex().equals(pat.getSex())) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkDoctor(Doctor doc) {
		List<Doctor> docs = getAllDoctors();
		Doctor tmp = null;
		try {
			for (int i = 0; i <= docs.size(); i++) {
				tmp = docs.get(i);
				if (tmp.getName().equalsIgnoreCase(doc.getName()) && tmp.getBirthday().equals(doc.getBirthday())
						&& tmp.getCollegiate_number().equalsIgnoreCase(doc.getCollegiate_number())
						&& tmp.getHospital().equalsIgnoreCase(doc.getHospital()) && tmp.getSex().equals(doc.getSex())
						&& tmp.getSpeciality().equalsIgnoreCase(doc.getSpeciality())) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User getUserbydoctor(Doctor d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getPicFromLab(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Patient> getPatientsfromLab(int Lid) {
		List<Patient> pats = new ArrayList<Patient>();

		try {
			String sql = "SELECT p.birthday, p.sex, p.height, p.weight, p.id_adm FROM patients AS p "
					+ "JOIN pat_lab AS pl ON p.id = pl.id_pat JOIN lab AS l ON pl.id_lab = l.id WHERE l.id = ? ";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, Lid);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {

				Date birthday = rs.getDate("birthday");
				Sex sexo;
				if (rs.getString("sex").equalsIgnoreCase("m")) {
					sexo = Sex.Male;
				} else {
					sexo = Sex.Female;
				}
				Float height = rs.getFloat("height");
				Float weight = rs.getFloat("weight");
				int govId = rs.getInt("id_adm");
				Patient p = new Patient(birthday, sexo, height, weight, govId);

				pats.add(p);

			}

			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pats;
	}
	
	@Override
	public List<Lab> getAllLabsXML() {
		List<Lab> labs = new ArrayList<Lab>();
		try {
			String sql = "SELECT id, name, adress, cif, vacciness FROM lab WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, '%'+""+'%');
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int Lid = rs.getInt("id");
				String Lname = rs.getString("name");
				String Ladress = rs.getString("adress");
				String Lcif = rs.getString("cif");
				int Lvacciness = rs.getInt("vacciness");
				List<Patient> pats = getPatientsfromLab(Lid);
				Lab lab = new Lab(Lvacciness, Ladress, Lname, Lcif, pats);
				labs.add(lab);
			}
	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return labs;
	}

	@Override
	public List<Patient> getPatientsfromDoc(int Did) {
		List<Patient> pats = new ArrayList<Patient>();

		try {
			String sql = "SELECT p.birthday, p.sex, p.height, p.weight, p.id_adm FROM patients AS p "
					+ "JOIN pat_doc AS pd ON p.id = pd.id_pat JOIN doctors AS d ON pd.id_doc = d.id WHERE d.id = ? ";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, Did);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {

				Date birthday = rs.getDate("birthday");
				Sex sexo;
				if (rs.getString("sex").equalsIgnoreCase("m")) {
					sexo = Sex.Male;
				} else {
					sexo = Sex.Female;
				}
				Float height = rs.getFloat("height");
				Float weight = rs.getFloat("weight");
				int govId = rs.getInt("id_adm");
				Patient p = new Patient(birthday, sexo, height, weight, govId);

				pats.add(p);

			}

			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pats;
	}

	@Override
	public List<Doctor> getAllDoctorsXML() {
		List<Doctor> docs = new ArrayList<Doctor>();
		try {
			String sql = "SELECT id, name, speciality, birth_date, collegiate_number, sex, hospital FROM doctors WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, '%'+""+'%');
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int Did = rs.getInt("id");
				String Dname = rs.getString("name");
				String Dspeciality = rs.getString("speciality");
				Date Dbirth = rs.getDate("birth_date");
				String Dcollegiate = rs.getString("collegiate_number");
				Sex Dsexo;
				if (rs.getString("sex").equalsIgnoreCase("m")) {
					Dsexo = Sex.Male;
				} else {
					Dsexo = Sex.Female;
				}
				String Dhospital = rs.getString("hospital");
				List<Patient> pats = getPatientsfromDoc(Did);
				Doctor doc = new Doctor(Dname, Dspeciality, Dbirth, Dcollegiate, Dsexo, Dhospital, pats);
				docs.add(doc);
			}
	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return docs;
	}

	@Override
	public List<Administration> getAllGovermentsXML() {
		List<Administration> govs = new ArrayList<Administration>();
		try {
			String sql = "SELECT id, nameCountry, total_vacciness FROM administration WHERE nameCountry LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, '%'+""+'%');
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int Gid = rs.getInt("id");
				String Gname = rs.getString("nameCountry");
				int Gvacciness = rs.getInt("total_vacciness");
				Administration gov = new Administration(Gname, Gvacciness);
				govs.add(gov);
			}
	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return govs;
	}

	

}
