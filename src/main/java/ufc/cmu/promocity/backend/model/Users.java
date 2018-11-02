package ufc.cmu.promocity.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.annotations.Type;

import ufc.cmu.promocity.backend.model.AbstractModel;

/**
 * Classe modelo de Usuário
 * @author armandosoaressousa
 *
 */
@Entity
public class Users extends AbstractModel<Long>{
	
	@Column(length=50)
	private String username;
	@Column(length=255)
	private String password;
	@Column(nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enabled;
	
	private String email;
	//private GPSPoint location;
	private double latitude;
	private double longitude;
	
	public Users() {
	}
	
	public Users(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getId() {
		Long id;
		id = super.getId();
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

}