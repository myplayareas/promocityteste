package ufc.cmu.promocity.backend.model;

import java.util.Iterator;
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

	/**
	 * Percorre a lista de cupons até encontrar o cupom do idCoupon
	 * @param idCoupon do cupom pesquisado
	 * @return Coupon encontrado
	 */
	public Coupon getCouponFromCouponList(Long idCoupon) {
		for (Iterator<Coupon> iterator = this.getCouponList().iterator(); iterator.hasNext();) {
			Coupon coupon = (Coupon) iterator.next();
			if (coupon.getId() == idCoupon) {
				return coupon;
			}
		}
		return null;
	}
			
}
