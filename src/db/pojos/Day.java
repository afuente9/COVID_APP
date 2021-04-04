package db.pojos;

import java.sql.Date;

public class Day {
private int deaths;
private float average;
private Date date;

public Day(Date date, int deaths, float average) {
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

public Date getDate() {
	return date;
}

public void setDeaths(int deaths) {
	this.deaths = deaths;
}

public void setAverage(float average) {
	this.average = average;
}

public void setDate(Date date) {
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
