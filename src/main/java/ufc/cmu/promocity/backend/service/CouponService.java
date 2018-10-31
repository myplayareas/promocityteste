package ufc.cmu.promocity.backend.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import ufc.cmu.promocity.backend.model.*;
import ufc.cmu.promocity.backend.service.exceptions.CouponNotFoundException;
import ufc.cmu.promocity.backend.service.interfaces.ICouponService;

/**
 * Class the manipulate the repository of Coupons
 * @author armandosoaressousa
 *
 */
@Service
public class CouponService implements ICouponService{
	private static AtomicLong contador = new AtomicLong(0);
	private final Map<Long, Coupon> coupons;
	
	public CouponService() {
		this.coupons = new HashMap<Long, Coupon>();
	}

	/**
	 * Returns all Coupons in repository
	 * @return List of Coupons
	 */
	public List<Coupon> getAllCoupons() {
		List<Coupon> lista = new LinkedList<Coupon>();
		
		for (Coupon element : coupons.values()) {
			lista.add(element);
		}
		
		return lista;
	}
	
	/**
	 * get Coupon by id
	 * @param id
	 * @return Coupon
	 */
	public Coupon getCoupon(Long id) {
		return this.coupons.get(id);
	}
	
	/**
	 * Add a new Coupon
	 * @param Coupon
	 */
	public void addCoupon(Coupon Coupon) {
		Long id = contador.incrementAndGet();
		Coupon.setId(id);
		coupons.put(id, Coupon);
	}
	
	/**
	 * Replace a new Coupon by id
	 * @param id
	 * @param newCoupon
	 */
	public void updateCoupon(Long id, Coupon newCoupon) {
		coupons.replace(id, newCoupon);
	}
	
	/**
	 * Delete a Coupon by id
	 * @param id
	 * @return 
	 */
	public Coupon deleteCoupon(Long id) {
		if (!coupons.containsKey(id)) {
			throw new CouponNotFoundException("Can't delete Coupon. Coupon for oid: " + id + " not found");
		}
		return coupons.remove(id);
	}
	
	public void createTestCoupons() {
		Coupon c1 = new Coupon(Long.valueOf(1), "", ""); 
		this.addCoupon(c1);
	}
	
}