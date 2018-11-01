package ufc.cmu.promocity.backend.model;

import ufc.cmu.promocity.backend.utils.geographic.GPSPoint;

public class User {
	private Long id;
	private String name;
	private String password;
	private String email;
	private GPSPoint location;
	
	public User() {
	}
	
	public User(Long id, String name, String password, String email, GPSPoint location) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.location = location;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public GPSPoint getLocation() {
		return location;
	}

	public void setLocation(GPSPoint location) {
		this.location = location;
	}

}