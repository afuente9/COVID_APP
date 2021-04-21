package db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Other_Pathologies implements Serializable{
	
	private Integer id;
	private String Name;
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
		return "Other_Pathologies [id=" + id + ", Name=" + Name + "]";
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
