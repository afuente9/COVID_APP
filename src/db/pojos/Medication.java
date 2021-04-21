package db.pojos;

import java.io.Serializable;
import java.util.List;

public class Medication implements Serializable{
	
	private Integer id;
	private String name;
	
	public Medication() {
		super();
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
		return "Medication [id=" + id + ", name=" + name + "]";
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
