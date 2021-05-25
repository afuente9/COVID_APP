package db.interfaces;

import java.io.OutputStream;
import java.sql.Date;
import java.util.*;

import db.pojos.*;
import db.pojos.users.User;

public interface Cov_Manager {
	// Add a new day 
	public void addDay(Day f);
	// Get a list of all the days
	public List<Day> getDay(int id);

	// Connects with the database and, if needed, performs necessary setup
	public void connect();
	public void connectWithNoPrint();
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
	public List<Patient> getPatientsfromLab(int Lid);
	public List<Patient> getPatientsfromDoc(int Did);
		
	// Add a new doctor
	public void addDoctor(Doctor d);
	public void addDoctorUser(Doctor d, User u);
	// Get a particular doctor
	public Doctor getDoctor(int id);
	
	// Provides a list with all the patients of a doctor
	public List<Patient> getPatientsOfDoctor(int doc_id);
	 
	// Provides a list with all the doctors that a patient has
	public List<Doctor> getDoctorsOfPatient(int pat_id);
	
	// Add a new lab
	public void addLab(Lab l);
	public void addLabUser(Lab l, User u);
	
	// Gets the lab 
	public Lab getLab(int id);	
	
	//Gets a list with all the Object that exist in the DDBB
	public List<Administration> getAllAdmins();
	public List<Doctor> getAllDoctors();
	public List<Medication> getAllMedication();
	public List<Patient> getAllPatient();
	public List<Other_Pathologies> getAllPatho();
	public List<Lab> showLabs();
	public List<Lab> getAllLabsXML();
	public List<Doctor> getAllDoctorsXML();
	public List<Administration> getAllGovermentsXML();


	//Create tables
	public void creatTables();
		
	//get total number of patients
	public int getNumberofPatients();
	
	// Add a new shipment
	public void addShipment(Shipment s, Lab l, Administration a);
	
	// Add a new Government
	public void addGoverment(Administration a);
	public void addGovermentUser(Administration a, User u);
	
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
	
	//Gets the last X
	Patient getLastPatient();
	Medication getLastMedication();
	Other_Pathologies getLastPath();
	
	//Modifies a patient (located usin the ID) atribute (atrib) to the value (value)
	void modifyPatient(int iden, String atrib, String value);
	
	//Gets all the medications associated to a patient that is located ussing its ID
	public List<Medication> getMedicationfromPatient(int id);
	
	//Deletes X from the patient list
	void deleteAssignmentPathology(int idPatient, int idpatho);
	void deleteAssignmentMedication(Integer idPatient, int idmedication);
	
	//Filters the patients between two dates
	List<Patient> filterPatient(String dateFrom, String dateTo);
	
	//Gets the ID from X
	Medication getMedicationId(Integer id);
	Other_Pathologies getPathologyId(Integer id);
	
	List<Other_Pathologies> getPathofromPatient(int id);
	List<Patient> getPatientbyBD(String BDdate);
	List<Patient> getPatientbyDateIntro(String dateintro);
	void ModifyVaccinesFromLab(int amount, int id);
	int getNumberVaccinesLab(int id);
	
	//Changes the picture from X
	public void changeDocPic(Doctor d, byte[] pic);
	public void changeLabPic(Lab l, byte[] pic);
	
	//Gets the actual image from X
	public byte[] getPicFromDoc(int id);
	public byte[] getPicFromLab(int id);
	
	//Used for calculating the score
	Day getLastDay();
	int getNumberofDeads();
	List<Day> getLast7Days();
	int getNumberofDays();
	List<Integer> getNumberofDeadsofEachDay();
	int getNumberofInfecteds();
	List<Integer> getNumberofInfectedsofEachDay();
	int searchPatientGenericCOUNT(String feature, String type, boolean alive);
	List<Date> getDates(boolean alive);
	int getNumberPatientsbyRangeofFeature(String feature, float max, float min, boolean alive);
	List<String> getdifferentHospitals(boolean alive);
	int getNumberPatientsbyanyString(String feature, String type, boolean alive);
	List<String> getdifferentMeds(boolean alive);
	List<String> getdifferentPaths(boolean alive);
	int getdifferentMedsCOUNT(String name, boolean alive);
	int getdifferentPathsCOUNT(String name, boolean alive);
	List<String> getMedicationfromPatientNAME(int id);
	List<String> getPathofromPatientNAME(int id);
	void modifyScore(int id, float value);
	List<Patient> getSimulatedPatients(int availableVaccines, int id0);
	int searchadminIDByName(String name);
	boolean adminRegisteredByName(String name);
	boolean MedicationRegisteredByName(String name);
	int getIdMedicationbyName(String name);
	List<Medication> getMedicationfromPatientwithoutID(int id);
	boolean PathologyRegisteredByName(String name);
	Other_Pathologies getPatByName(String name);
	List<Other_Pathologies> getPatfromPatientwithoutID(int id);
	List<Shipment> getAllShipmentforAdminView(int id);
	Administration getAdministration(int id0);
	void ModifyVaccinesAdmin(int amount, int id);
	String getAdministrationOnlyName(int id0);
	int getNumberVaccinesAdmin(int id);
	List<String> getdifferentCountries(boolean alive);
	int getNumberPatientsbyGOVID(int govId, boolean alive);
	
	//Allows to check if an object already exist in the DDBB ignoring the ID
	public boolean checkAdmin(Administration admin);
	public boolean checkLab(Lab labo);
	public boolean checkMeds(Medication med);
	public boolean checkOther_Pat(Other_Pathologies other);
	public boolean checkPatient(Patient pat);
	public boolean checkDoctor(Doctor doc);
	boolean checkAdminName(String name);
	Doctor getDoctorbyUser(User u);
	Lab getLabByUser(User u);
	Administration getAdministrationbyUser(User u);
	User getUserbydoctor(Doctor d);
	String getUserMailbydoctor(Doctor d);
	String getUserMailbylab(Lab l);
	String getUserMailbyadmin(Administration admin);
	List<Shipment> getAllShipment(int id);
	void openpicturedoctor(Doctor ds);
	void openpicturelab(Lab l);
	void assignPattoDoc(int idPati, Doctor doc);
	Doctor getLastDoctor();
	void assignPattoLab(int idPati, Lab l);
	Lab getLastLab();
	List<Patient> getPatientsOfLab(int lab_id);
	

}
