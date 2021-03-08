package db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Patient implements Serializable{
	
	private Integer id;
	private String hos_location;
	private String name;
	private Date birthday;
	private long social_security;
	private float heigh;
	private float weight;
	private Sex sex;
	private boolean infected;
	private boolean alive;
	private String hospital;
	private int score;
	private List<Patient> patients;
	private boolean is_vaccinated;
	private String bloodType;
	
	public Patient() {
		super();
		this.patients = new ArrayList<Patient>();
	}

	public Patient(Integer id, String hos_location, String name, Date birthday, long social_security, float heigh,
			float weight, Sex sex, boolean infected, boolean alive, String hospital, int score, List<Patient> patients,
			boolean is_vaccinated, String bloodType) {
		super();
		this.id = id;
		this.hos_location = hos_location;
		this.name = name;
		this.birthday = birthday;
		this.social_security = social_security;
		this.heigh = heigh;
		this.weight = weight;
		this.sex = sex;
		this.infected = infected;
		this.alive = alive;
		this.hospital = hospital;
		this.score = score;
		this.patients = patients;
		this.is_vaccinated = is_vaccinated;
		this.bloodType = bloodType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alive ? 1231 : 1237);
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + Float.floatToIntBits(heigh);
		result = prime * result + ((hos_location == null) ? 0 : hos_location.hashCode());
		result = prime * result + ((hospital == null) ? 0 : hospital.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (infected ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((patients == null) ? 0 : patients.hashCode());
		result = prime * result + score;
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + (int) (social_security ^ (social_security >>> 32));
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
		Patient other = (Patient) obj;
		if (alive != other.alive)
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (Float.floatToIntBits(heigh) != Float.floatToIntBits(other.heigh))
			return false;
		if (hos_location == null) {
			if (other.hos_location != null)
				return false;
		} else if (!hos_location.equals(other.hos_location))
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
		if (patients == null) {
			if (other.patients != null)
				return false;
		} else if (!patients.equals(other.patients))
			return false;
		if (score != other.score)
			return false;
		if (sex != other.sex)
			return false;
		if (social_security != other.social_security)
			return false;
		if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "Patient [id=" + id + ", hos_location=" + hos_location + ", name=" + name + ", birthday=" + birthday
				+ ", social_security=" + social_security + ", heigh=" + heigh + ", weight=" + weight + ", sex=" + sex
				+ ", infected=" + infected + ", alive=" + alive + ", hospital=" + hospital + ", score=" + score
				+ ", patients=" + patients + "]";
	}

	public String getHos_location() {
		return hos_location;
	}

	public void setHos_location(String hos_location) {
		this.hos_location = hos_location;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public boolean isIs_vaccinated() {
		return is_vaccinated;
	}

	public void setIs_vaccinated(boolean is_vaccinated) {
		this.is_vaccinated = is_vaccinated;
	}
}