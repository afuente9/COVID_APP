package db.pojos;

import java.sql.Date;
import java.util.*;

public class Day {
	private Integer id;
	private int deaths;
	private int infectedPatients;
	private float average;
	private Date date;
	private List<Day> savedDates = new ArrayList();

	public Day(Integer id, int deaths,int infectedPatients , float average, Date date) {
		super();
		this.id = id;
		this.deaths = deaths;
		this.infectedPatients=infectedPatients;
		this.average = average;
		this.date = date;
	}
	public Day( int deaths, int infectedPatients,float average, Date date) {
		super();
		this.id = id;
		this.deaths = deaths;
		this.infectedPatients=infectedPatients;

		this.average = average;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}
	public int getInfectedPatients() {
		return infectedPatients;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setInfectedPatients(int infectedPatients) {
		this.infectedPatients = infectedPatients;
	}
	public List<Day> getSavedDates() {
		return savedDates;
	}

	public int getDeaths() {
		return deaths;
	}

	public float getAverage() {
		return average;
	}

	public Date getDate() {
		return date;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public void setAverage(float average) {
		this.average = average;
	}

	public void setSavedDates(List<Day> savedDates) {
		this.savedDates = savedDates;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void LocalDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(average);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + deaths;
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
		Day other = (Day) obj;
		if (Float.floatToIntBits(average) != Float.floatToIntBits(other.average))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (deaths != other.deaths)
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
		return "Day [id=" + id + ", deaths=" + deaths + ", average=" + average + ", date=" + date + "]";
	}

}
