package ufc.cmu.promocity.backend.model;

import java.util.LinkedList;
import java.util.List;

public class Store {
	private Long id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String location; //TODO manipulate google geoposition
	private List<Promotion> PromotionList = new LinkedList<Promotion>();
	
	public Store() {
		
	}
	
	public Store(Long id, String name, String address, String city, String state, String location, List<Promotion> promotionList) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.location = location;
		this.PromotionList = promotionList;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public List<Promotion> getPromotionList() {
		return PromotionList;
	}
	public void setPromotionList(List<Promotion> promotionList) {
		PromotionList = promotionList;
	}

	public void addPromotion(Promotion promotion) {
		this.PromotionList.add(promotion);		
	}

}