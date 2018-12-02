package ufc.cmu.promocity.backend.report;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import ufc.cmu.promocity.backend.model.Coupon;
import ufc.cmu.promocity.backend.model.Users;

public class ReportUser {
	private Long id;
	private String username;
	private String password;
	private String email;
	private double latitude;
	private double longitude;
	private boolean enabled;
	private int amountOfCoupons;
	private int amountOfFriends;
	private List<Long> idCoupons = new LinkedList<Long>();
	private List<Long> idFriends = new LinkedList<Long>();
			
	public ReportUser(Users user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.latitude = user.getLatitude();
		this.longitude = user.getLongitude();
		this.enabled = user.isEnabled();
		this.amountOfCoupons = user.getAmountOfCoupons();
		this.amountOfFriends = user.getAmountOfFriends();
		
		List<Coupon> coupons = new LinkedList<Coupon>();
		List<Users> friends = new LinkedList<Users>();		
		coupons = user.getCouponList();
		friends = user.getIdFriendsList();
		
		for (Coupon coupon : coupons) {
			this.idCoupons.add(coupon.getId());
		}
		
		for (Users myUser : friends) {
			this.idFriends.add(myUser.getId());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getAmountOfCoupons() {
		return amountOfCoupons;
	}

	public void setAmountOfCoupons(int amountOfCoupons) {
		this.amountOfCoupons = amountOfCoupons;
	}

	public int getAmountOfFriends() {
		return amountOfFriends;
	}

	public void setAmountOfFriends(int amountOfFriends) {
		this.amountOfFriends = amountOfFriends;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Long> getIdCoupons() {
		return idCoupons;
	}

	public void setIdCoupons(List<Long> idCoupons) {
		this.idCoupons = idCoupons;
	}

	public List<Long> getIdFriends() {
		return idFriends;
	}

	public void setIdFriends(List<Long> idFriends) {
		this.idFriends = idFriends;
	}

}
