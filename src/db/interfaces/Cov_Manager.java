package db.interfaces;

import java.util.List;

import db.pojos.*;

public interface Cov_Manager {

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
	
	// Add a new patient
	public void addShipment(Shipment s); // TODO create (id's)
	
	// Add a new Government
	public void addGoverment(Administration a);
	
	// Add a new Pathology
	public void addOtherPathologies(Other_Pathologies op); 
	
	// Add a new Medication
	public void addMedication(Medication m);
	
	// Assign a pathology to a patient
	public void assignPatho(Patient p, Other_Pathologies op);
	
	// Assign a pathology to a patient
	public void assignMed(Patient p, Medication m);
	
}
