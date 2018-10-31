package ufc.cmu.promocity.backend.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import ufc.cmu.promocity.backend.model.Coupon;
import ufc.cmu.promocity.backend.model.Promotion;
import ufc.cmu.promocity.backend.service.exceptions.PromotionNotFoundException;
import ufc.cmu.promocity.backend.service.interfaces.IPromotionService;

/**
 * Class the manipulate the repository of Promotions
 * @author armandosoaressousa
 *
 */
@Service
public class PromotionService implements IPromotionService{
	private static AtomicLong contador = new AtomicLong(0);
	private final Map<Long, Promotion> promotions;
	
	public PromotionService() {
		this.promotions = new HashMap<Long, Promotion>();
	}

	/**
	 * Returns all Promotions in repository
	 * @return List of Promotions
	 */
	public List<Promotion> getAllPromotions() {
		List<Promotion> lista = new LinkedList<Promotion>();
		
		for (Promotion element : promotions.values()) {
			lista.add(element);
		}
		
		return lista;
	}
	
	/**
	 * get Promotion by id
	 * @param id
	 * @return Promotion
	 */
	public Promotion getPromotion(Long id) {
		return this.promotions.get(id);
	}
	
	/**
	 * Add a new Promotion
	 * @param Promotion
	 */
	public void addPromotion(Promotion Promotion) {
		Long id = contador.incrementAndGet();
		Promotion.setId(id);
		promotions.put(id, Promotion);
	}
	
	/**
	 * Replace a new Promotion by id
	 * @param id
	 * @param newPromotion
	 */
	public void updatePromotion(Long id, Promotion newPromotion) {
		promotions.replace(id, newPromotion);
	}
	
	/**
	 * Delete a Promotion by id
	 * @param id
	 * @return 
	 */
	public Promotion deletePromotion(Long id) {
		if (!promotions.containsKey(id)) {
			throw new PromotionNotFoundException("Can't delete Promotion. Promotion for id: " + id + " not found");
		}
		return promotions.remove(id);
	}
	
	public void addCoupon(Long idPromotion, Coupon coupon) {
		Promotion p = this.promotions.get(idPromotion);
		p.addCoupon(coupon);
		this.updatePromotion(idPromotion, p);
	}
	
	public void createTestPromotions() {
		Promotion p1 = new Promotion(Long.valueOf(1), "", null); 
		this.addPromotion(p1);
	}
	
}