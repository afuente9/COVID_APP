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
	//public Day today=ultimo dia añadido a la lista de dias;

	
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
			System.out.println("3. Add a lab			");
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
			case 4:
				addLab();
				case 5:
					searchPatients();
					
				case 0:
				inter.disconnect();
				System.exit(0);
				break;
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
		
		//inter.addPatient(new Patient (p_hosL, p_name, Date.valueOf(p_bday), p_ss, p_height, p_weight, p_sex, p_infected, p_alive, p_hos, p_vaccinated, p_blood));
	}
	

	private static void searchPatients() throws Exception {
		// TODO terminar metodo
		System.out.println("Please, input the person info:");
		System.out.print("Name: ");
		String p_name = reader.readLine();
		/*List<Patient> result = searchPatientByName(p_name);
		result.toString();*/
	}
	
	
	private static void addDoctor() throws Exception{
		System.out.println("Please, input the person info:");
		System.out.print("Name: ");
		String d_name = reader.readLine();
		//inter.addPatient(new Patient(name));
	}
	
	
	private static void addLab() throws Exception{
		System.out.println("Please, input the person info:");
		System.out.print("Name: ");
		String l_name = reader.readLine();
		//inter.addPatient(new Patient(name));
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
						//añadir newtoday a la lista de dias
						
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
