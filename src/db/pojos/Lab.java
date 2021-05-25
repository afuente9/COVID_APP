package db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Lab")
@XmlType(propOrder = { "id", "name", "address", "vaccines_produce", "cif", "patients"})

public class Lab implements Serializable{

	private static final long serialVersionUID = 1L;
	@XmlAttribute
	private int id;
	@XmlElement
	private int vaccines_produce;
	@XmlElement
	private String address;
	@XmlAttribute
	private String name;
	@XmlElement
	private String cif;
	
	@XmlTransient
	byte[] image;
	
	@XmlElement(name="Patient")
	@XmlElementWrapper(name="patients")
	private List<Patient> patients;

	public Lab() {
		super();
		this.patients = new ArrayList<Patient>();
	}
	
	public Lab(int vaccines, String address, String name, String cif, List<Patient> pats) {
		super();
		this.vaccines_produce = vaccines;
		this.address = address;
		this.name = name;
		this.cif = cif;
		this.patients = pats;
	}

	public Lab(int id, int vaccines_produce, String address, String name, String cif) {
		super();
		this.id = id;
		this.vaccines_produce = vaccines_produce;
		this.address = address;
		this.name = name;
		this.cif = cif;
	}
	
	public Lab(int id, int vaccines_produce, String address, String name, String cif,  byte[] image) {
		super();
		this.id = id;
		this.vaccines_produce = vaccines_produce;
		this.address = address;
		this.name = name;
		this.cif = cif;
		this.image = image;
	}

	public Lab(String l_name, String l_address, String l_cif, int l_vac, byte[] imagen) {
		super();
		this.vaccines_produce = l_vac;
		this.address = l_address;
		this.name = l_name;
		this.cif = l_cif;
		this.image = imagen;
	}


	public Lab(String l_name, String l_adress, String l_cif, int l_vac) {
		super();
		this.vaccines_produce = l_vac;
		this.address = l_adress;
		this.name = l_name;
		this.cif = l_cif;
	}


	@Override
	public String toString() {
		return "Lab [id=" + id + ", vaccines_produce=" + vaccines_produce + ", address=" + address + ", name=" + name
				+ ", cif=" + cif + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + id;
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


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public List<Patient> getPatients() {
		return patients;
	}


	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
