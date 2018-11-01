package ufc.cmu.promocity.backend.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ufc.cmu.promocity.backend.utils.geographic.GPSPoint;

public class Store {
	private Long id;
	private String name;
	private String address;
	private String city;
	private String state;
	private GPSPoint location;
	private double radius;
	private List<Promotion> PromotionList = new LinkedList<Promotion>();
	
	public Store() {
		
	}
	
	public Store(Long id, String name, String address, String city, String state, GPSPoint location, List<Promotion> promotionList) {
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
	public GPSPoint getLocation() {
		return location;
	}
	public void setLocation(GPSPoint location) {
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
	
	/**
	 * Percorre a lista de promocoes até encontrar a promocao com idPromocao
	 * @param idPromocao promocao pesquisada
	 * @return promocao encontrada
	 */
	public Promotion getPromotionFromPromotionList(Long idPromotion) {
		for (Iterator<Promotion> iterator = this.getPromotionList().iterator(); iterator.hasNext();) {
			Promotion promotion = (Promotion)iterator.next();
			if (promotion.getId()==idPromotion) {
				return promotion;
			}
		}
		return null;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
}