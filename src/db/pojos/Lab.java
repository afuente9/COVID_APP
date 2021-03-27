package db.pojos;

import java.io.Serializable;
import java.util.List;


public class Lab implements Serializable{
	private int id;
	private int vaccines_produce;
	private String address;
	private String name;
	private String cif;
	private List<Lab> labs;
	
	public Lab() {
		super();
	}
	

	public Lab(int id2, String lname, String ladress, String lcif, int lvacciness) {
		super();
		this.id = id2;
		this.name = lname;
		this.address = ladress;
		this.cif = lcif;
		this.vaccines_produce = lvacciness;
	}






	@Override
	public String toString() {
		return "Lab [id=" + id + ", vaccines_produce=" + vaccines_produce + ", address=" + address + ", name=" + name
				+ ", cif=" + cif + ", labs=" + labs + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + id;
		result = prime * result + ((labs == null) ? 0 : labs.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + vaccines_produce;
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
		Lab other = (Lab) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (id != other.id)
			return false;
		if (labs == null) {
			if (other.labs != null)
				return false;
		} else if (!labs.equals(other.labs))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (vaccines_produce != other.vaccines_produce)
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public int getVaccines_produce() {
		return vaccines_produce;
	}

	public void setVaccines_produce(int vaccines_produce) {
		this.vaccines_produce = vaccines_produce;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public List<Lab> getLabs() {
		return labs;
	}

	public void setLabs(List<Lab> labs) {
		this.labs = labs;
	}
	
	
}
