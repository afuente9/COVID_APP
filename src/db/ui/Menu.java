package db.ui;

import java.io.*;
import java.util.*;

import db.interfaces.Interface;
import db.jdbc.JDBCManagment;

public class Menu {
	private static Interface inter = new JDBCManagment();
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		inter.connect();
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
		//inter.addPatient(new Patient(name));
	}
	

	private static void searchPatients() {
		// TODO Auto-generated method stub
		
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
}
