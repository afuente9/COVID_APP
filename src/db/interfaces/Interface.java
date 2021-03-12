package db.interfaces;

import java.util.List;

import db.pojos.*;

public interface Interface {

	public void connect();
	
	public void disconnect();

	public void addPatient(Patient p);
	public Patient getPatient(int id);
	public List <Patient> searchPatientByName(String name);
	
	public void addDoctor(Doctor d); // TODO create 
	 
	public void addLab(Lab l); // TODO create 
	
	public void addShipment(Shipment s); // TODO create 
	
	public void addGoverment(Administration a); // TODO create 
	
	public void addOtherPathologies(Other_Pathologies op); // TODO create 
	
	public void addMedication(Medication m); // TODO create 
}
