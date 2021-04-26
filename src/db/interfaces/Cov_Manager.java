package db.interfaces;

import java.util.*;

import db.pojos.*;

public interface Cov_Manager {
	// Add a new day 
	public void addDay(Day f);
	// Get a list of all the days
	public List<Day> getDay(int id);

	// Connects with the database and, if needed, performs necessary setup
	public void connect();
	// Closes the connection with the database
	public void disconnect();

	// Add a new patient
	public void addPatient(Patient p);
	// Get a particular patient
	public Patient getPatient(int id);
	// Search patient by name
	// If name is empty or null, returns all people
	public List <Patient> searchPatientByName(String name);
//en pruebas
	public List <Patient> searchPatientGeneric(String feature, String type);
		
	// Add a new doctor
	public void addDoctor(Doctor d);
	// Get a particular doctor
	public Doctor getDoctor(int id);
	
	// Provides a list with all the patients of a doctor
	public List<Patient> getPatientsOfDoctor(int doc_id);
	 
	// Provides a list with all the doctors that a patient has
	public List<Doctor> getDoctorsOfPatient(int pat_id);
	
	// Add a new lab
	public void addLab(Lab l);
	
	// Gets the lab 
	public Lab getLab(int id);
	
	// Show all labs
	public List<Lab> showLabs();
		
	//get total number of patients
	public int getNumberofPatients();
	
	// Add a new shipment
	public void addShipment(Shipment s, Lab l, Administration a);
	
	// Add a new Government
	public void addGoverment(Administration a);
	
	// Add a new Pathology
	public void addOtherPathologies(Other_Pathologies op); 
	
	// Add a new Medication
	public void addMedication(Medication m);
	
	// Assign a pathology to a patient
	public void assignPatho(int id, Other_Pathologies op);
	
	// Assign a pathology to a patient
	public void assignMed(int id, Medication m);

	// Delete a medication from the list using the name
	public void deleteMedByName(Medication m);

	// Delete a pathology from the list using the name
	public void deletePathologyByName(Other_Pathologies op);
	
	//Modifies a doctor information
	public void modifyDoctor(int iden, String atrib, String value);
	
	//Modifies a laboratory information
	public void modifyLab(int iden, String atrib, String value);
	
	//Gets a list with all the medications added to the DDBB
	public List<Medication> searchMedicationByName(String name);
	
	//Gets the medication based on the name
	public Medication getMedication(String name);
	Patient getLastPatient();
	Medication getLastMedication();
	Other_Pathologies getLastPath();
	void modifyPatient(int iden, String atrib, String value);
	public List<Medication> getMedicationfromPatient(Integer id);
	
}
