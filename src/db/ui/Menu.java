package db.ui;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import db.pojos.*;

import db.interfaces.Cov_Manager;
import db.jdbc.JDBCManagment;

public class Menu {
	//public Day today=ultimo dia aï¿½adido a la lista de dias;

	
	private static Cov_Manager inter = new JDBCManagment();
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static void main(String[] args) throws Exception {
		inter.connect();
		/*if (today.getDate() != LocalDate.now()) {
			newDay();
		
		}
		*/
		do {
			System.out.println("Choose an option:		");
			System.out.println("1. Add a patient		");
			System.out.println("2. Search patients		");
			System.out.println("3. Add a doctor			");
			System.out.println("4. Add a lab			");
			System.out.println("5. Add a medication		");
			System.out.println("6. Modify doctor        ");
			System.out.println("7. Get doctor           ");
			System.out.println("8. Modify lab           ");
			System.out.println("9. Get lab              ");
			System.out.println("10. Get medication      ");
			System.out.println("0. Exit					");
			
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				addPatient();
				break;
			case 2:
				searchPatientGeneric();
				break;
			case 3:
				addDoctor();
				break;
			case 4:
				addLab();
				break;
			case 5:
				addMedication();
				break;
			case 6:
				modifyDoctor();
				break;
			case 7:
				getDoc();
				break;
			case 8:
				modifyLab();
				break;
			case 9:
				getLab();
				break;
			case 10:
				searchMed();
				break;
			case 0:
				//TODO ESTO PARA LA GUI?
				inter.disconnect();
				System.exit(0);
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
		System.out.print("Do you want to add any medication? (True or False): ");
		Boolean p_med = Boolean.parseBoolean(reader.readLine());
		if(p_med.TRUE) {
			System.out.println();
			addMedication();
		}
		
		inter.addPatient(new Patient (p_hosL, p_name, Date.valueOf(p_bday), p_ss, p_height, p_weight, p_sex, p_infected, p_alive, p_hos, p_vaccinated, p_blood));
		System.out.println("Select patient: ");
		inter.searchPatientByName(p_name);
		Integer id_pac = Integer.parseInt(reader.readLine());
		System.out.println("Now the medications: ");
		inter.searchMedicationByName("");
		System.out.print("Does the medication exist? (True, False or NN): ");
		Boolean check = Boolean.parseBoolean(reader.readLine());
		if(check.TRUE) {
			System.out.println("Select the medication (write the name): ");
			String med_name = reader.readLine();
			inter.assignMed(inter.getPatient(id_pac), inter.getMedication(med_name));
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
	
	
	private static void addDoctor() throws Exception{
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
		inter.addDoctor(new Doctor(d_name, d_speciality, Date.valueOf(d_bday), d_cn, d_hosp, d_sex));
	}
	
	
	private static void addLab() throws Exception{
		System.out.println("Please, input the LAB info:");
		System.out.print("Name: ");
		String l_name = reader.readLine();
		System.out.print("Adress: ");
		String l_adress = reader.readLine();
		System.out.print("CIF: ");
		String l_cif = reader.readLine();
		System.out.print("Vaccines: ");
		int l_vac = Integer.parseInt(reader.readLine());
		inter.addLab(new Lab(l_name, l_adress, l_cif, l_vac));
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
				System.out.println("Which atribute want you to change? (name, cif or adress)");//vacciness No se si necesitamos algo que modifique el nº de vacunas en este 
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
						//aï¿½adir newtoday a la lista de dias
						
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
