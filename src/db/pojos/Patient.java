package db.pojos;

import java.io.Serializable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient implements Serializable{
	
	private Integer id;
	private String hos_location;
	private String name;
	private Date birthday;
	private String social_security;
	private float height;
	private float weight;
	private Sex sex;
	private boolean infected;
	private boolean alive;
	private String hospital;
	private int score;
	private boolean Vaccinated;
	private String bloodType;	
	private Date DateIntroduced;
	private List<Medication> medication;
	private List<Other_Pathologies> other_pathologies;
	private List<Doctor> doctors;
	
	
	public Patient() {
		super();
		this.medication = new ArrayList<Medication>();
		this.other_pathologies = new ArrayList<Other_Pathologies>();
		this.doctors = new ArrayList<Doctor>();
	}
	
	
	

	public Patient(Integer id,String hos_location, String name, Date birthday, String social_security, float height, float weight,
			Sex sex, boolean infected, boolean alive, String hospital, boolean Vaccinated, String bloodType,List<Medication> medication,List<Other_Pathologies> other_pathologies) {
		super();
		this.id=id;
		this.hos_location = hos_location;
		this.name = name;
		this.birthday = birthday;
		this.social_security = social_security;
		this.height = height;
		this.weight = weight;
		this.sex = sex;
		this.infected = infected;
		this.alive = alive;
		this.hospital = hospital;
		this.Vaccinated = Vaccinated;
		this.bloodType = bloodType;
		this.medication=medication;
		this.other_pathologies=other_pathologies;
		
	}




	public Patient(Integer id, String hos_location, String name, Date birthday, String social_security, float height,
			float weight, Sex sex, boolean infected, boolean alive, String hospital, boolean Vaccinated,
			String bloodType,Date dateIntroduced) {
		super();
		this.id = id;
		this.hos_location = hos_location;
		this.name = name;
		this.birthday = birthday;
		this.social_security = social_security;
		this.height = height;
		this.weight = weight;
		this.sex = sex;
		this.infected = infected;
		this.alive = alive;
		this.hospital = hospital;
		this.Vaccinated = Vaccinated;
		this.bloodType = bloodType;
 		this.DateIntroduced=dateIntroduced;

	}


	public Patient(Integer id, String hos_location, String name, Date birthday, String social_security, float height,
			float weight, Sex sex, boolean infected, boolean alive, String hospital, int score, boolean is_vaccinated, String bloodType,Date dateIntroduced) {
		super();
		this.id = id;
		this.hos_location = hos_location;
		this.name = name;
		this.birthday = birthday;
		this.social_security = social_security;
		this.height = height;
		this.weight = weight;
		this.sex = sex;
		this.infected = infected;
		this.alive = alive;
		this.hospital = hospital;
		this.score = score;
		this.Vaccinated = is_vaccinated;
		this.bloodType = bloodType;
 		this.DateIntroduced=dateIntroduced;

	}
	
	// NO TOCAR ESTE CONSTRUCTOR QUE ES IMPORTANTE!!!!
	//TODO ANADIR LAS LISTAS DE PATOLOGIAS Y MEDICACION
	//TODO ANADIR LOCALDATE Date INTRODUCED PARA QUE LUEGO LOS PODAMOS FILTRAR 
	
	public Patient(String hos_location, String name, Date birthday, String social_security, float height, float weight,
 			Sex sex, boolean infected, boolean alive, String hospital, boolean Vaccinated, String bloodType,Date dateIntroduced) {
 		super();
 		this.hos_location = hos_location;
 		this.name = name;
 		this.birthday = birthday;
 		this.social_security = social_security;
 		this.height = height;
 		this.weight = weight;
 		this.sex = sex;
 		this.infected = infected;
 		this.alive = alive;
 		this.hospital = hospital;
 		this.Vaccinated = Vaccinated;
 		this.bloodType = bloodType;
 		this.DateIntroduced=dateIntroduced;
 	}

	public Patient(Integer id, String patientName) {
		super();
		this.id = id;
		this.name = patientName;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alive ? 1231 : 1237);
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((bloodType == null) ? 0 : bloodType.hashCode());
		result = prime * result + Float.floatToIntBits(height);
		result = prime * result + ((hos_location == null) ? 0 : hos_location.hashCode());
		result = prime * result + ((hospital == null) ? 0 : hospital.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (infected ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((social_security == null) ? 0 : social_security.hashCode());
		result = prime * result + (Vaccinated ? 1231 : 1237);
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
		if (bloodType == null) {
			if (other.bloodType != null)
				return false;
		} else if (!bloodType.equals(other.bloodType))
			return false;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
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
		if (score != other.score)
			return false;
		if (sex != other.sex)
			return false;
		if (social_security == null) {
			if (other.social_security != null)
				return false;
		} else if (!social_security.equals(other.social_security))
			return false;
		if (Vaccinated != other.Vaccinated)
			return false;
		if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Patient [id=" + id + ", hos_location=" + hos_location + ", name=" + name + ", birthday=" + birthday
				+ ", social_security=" + social_security + ", height=" + height + ", weight=" + weight + ", sex=" + sex
				+ ", infected=" + infected + ", alive=" + alive + ", hospital=" + hospital + ", score=" + score;
	}

	
	
	public Integer getId() {
		return id;
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

	
	public String getSocial_security() {
		return social_security;
	}

	
	public void setSocial_security(String social_security) {
		this.social_security = social_security;
	}

	
	public float getHeight() {
		return height;
	}

	
	public void setHeight(float height) {
		this.height = height;
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

	
	public boolean getVaccinated() {
		return Vaccinated;
	}

	
	public Date getDateIntroduced() {
		return DateIntroduced;
	}




	public List<Medication> getMedication() {
		return medication;
	}




	public List<Other_Pathologies> getOther_pathologies() {
		return other_pathologies;
	}




	public List<Doctor> getDoctors() {
		return doctors;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public void setDateIntroduced(Date dateIntroduced) {
		this.DateIntroduced = DateIntroduced;
	}




	public void setMedication(List<Medication> medication) {
		this.medication = medication;
	}




	public void setOther_pathologies(List<Other_Pathologies> other_pathologies) {
		this.other_pathologies = other_pathologies;
	}




	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}




	public void setVaccinated(boolean vaccinated) {
		this.Vaccinated = vaccinated;
	}

	public int getTheAge(Date birthday2) {
		LocalDate temp = LocalDate.now().minusYears(birthday2.toLocalDate().getYear());
		return temp.getYear();
	}
}