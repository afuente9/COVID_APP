package db.interfaces;

import java.util.List;

import db.pojos.*;

public interface Interface {

	public void connect();
	
	public void disconnect();

	public void addPatient(Patient p);
	public Patient getPatient(int id);
	public List <Patient> searchPatientByName(String name);
	
	public void addDoctor(Doctor d);  
	 
	public void addLab(Lab l);  
	
	public void addShipment(Shipment s); // TODO create (id's)
	
	public void addGoverment(Administration a);
	
	public void addOtherPathologies(Other_Pathologies op); 
	
	public void addMedication(Medication m);  
}
