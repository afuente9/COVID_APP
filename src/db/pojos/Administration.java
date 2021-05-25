package db.pojos;

import java.io.Serializable;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Administration")
@XmlType(propOrder = { "id", "name", "vaccines"})


public class Administration implements Serializable{

	private static final long serialVersionUID = 1L;
	@XmlAttribute
	private Integer id;
	@XmlElement
	private int vaccines;
	@XmlAttribute
	private String name;

	@XmlTransient
	byte[] image;
	
	public Administration(Integer id,int vaccines, String name) {
		this.id=id;
		this.vaccines= vaccines;
		this.name = name;
		
	}

	
	public Administration() {
		super();
	}
	

	public Administration(int vaccines, String name) {
		super();
		this.vaccines = vaccines;
		this.name = name;
	}

	public Administration(String name, int vaccines) {
		super();
		this.vaccines = vaccines;
		this.name = name;
	}

	public Administration(Integer id, int vaccines, String name, byte[] image) {
		super();
		this.id = id;
		this.vaccines = vaccines;
		this.name = name;
		this.image = image;
	}

	public Administration(String name2) {
		this.name= name2;	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + vaccines;
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
		Administration other = (Administration) obj;
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
		if (vaccines != other.vaccines)
			return false;
		return true;
	}


	

	@Override
	public String toString() {
		return "Administration [id=" + id + ", vaccines=" + vaccines + ", name=" + name + "]";
	}


	public Integer getId() {
		return id;
	}
	
	public int getVaccines() {
		return vaccines;
	}

	
	public void setVaccines(int vaccines) {
		this.vaccines = vaccines;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}
	
	

}
