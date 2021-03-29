package db.pojos;

import java.io.Serializable;
import java.util.List;

public class Other_Pathologies implements Serializable{
	
	private Integer id;
	private String Name;
	private List<Other_Pathologies> other_path;
	
	public Other_Pathologies() {
		super();
	}

	public Other_Pathologies(Integer id, String name, List<Other_Pathologies> other_path) {
		super();
		this.id = id;
		Name = name;
		this.other_path = other_path;
	}
	public Other_Pathologies(Integer id, String name) {
		super();
		this.id = id;
		Name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((other_path == null) ? 0 : other_path.hashCode());
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
		if (other_path == null) {
			if (other.other_path != null)
				return false;
		} else if (!other_path.equals(other.other_path))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Other_Pathologies [id=" + id + ", Name=" + Name + ", other_path=" + other_path + "]";
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

	public List<Other_Pathologies> getOther_path() {
		return other_path;
	}

	public void setOther_path(List<Other_Pathologies> other_path) {
		this.other_path = other_path;
	}
	
	
	
}
