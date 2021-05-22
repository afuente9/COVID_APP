package db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "medications")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Medication")
@XmlType(propOrder = { "id", "name"})

public class Medication implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "medications")
	@TableGenerator(name = "medications", table = "sqlite_sequence", 
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "medications")	
	@XmlElement
	private Integer id;
	@XmlElement
	private String name;
	@ManyToMany
	@JoinTable(name="patients", 
	joinColumns = {@JoinColumn(name="medic_id", referencedColumnName="id")}, 
	inverseJoinColumns= {@JoinColumn(name= "patient_id", referencedColumnName="id")})
	@XmlTransient
	private List<Patient> patients;
	
	public Medication() {
		super();
		this.patients = new ArrayList<Patient>();
	}

	public Medication(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Medication(String n_med) {
		super();
		this.name = n_med;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Medication other = (Medication) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ""+id + ": " + name ;
	}

	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
