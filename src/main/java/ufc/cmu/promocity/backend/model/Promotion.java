package ufc.cmu.promocity.backend.model;

import java.util.LinkedList;
import java.util.List;

public class Promotion {
	private Long id;
	private String description;
	private List<Coupon> CouponList = new LinkedList<Coupon>();
	
	public Promotion() {
		
	}
	
	public Promotion(Long id, String description, List<Coupon> CouponList) {
		super();
		this.id = id;
		this.description = description;
		this.CouponList = CouponList;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Coupon> getCouponList() {
		return CouponList;
	}
	public void setCouponList(List<Coupon> couponList) {
		CouponList = couponList;
	}
	
	public void addCoupon(Coupon coupon) {
		this.CouponList.add(coupon);
	}
}
