package ufc.cmu.promocity.backend.report;

import java.util.LinkedList;
import java.util.List;

import ufc.cmu.promocity.backend.model.Promotion;
import ufc.cmu.promocity.backend.model.Store;

public class ReportStore {
	private Long id;
	private String name;
	private String address;
	private String state;
	private double radius;
	private String city;
	private double latitude;
	private double longitude;	
	private List<Long> idPromotions = new LinkedList<Long>();
		
	public ReportStore(Store myStore) {
		this.id = myStore.getId();
		this.name = myStore.getName();
		this.address = myStore.getAddress();
		this.state = myStore.getState();
		this.radius = myStore.getRadius(); 
		this.city = myStore.getCity();
		this.latitude = myStore.getLatitude();
		this.longitude = myStore.getLongitude();
		
		List<Promotion> promotions = new LinkedList<Promotion>();
		promotions = myStore.getPromotionList();
		
		for (Promotion promotion : promotions) {
			idPromotions.add(promotion.getId());
		}
		
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public double getRadius() {
		return radius;
	}


	public void setRadius(double radius) {
		this.radius = radius;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
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


	public List<Long> getIdPromotions() {
		return idPromotions;
	}


	public void setIdPromotions(List<Long> idPromotions) {
		this.idPromotions = idPromotions;
	}

}
