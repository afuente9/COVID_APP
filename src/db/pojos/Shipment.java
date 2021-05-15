package db.pojos;

import java.io.Serializable;
import java.sql.Date;

import db.GUI.Main;

public class Shipment implements Serializable{
	
	private Integer id;
	private int Vaccines;
	private String LabName;
	private Date date_ship;
	private int governmentID;
	public Shipment() {
		super();
	}
	
	public Shipment(Integer id, int vaccines, Date date_ship,String LabName , int governmentID) {
		super();
		this.id = id;
		this.Vaccines = vaccines;
		this.date_ship = date_ship;
		this.LabName=LabName;
		this.governmentID=governmentID;
	}
	public String getGovernmentID() {
		return Main.getInter().getAdministrationOnlyName(this.governmentID);
	}

	public void setGovernmentID(int governmentID) {
		this.governmentID = governmentID;
	}

	public Shipment( int vaccines, Date date_ship, String LabName,int governmentID) {
		super();
		this.Vaccines = vaccines;
		this.date_ship = date_ship;
		this.LabName=LabName;
		this.governmentID=governmentID;

	}public Shipment( int vaccines, Date date_ship, String LabName) {
		super();
		this.Vaccines = vaccines;
		this.date_ship = date_ship;
		this.LabName=LabName;

	}

	public Integer getId() {
		return id;
	}

	public String getLabName() {
		return LabName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLabName(String labName) {
		LabName = labName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date_ship == null) ? 0 : date_ship.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Vaccines;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shipment other = (Shipment) obj;
		if (date_ship == null) {
			if (other.date_ship != null)
				return false;
		} else if (!date_ship.equals(other.date_ship))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Vaccines != other.Vaccines)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Shipment [id=" + id + ", vaccines=" + Vaccines + ", date_ship=" + date_ship + "]";
	}

	public int getVaccines() {
		return Vaccines;
	}

	public void setVaccines(int vaccines) {
		this.Vaccines = vaccines;
	}

	public Date getDate_ship() {
		return date_ship;
	}

	public void setDate_ship(Date date_ship) {
		this.date_ship = date_ship;
	}
	
	
}
