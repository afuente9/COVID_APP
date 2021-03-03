package db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


public class Doctor implements Serializable{
	
	private Integer id;
	private boolean online;
	private String speciality;
	private String name;
	private Date birthday;
	private long social_security;
	private long collegiate_number;
	private float heigh;
	private float weight;
	private Sex sex;
	private boolean infected;
	private boolean alive;
	private String hospital;
	private List<Doctor> doctors;
	
	public Doctor() {
		super();
	}

	public Doctor(Integer id, boolean online, String speciality, String name, Date birthday, long social_security,
			long collegiate_number, float heigh, float weight, Sex sex, boolean infected, boolean alive,
			String hospital, List<Doctor> doctors) {
		super();
		this.id = id;
		this.online = online;
		this.speciality = speciality;
		this.name = name;
		this.birthday = birthday;
		this.social_security = social_security;
		this.collegiate_number = collegiate_number;
		this.heigh = heigh;
		this.weight = weight;
		this.sex = sex;
		this.infected = infected;
		this.alive = alive;
		this.hospital = hospital;
		this.doctors = doctors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alive ? 1231 : 1237);
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + (int) (collegiate_number ^ (collegiate_number >>> 32));
		result = prime * result + ((doctors == null) ? 0 : doctors.hashCode());
		result = prime * result + Float.floatToIntBits(heigh);
		result = prime * result + ((hospital == null) ? 0 : hospital.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (infected ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (online ? 1231 : 1237);
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + (int) (social_security ^ (social_security >>> 32));
		result = prime * result + ((speciality == null) ? 0 : speciality.hashCode());
		result = prime * result + Float.floatToIntBits(weight);
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
		if (alive != other.alive)
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (collegiate_number != other.collegiate_number)
			return false;
		if (doctors == null) {
			if (other.doctors != null)
				return false;
		} else if (!doctors.equals(other.doctors))
			return false;
		if (Float.floatToIntBits(heigh) != Float.floatToIntBits(other.heigh))
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
		if (infected != other.infected)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (online != other.online)
			return false;
		if (sex != other.sex)
			return false;
		if (social_security != other.social_security)
			return false;
		if (speciality == null) {
			if (other.speciality != null)
				return false;
		} else if (!speciality.equals(other.speciality))
			return false;
		if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", online=" + online + ", speciality=" + speciality + ", name=" + name
				+ ", birthday=" + birthday + ", social_security=" + social_security + ", collegiate_number="
				+ collegiate_number + ", heigh=" + heigh + ", weight=" + weight + ", sex=" + sex + ", infected="
				+ infected + ", alive=" + alive + ", hospital=" + hospital + ", doctors=" + doctors + "]";
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
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

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public long getSocial_security() {
		return social_security;
	}

	public void setSocial_security(long social_security) {
		this.social_security = social_security;
	}

	public long getCollegiate_number() {
		return collegiate_number;
	}

	public void setCollegiate_number(long collegiate_number) {
		this.collegiate_number = collegiate_number;
	}

	public float getHeigh() {
		return heigh;
	}

	public void setHeigh(float heigh) {
		this.heigh = heigh;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public boolean isInfected() {
		return infected;
	}

	public void setInfected(boolean infected) {
		this.infected = infected;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	
}
