package db.ui;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import db.pojos.*;

import db.interfaces.Cov_Manager;
import db.jdbc.JDBCManagment;

public class Menu {
	//public Day today=ultimo dia a�adido a la lista de dias;

	
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
			System.out.println("0. Exit					");
			
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				addPatient();
				break;
			case 2:
				searchPatients();
				break;
			case 3:
				addDoctor();
				break;
			case 4:
				addLab();
				break;
			case 0:
				inter.disconnect();
				System.exit(0);
			default:
				break;
			}
		}
		while(true);
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
		
		inter.addPatient(new Patient (p_hosL, p_name, Date.valueOf(p_bday), p_ss, p_height, p_weight, p_sex, p_infected, p_alive, p_hos, p_vaccinated, p_blood));
	}
	

	private static void searchPatients() throws Exception {
		System.out.println("Please, input the person info:");
		System.out.print("Name: ");
		String p_name = reader.readLine();
		List<Patient> result = inter.searchPatientByName(p_name);
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
		System.out.print("Do you want to add a photo? (Yes/No): ");
		String YesNo = reader.readLine();
		if(YesNo.equalsIgnoreCase("no")) {
			inter.addDoctor(new Doctor(d_name, d_speciality, Date.valueOf(d_bday), d_cn, d_hosp, d_sex), YesNo);
		}
		else {
			System.out.print("Type the file name as it appears in folder /photos, including extension: ");
			String fileName = reader.readLine();
			File photo = new File("./photos/" + fileName);
			InputStream streamBlob = new FileInputStream(photo);
			byte[] d_imagen = new byte[streamBlob.available()];
			inter.addDoctor(new Doctor(d_name, d_speciality, Date.valueOf(d_bday), d_cn, d_hosp, d_sex, d_imagen), YesNo);
		}
	}
	
	
	private static void addLab() throws Exception{
		//TODO a�ade laboratorios?
		System.out.println("Please, input the LAB info:");
		System.out.print("Name: ");
		String l_name = reader.readLine();
		System.out.print("Adress: ");
		String l_adress = reader.readLine();
		System.out.print("CIF: ");
		String l_cif = reader.readLine();
		System.out.print("Vaccines: ");
		int l_vac = Integer.parseInt(reader.readLine());
		System.out.print("Do you want to add a photo? (Yes/No): ");
		String YesNo = reader.readLine();
		if(YesNo.equalsIgnoreCase("no")) {
			inter.addLab(new Lab(l_name, l_adress, l_cif, l_vac), YesNo);
		}
		else {
			byte[] l_imagen = null;
			inter.addLab(new Lab(l_name, l_adress, l_cif, l_vac, l_imagen), YesNo);
		}
	}
	public void newDay() { 
		
		//TODO  esto pasara if today != LocalDate.now() 
		
		//calcular la media de muertos de los ultimos 7 dias
		
				float average=0;
				
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
