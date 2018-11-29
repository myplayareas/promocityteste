package ufc.cmu.promocity.backend.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Track extends AbstractModel<Long>{
	public double latitude;
	public double longitude;
	public Date day;

	public Track() {
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}
}