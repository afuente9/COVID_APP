package db.interfaces;

import java.util.List;

import db.pojos.Patient;

public interface Interface {

	public void connect();
	
	public void disconnect();

	public void addPatient(Patient p);
	public Patient getPatient(int id);
	public List <Patient> searchPatientByName(String name);
	
	
}
