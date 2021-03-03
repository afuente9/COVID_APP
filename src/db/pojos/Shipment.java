package db.pojos;

import java.io.Serializable;
import java.sql.Date;

public class Shipment implements Serializable{
	
	private Integer id;
	private int vaccines;
	private Date date_ship;
	
	public Shipment() {
		super();
	}
	
	public Shipment(Integer id, int vaccines, Date date_ship) {
		super();
		this.id = id;
		this.vaccines = vaccines;
		this.date_ship = date_ship;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date_ship == null) ? 0 : date_ship.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Shipment other = (Shipment) obj;
		if (date_ship == null) {
			if (other.date_ship != null)
				return false;
		} else if (!date_ship.equals(other.date_ship))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (vaccines != other.vaccines)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Shipment [id=" + id + ", vaccines=" + vaccines + ", date_ship=" + date_ship + "]";
	}

	public int getVaccines() {
		return vaccines;
	}

	public void setVaccines(int vaccines) {
		this.vaccines = vaccines;
	}

	public Date getDate_ship() {
		return date_ship;
	}

	public void setDate_ship(Date date_ship) {
		this.date_ship = date_ship;
	}
	
	
}
