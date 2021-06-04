package db.ui;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import db.pojos.*;
import db.pojos.users.Role;
import db.pojos.users.User;
import db.interfaces.Cov_Manager;
import db.interfaces.UserManager;
import db.jdbc.JDBCManagment;
import db.jpa.JPAUserManagment;
import db.xml.dtd.*;

public class Menu {
	//public Day today=ultimo dia a�adido a la lista de dias;

	private static Connection c;
	private static XmlToJava xj = new XmlToJava();
	private static JavaToXml jx = new JavaToXml();
	private static Cov_Manager inter = new JDBCManagment();
	private static UserManager userman = new JPAUserManagment();
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./database/covid.database");
			c.createStatement().execute("PRAGMA foreign_keys = ON");
			System.out.println("Database connection opened");
			inter.creatTables();
		} catch (SQLException E) {
			System.out.println("There was a database exception.");
			E.printStackTrace();
		} catch (Exception e) {
			System.out.println("There was a general exception.");
			e.printStackTrace();
		}

	}
	
	public static void parse() {
		BufferedReader lector;
		connect();
		try {
			lector = new BufferedReader(new FileReader("/Users/alvaro/Desktop/Covid_App/COVID_APP/export.csv"));
			
			int personas = 100;
			
			for(int i = 0; i <= personas; i++) {
				if(i == 0) {
					String linea = lector.readLine();

				}else {
					String linea = lector.readLine();
					patientParse(linea);
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void patientParse(String linea) {
		String[] datos = linea.split(",");
		System.out.println(datos.toString());
		
		int id = Integer.parseInt(datos[0]);
		String name = datos[1]; //Bien
		String hos_location = datos[2];//Bien 
		
		String social_security = datos[4];
		Float height = Float.parseFloat(datos[5]);
		Float weight = Float.parseFloat(datos[6]);
		Sex sex = Sex.parse(datos[7]);
		String ing = datos[8];
		Boolean infected;
		if(ing.equalsIgnoreCase("1")) {
			infected = Boolean.parseBoolean("TRUE");
		}
		else {
			infected = Boolean.parseBoolean("FALSE");
		}
		String al = datos[9];
		Boolean alive;
		if(al.equalsIgnoreCase("1")) {
			alive = Boolean.parseBoolean("TRUE");
		}
		else {
			alive = Boolean.parseBoolean("FALSE");
		}
		String hos = datos[10];
		Float score = Float.parseFloat(datos[11]);
		int id_adm = Integer.parseInt(datos[12]);
		String vac = datos[13];
		Boolean vaccinated;
		if(vac.equalsIgnoreCase("1")) {
			vaccinated = Boolean.parseBoolean("TRUE");
		}
		else {
			vaccinated = Boolean.parseBoolean("FALSE");
		}
		String blood = datos[14];
		long dateIntro = Long.parseLong(datos[15]);
		String b = datos[3];
		long birthday = Long.parseLong(b);  

		try {
			String sexo;
			if (sex.equals(Sex.Male)) {
				sexo = "M";
			} else {
				sexo = "F";
			}
			String sql = "INSERT INTO patients (name, birthday, social_security, height, weight, sex, infected, alive, hospital, hos_location, score,id_adm, bloodType, vaccinated, dateIntroduced)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, name);
			prep.setLong(2, birthday);
			prep.setString(3, social_security);
			prep.setFloat(4, height);
			prep.setFloat(5, weight);
			prep.setString(6, sexo);
			prep.setBoolean(7, infected);
			prep.setBoolean(8, alive);
			prep.setString(9, hos);
			prep.setString(10, hos_location);
			prep.setFloat(11, score); 
			prep.setInt(12, id_adm);
			prep.setString(13, blood);
			prep.setBoolean(14, vaccinated);
			prep.setLong(15, dateIntro);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*private static void addPatientsToLabs() {
		for(int i = 1; i <= inter.showLabs().size(); i++) {
			System.out.println(i);
			for(int j = 1; j <= inter.getAllPatient().size(); j++) {
				Lab l = inter.getLab(i);
				inter.assignPattoLab(j, l);
			}
		}
	}
	
	private static void addPatientsToDocs() {
		for(int i = 1; i <= inter.getAllDoctors().size(); i++) {
			System.out.println(i);
			for(int j = 1; j <= inter.getAllPatient().size(); j++) {
				Doctor d = inter.getDoctor(i);
				System.out.println(i + ", " + j);
				inter.assignPattoDoc(j, d);
			}
		}
	}*/
	
	public static void main(String[] args) throws Exception {
		inter.connect();
		userman.connect();
		/*if (today.getDate() != LocalDate.now()) {
			newDay();
		
		}
		*/
		do {
			System.out.println("|	   Choose an option:		|");
			System.out.println("|	1.  Register			|");
			System.out.println("|	2.  Login			|");
			System.out.println("|	3.  Add patients CSV		|");
			System.out.println("|	4.  Generate XML                |");
			System.out.println("|	5.  Add from XML                |");
			System.out.println("|	6.  Generate HTML               |");
			//System.out.println("|	7.  Add Relation Lab            |");
			//System.out.println("|	8.  Add Relation Doc            |");
			System.out.println("|	0.  Exit			|");
			
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				register();
				break;
			case 2:
				login();
				break;
			case 3:
				parse();
				break;
			case 4:
				xmlgen();
				break;
			case 5:
				addFromXml();
				break;
			case 6: 
				htmlgen();
				break;
			case 0:
				System.out.println("Have a nice day :)");
				inter.disconnect();
				userman.disconnect();
				System.exit(0);
			default:
				break;
			}
		}
		while(true);
	}
	

	private static void htmlgen() throws Exception{
		System.out.println("1. Generate HTML for Lab: ");
		System.out.println("2. Generate HTML for Govern: ");
		System.out.println("3. Generate HTML for Doc: ");
		int op = Integer.parseInt(reader.readLine());
		if (op==1) {
			System.out.println("Indicate the directory: ");
			XmlToHtml.createHTML(reader.readLine(), "W:\\DOCS\\java-2020-12\\COVID_APP\\src\\db\\xmls\\LabHTML.xslt", "./xmlFileSave/labResult.html");
			System.out.println("File labResult.html created in the proyect's folder xmlFileSave ");
		} else if (op==2) {
			System.out.println("Indicate the directory: ");
			XmlToHtml.createHTML(reader.readLine(), "W:\\DOCS\\java-2020-12\\COVID_APP\\src\\db\\xmls\\AdminHTML.xslt", "./xmlFileSave/adminResult.html");
			System.out.println("File adminResult.html created in the proyect's folder xmlFileSave ");
		} else if (op == 3) {
			System.out.println("Indicate the directory: ");
			XmlToHtml.createHTML(reader.readLine(), "W:\\DOCS\\java-2020-12\\COVID_APP\\src\\db\\xmls\\DoctorHTML.xslt", "./xmlFileSave/docResult.html");
			System.out.println("File docResult.html created in the proyect's folder xmlFileSave ");
		}
		
	}

	private static void addFromXml() throws Exception{
		System.out.println("1. Insert Lab: ");
		System.out.println("2. Insert Govern: ");
		System.out.println("3. Insert Doc: ");
		int op = Integer.parseInt(reader.readLine());
		if (op==1) {
			System.out.println("Indicate the directory: ");
			xj.getLabFromXml(reader.readLine());
		} else if (op==2) {
			System.out.println("Indicate the directory: ");
			xj.getAdminFromXml(reader.readLine());
		} else if (op == 3) {
			System.out.println("Indicate the directory: ");
			xj.getDocFromXml(reader.readLine());
		}
	}

	private static void xmlgen() throws Exception{
		
		jx.getXMLforLab();
		jx.getXMLforAdministration();
		jx.getXMLforDoc();
		
	}
	
	private static void register() throws Exception{ 
		//TODO como queremos que eligan el role que tienen, ¿es lo primero que eligen o lo ultimo?
		System.out.println(userman.getRoles());
		System.out.println("Introduce the chosen role ID:");
		int id = Integer.parseInt(reader.readLine());
		Role role = userman.getRole(id);
		if(role.getName().equalsIgnoreCase("doctor")) {
			System.out.println("Please, introduce your email address:");
			String email = reader.readLine();
			if (userman.checkEmail(email)) {
				System.out.println("Email already used, try to log in");
			}
			else {
				System.out.println("Now, please, introduce your password:");
				String password = reader.readLine();
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] hash = md.digest();
				User u = new User(email, hash, role);
				userman.newUser(u);
				
				addDoctor(u);
			}
		}else if(role.getName().equalsIgnoreCase("laboratory")) {
			System.out.println("Please, introduce your email address:");
			String email = reader.readLine();
			if (userman.checkEmail(email)) {
				System.out.println("Email already used, try to log in");
			}
			else {
				System.out.println("Now, please, introduce your password:");
				String password = reader.readLine();
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] hash = md.digest();
				User u = new User(email, hash, role);
				userman.newUser(u);
				addLab(u);
			}
		}else {
			System.out.println("Please, introduce your email address:");
			String email = reader.readLine();
			if (userman.checkEmail(email)) {
				System.out.println("Email already used, try to log in");
			} else {
				System.out.println("Now, please, introduce your password:");
				String password = reader.readLine();
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] hash = md.digest();
				User u = new User(email, hash, role);
				userman.newUser(u);
				addAdmin(u);
			}
		}
		
	}
	
	private static void login() throws Exception{
		System.out.println("Please, introduce your email address:");
		String email = reader.readLine();
		System.out.println("Now, please, introduce your password:");
		String password = reader.readLine();
		User u = userman.checkPassword(email, password);
		if(u == null) {
			System.out.println("Wrong email or password");
			return;
		}else if(u.getRole().getName().equalsIgnoreCase("administration")) {
			administrationMenu();
		}else if(u.getRole().getName().equalsIgnoreCase("doctor")) {
			doctorMenu();
		}else if(u.getRole().getName().equalsIgnoreCase("laboratory")) {
			labMenu();
		}
	}
	
	private static void deleteAccount()throws Exception{
		System.out.println("Please, introduce again your email address:");
		String email = reader.readLine();
		System.out.println("Now, please, introduce again your password:");
		String password = reader.readLine();
		System.out.println("Are you sure you want to delete your account? (YES / NO)");
		String sure = reader.readLine();
		if(sure.equalsIgnoreCase("yes")) {
			userman.deleteUser(email, password);
		}
		
	}
	
	private static void changeMail() throws Exception{
		System.out.println("Please, introduce again your email address:");
		String oldEmail = reader.readLine();
		System.out.println("Now, please, introduce your new email address:");
		String newEmail = reader.readLine();
		System.out.println("Now, please, introduce again your password:");
		String password = reader.readLine();
		System.out.println("Are you sure you want to change your email address? (YES / NO)");
		String sure = reader.readLine();
		if(sure.equalsIgnoreCase("yes")) {
			userman.updateUserMail(newEmail, oldEmail, password);
		}
	}
	
	private static void changePassword() throws Exception{
 		System.out.println("Please, introduce again your email address:");
 		String email = reader.readLine();
 		System.out.println("Now, please, introduce again your password:");
 		String oldPassword = reader.readLine();
 		System.out.println("Now, please, introduce your new password:");
 		String newPassword = reader.readLine();
 		System.out.println("Are you sure you want to change your password? (YES / NO)");
 		String sure = reader.readLine();
 		if(sure.equalsIgnoreCase("yes")) {
 			userman.updateUserPassword(email, newPassword, oldPassword);
 		}
 	}
	
	private static void administrationMenu() throws Exception{
		do {
			System.out.println("|	   Choose an option:		|");
			System.out.println("|	1.  Total nº of vaccines	|");
			System.out.println("|	2.  Simulation			|");
			System.out.println("|	3.  Vaccines used		|");
			System.out.println("|	4.  Delete account		|");
			System.out.println("|	5.  Change email		|");
			System.out.println("|	6.  Change password		|");
			System.out.println("|	0.  Go back			|");
			
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				//totalNVaccines();
				break;
			case 2:
				//simulation();
				break;
			case 3:
				//vaccinesUsed();
				break;
			case 4:
				deleteAccount();
				break;
			case 5:
				changeMail();
				break;
			case 6:
				changePassword();
				break;
			case 0:
				return;
			default:
				break;
			}
		}
		while(true);
	}
	
	private static void doctorMenu() throws Exception{
		do {
			System.out.println("|	   Choose an option:		|");
			System.out.println("|	1.  Add a patient		|");
			System.out.println("|	2.  Search patients		|");
			System.out.println("|	3.  Modify doctor 		|");
			System.out.println("|	4.  Delete account		|");
			System.out.println("|	5.  Change email		|");
			System.out.println("|	0.  Go back			|");
			
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				addPatient();
				break;
			case 2:
				searchPatients();
				break;
			case 3:
				modifyDoctor();
				break;
			case 4:
				deleteAccount();
				break;
			case 5:
				changeMail();
				break;
			case 0:
				return;
			default:
				break;
			}
		}
		while(true);
	}
	
	private static void labMenu() throws Exception{
		do {
			System.out.println("|	   Choose an option:			|");
			System.out.println("|	1.  View Stadistics			|");
			System.out.println("|	2.  Search patients			|");
			System.out.println("|	3.  Send new Shipment			|");
			System.out.println("|	4.  Add new batch of vaccines		|");
			System.out.println("|	5.  Modify lab  			|");
			System.out.println("|	6.  Delete account			|");
			System.out.println("|	7.  Change email			|");
			System.out.println("|	0.  Go back				|");
			
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				//viewStadistics();
				break;
			case 2:
				searchPatientGeneric();
				break;
			case 3:
				//newShipment();
				break;
			case 4:
				//newBatch();
				break;
			case 5:
				modifyLab();
				break;
			case 6:
				deleteAccount();
				break;
			case 7:
				changeMail();
				break;
			case 0:
				return;
			default:
				break;
			}
		}
		while(true);
	}
	
	private static void searchMed() {
		try {
		System.out.println("Tell me the name of the medication: ");
		String nombre = reader.readLine();
		List<Medication> result = inter.searchMedicationByName(nombre);
		System.out.println("Those are the medications: \n" + result.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void getDoc() {
		try {
			System.out.print("Doctor id: ");
			int iden = Integer.parseInt(reader.readLine());
			System.out.print(inter.getDoctor(iden).toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static void getLab() {
		try {
			System.out.print("Laboratory id: ");
			int iden = Integer.parseInt(reader.readLine());
			System.out.print(inter.getLab(iden).toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static void addMedication() throws Exception{ 
		List<Medication> med = new ArrayList<Medication>();
		String n_med = "";
		while(true) {
			System.out.println("Please intoduce the name, 0 for exit");
			n_med = reader.readLine();
			if (n_med.equalsIgnoreCase("0")) {
				break;
			}
			else {
			Medication m = new Medication(n_med);		
			inter.addMedication(m);
			med.add(inter.getMedication(n_med));		
			}
		}		
		System.out.println(med);
	}

	private static void addPatient() throws Exception{
		System.out.println("Please, input the person info:");
		System.out.print("Name: ");
		String p_name = reader.readLine();
		System.out.print("Birthday (yyyy-mm-dd): ");
		LocalDate p_bday = LocalDate.parse(reader.readLine(), formatter);
		System.out.print("Social Security Number [(nn)nnnnnnnnnnn]: ");
		String p_ss = reader.readLine();
		System.out.print("Height (m.cm): ");
		Float p_height = Float.parseFloat(reader.readLine());
		System.out.print("Weight: ");
		Float p_weight = Float.parseFloat(reader.readLine());
		System.out.print("Sex (male, female): ");
		Sex p_sex = Sex.parse(reader.readLine());
		System.out.print("Infected (True or False): ");
		Boolean p_infected = Boolean.parseBoolean(reader.readLine());
		System.out.print("Alive (True or False): ");
		Boolean p_alive = Boolean.parseBoolean(reader.readLine());
		System.out.print("Hospital: ");
		String p_hos = reader.readLine();
		System.out.print("Hospital location: ");
		String p_hosL = reader.readLine();
		System.out.print("Blood type: ");
		String p_blood = reader.readLine();
		System.out.print("Is vaccinated (True or False): ");
		Boolean p_vaccinated = Boolean.parseBoolean(reader.readLine());
        Date dateIntroduced= Date.valueOf(LocalDate.now());

		//inter.addPatient(new Patient (p_hosL, p_name, Date.valueOf(p_bday), p_ss, p_height, p_weight, p_sex, p_infected, p_alive, p_hos, p_vaccinated, p_blood,dateIntroduced));
		System.out.print("Do you want to add any medication? (True or False): ");
		Boolean p_med = Boolean.parseBoolean(reader.readLine());
		if(p_med.TRUE) {
			System.out.println("Select patient: ");
			System.out.println(inter.searchPatientByName(p_name).toString());
			Integer id_pac = Integer.parseInt(reader.readLine());
			System.out.println("Now the medications: ");
			System.out.println(inter.searchMedicationByName("").toString());
			System.out.print("Does the medication exist? (True or False): ");
			String check = reader.readLine();
			if(check.equalsIgnoreCase("true")) {
				System.out.println("Select the medication (write the name): ");
				String med_name = reader.readLine();
			//	inter.assignMed(inter.getPatient(id_pac), inter.getMedication(med_name));
			}
			else {
				System.out.println("Write the name of the medication: ");
				String n_med = reader.readLine();
				Medication m = new Medication(n_med);		
				inter.addMedication(m);
				//inter.assignMed(inter.getPatient(id_pac), inter.getMedication(n_med));
			}
		}
	}

	private static void searchPatients() throws Exception {
		System.out.println("Please, input the person info:");
		System.out.print("Name: ");
		String p_name = reader.readLine();
		List<Patient> result = inter.searchPatientByName(p_name);
		System.out.println("Those are the patients: \n" + result.toString());
	}
	
	private static void searchPatientGeneric() throws Exception {
		System.out.println("Please, input the person info:");
		System.out.print("feature: ");
		String feature = reader.readLine();
		System.out.print("type: ");
		String type = reader.readLine();
		List<Patient> result = inter.searchPatientGeneric(feature,type);
		System.out.println("Those are the patients: \n" + result.toString());
	}
	
	private static void addDoctor(User u) throws Exception{
		System.out.println("Please, input the DOCTOR info:");
		System.out.print("Name: ");
		String d_name = reader.readLine();
		System.out.print("Speciality: ");
		String d_speciality = reader.readLine();
		System.out.print("Birthday (yyyy-mm-dd): ");
		LocalDate d_bday = LocalDate.parse(reader.readLine(), formatter);
		System.out.print("Collegiate number: ");
		String d_cn = reader.readLine();
		System.out.print("Hospital: ");
		String d_hosp = reader.readLine();
		System.out.print("Sex (male, female): ");
		Sex d_sex = Sex.parse(reader.readLine());
		inter.addDoctorUser(new Doctor(d_name, d_speciality, Date.valueOf(d_bday), d_cn, d_hosp, d_sex), u);
	}
	
	private static void addLab(User u) throws Exception{
		System.out.println("Please, input the LAB info:");
		System.out.print("Name: ");
		String l_name = reader.readLine();
		System.out.print("Adress: ");
		String l_adress = reader.readLine();
		System.out.print("CIF: ");
		String l_cif = reader.readLine();
		System.out.print("Vaccines: ");
		int l_vac = Integer.parseInt(reader.readLine());
		inter.addLabUser(new Lab(l_name, l_adress, l_cif, l_vac), u);
	}
	
	private static void addAdmin(User u) throws Exception{
		System.out.println("Please, input the Goverment info:");
		System.out.print("Name: ");
		String a_name = reader.readLine();
		System.out.print("Vaccines: ");
		int a_vac = Integer.parseInt(reader.readLine());
		inter.addGovermentUser(new Administration(a_vac, a_name), u);
	}
	
	private static void modifyDoctor() {
		try {
			System.out.print("Doctor id: ");
			int iden = Integer.parseInt(reader.readLine());
			System.out.println("Which atribute want you to change? (name, collegiate_num1"
					+ "ber, sex, birth_date, speciality or hospital)");
			String atrib = reader.readLine();
			System.out.println("Introduce the new value: ");
			if (atrib.equalsIgnoreCase("sex")) {
				System.out.println("Male (M) or female (F): ");
				System.out.println("WARNING: if the value introduces is not the ones indicated, female will be setted");
			}
			else if(atrib.equalsIgnoreCase("birth_date")) {
				System.out.print("Birthday (yyyy-mm-dd): ");
			}
			String valor = reader.readLine();
			inter.modifyDoctor(iden, atrib, valor);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void modifyLab() {
			try {
				System.out.print("Laboratory id: ");
				int iden = Integer.parseInt(reader.readLine());
				System.out.println("Which atribute want you to change? (name, cif or adress)");//vacciness No se si necesitamos algo que modifique el n� de vacunas en este 
				String atrib = reader.readLine();											  //methode o si hay que crear uno nuevo exclusivo para ello
				System.out.println("Introduce the new value: ");
				String valor = reader.readLine();
				inter.modifyLab(iden, atrib, valor);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
		
	}
	
	public void newDay() { 
		
		//TODO  esto pasara if today != LocalDate.now() 
		
		//calcular la media de muertos de los ultimos 7 dias
		
				//float average=0;
				
				// calcular el average que tenemos este dia
				
				
				/*
				 * float suma=0;
				 * for (int i=listadedias.size; i>listadedias.size-7;i--){
				 * suma+=listadedias.getdia.getnumerodemuertos();
				 * }
				 * average= suma/7;
				 * 
				 */
				
						//crear un nuevo dia
					//	Day newToday= new Day(id,LocalDate.now(),getnumerodemuertosahoramismo, average);
				//this.today=  newtoday;
						//a�adir newtoday a la lista de dias
						
				//recorrer la lista de pacientes para calcularles el score TODO mirar cual es mas optima para recorrer, arraylist o linkedlist
				/* for(int i=0;i<listadepacientes.size;i++{
				 * 
				 *(paquete estadisticas) calculateScore(listadepacientes.get(i),newtoday,todaslaslistasdeporcentagesobtenidosenstadisticas);
				 * 
				 * }
				 * Score.setFirstTime(false); alomejor hay que cambiar boolean por Bool para que funcione
				 */
				
				
			}
}
