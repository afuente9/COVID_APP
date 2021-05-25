package db.pojos;

import java.io.Serializable;
import java.sql.Date;
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import db.xml.utils.SQLDateAdapter;
import db.xml.utils.SexAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Doctor")
@XmlType(propOrder = { "id", "name", "speciality", "birthday", "collegiate_number", "sex", "hospital", "patients"})

public class Doctor implements Serializable{

	private static final long serialVersionUID = 1L;
	@XmlAttribute
	private Integer id;
	@XmlElement
	private String speciality;
	@XmlAttribute
	private String name;
	@XmlElement
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date birthday;
	@XmlElement
	private String collegiate_number;
	@XmlElement
	@XmlJavaTypeAdapter(SexAdapter.class)	
	private Sex sex;
	@XmlElement
	private String hospital;
	
	@XmlTransient
	private byte[] image;
	@XmlElement(name="patients")
	@XmlElementWrapper(name="Patient")
	private List<Patient> patients;

	public Doctor() {
		super();
		this.patients = new ArrayList<Patient>();
	}
	
	public Doctor(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Doctor(String name, String speciality, Date birthday, String collegiate_number, Sex sex,
			String hospital, List<Patient> pats) {
		super();
		this.name = name;
		this.speciality = speciality;
		this.birthday = birthday;
		this.collegiate_number = collegiate_number;
		this.sex = sex;
		this.hospital = hospital;
		this.patients = pats;
	}


	public Doctor(Integer id, String speciality, String name, Date birthday, String collegiate_number, Sex sex,
			String hospital,  byte[] image) {
		super();
		this.id = id;
		this.speciality = speciality;
		this.name = name;
		this.birthday = birthday;
		this.collegiate_number = collegiate_number;
		this.sex = sex;
		this.hospital = hospital;
		this.image = image;
	}

	public Doctor(String d_name, String d_speciality, Date d_bday, String d_cn, String d_hosp, Sex d_sex, byte[] imagen) {
		super();
		this.speciality = d_speciality;
		this.name = d_name;
		this.birthday = d_bday;
		this.collegiate_number = d_cn;
		this.sex = d_sex;
		this.hospital = d_hosp;
		this.image = imagen;
	}

	public Doctor(String d_name, String d_speciality, Date d_bday, String d_cn, String d_hosp, Sex d_sex) {
		super();
		this.speciality = d_speciality;
		this.name = d_name;
		this.birthday = d_bday;
		this.collegiate_number = d_cn;
		this.sex = d_sex;
		this.hospital = d_hosp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((collegiate_number == null) ? 0 : collegiate_number.hashCode());
		result = prime * result + ((hospital == null) ? 0 : hospital.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((speciality == null) ? 0 : speciality.hashCode());
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
		Doctor other = (Doctor) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (collegiate_number == null) {
			if (other.collegiate_number != null)
				return false;
		} else if (!collegiate_number.equals(other.collegiate_number))
			return false;
		if (hospital == null) {
			if (other.hospital != null)
				return false;
		} else if (!hospital.equals(other.hospital))
			return false;
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
		if (sex != other.sex)
			return false;
		if (speciality == null) {
			if (other.speciality != null)
				return false;
		} else if (!speciality.equals(other.speciality))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", speciality=" + speciality + ", name=" + name + ", birthday=" + birthday
				+ ", collegiate_number=" + collegiate_number + ", sex=" + sex + ", hospital=" + hospital + "]";
	}


	public String getSpeciality() {
		return speciality;
	}

	
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public Date getBirthday() {
		return birthday;
	}

	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCollegiate_number() {
		return collegiate_number;
	}

	public void setCollegiate_number(String collegiate_number) {
		this.collegiate_number = collegiate_number;
	}

	public Sex getSex() {
		return sex;
	}

	
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	
	public String getHospital() {
		return hospital;
	}

	
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}


	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
}
