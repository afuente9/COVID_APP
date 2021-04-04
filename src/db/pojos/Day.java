package db.pojos;

import java.sql.Date;
import java.time.LocalDate;

public class Day {
	private Integer id;
private int deaths;
private float average;
private LocalDate date;

public Day(Integer id,LocalDate date, int deaths, float average) {
	this.id=id;
	this.date=date;
	this.deaths=deaths;
	this.average=average;
}

public int getDeaths() {
	return deaths;
}

public float getAverage() {
	return average;
}

public LocalDate getDate() {
	return date;
}

public void setDeaths(int deaths) {
	this.deaths = deaths;
}

public void setAverage(float average) {
	this.average = average;
}

public void LocalDate(LocalDate date) {
	this.date = date;
}

@Override
public int hashCode() {
	// TODO Auto-generated method stub
	return super.hashCode();
}

@Override
public boolean equals(Object obj) {
	// TODO Auto-generated method stub
	return super.equals(obj);
}

@Override
protected Object clone() throws CloneNotSupportedException {
	// TODO Auto-generated method stub
	return super.clone();
}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return super.toString();
}

@Override
protected void finalize() throws Throwable {
	// TODO Auto-generated method stub
	super.finalize();
}




}
