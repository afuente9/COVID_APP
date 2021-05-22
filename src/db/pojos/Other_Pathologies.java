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
@Table(name = "otherPath")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Other_Pathologies")
@XmlType(propOrder = { "id", "Name"})

public class Other_Pathologies implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "otherPath")
	@TableGenerator(name = "otherPath", table = "sqlite_sequence", 
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "otherPath")	
	@XmlElement
	private Integer id;
	@XmlElement
	private String Name;
	
	@ManyToMany
	@JoinTable(name="patients", 
	joinColumns = {@JoinColumn(name="other_path_id", referencedColumnName="id")}, 
	inverseJoinColumns= {@JoinColumn(name= "patient_id", referencedColumnName="id")})
	@XmlTransient
	private List<Patient> patient;
	
	public Other_Pathologies() {
		super();
		this.patient =  new ArrayList<Patient>();
	}

	public Other_Pathologies(Integer id, String name) {
		super();
		this.id = id;
		Name = name;
	}
	public Other_Pathologies(String name) {
		super();
		Name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Other_Pathologies other = (Other_Pathologies) obj;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + ": " + Name ;
	}
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
}
