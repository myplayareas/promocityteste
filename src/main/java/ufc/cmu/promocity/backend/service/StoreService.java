package ufc.cmu.promocity.backend.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import ufc.cmu.promocity.backend.model.Coupon;
import ufc.cmu.promocity.backend.model.Promotion;
import ufc.cmu.promocity.backend.model.Store;
import ufc.cmu.promocity.backend.service.exceptions.StoreNotFoundException;
import ufc.cmu.promocity.backend.service.interfaces.IStoreService;

/**
 * Class the manipulate the repository of Stores
 * @author armandosoaressousa
 *
 */
@Service
public class StoreService implements IStoreService{
	private static AtomicLong contador = new AtomicLong(0);
	private final Map<Long, Store> stores;
	
	public StoreService() {
		this.stores = new HashMap<Long, Store>();
	}

	/**
	 * Returns all Stores in repository
	 * @return List of Stores
	 */
	public List<Store> getAllStores() {
		List<Store> lista = new LinkedList<Store>();
		
		for (Store element : stores.values()) {
			lista.add(element);
		}
		
		return lista;
	}
	
	/**
	 * get Store by id
	 * @param id
	 * @return Store
	 */
	public Store getStore(Long id) {
		return this.stores.get(id);
	}
	
	/**
	 * Add a new Store
	 * @param Store
	 */
	public void addStore(Store Store) {
		Long id = contador.incrementAndGet();
		Store.setId(id);
		stores.put(id, Store);
	}
	
	/**
	 * Replace a new Store by id
	 * @param id
	 * @param newStore
	 */
	public void updateStore(Long id, Store newStore) {
		stores.replace(id, newStore);
	}
	
	/**
	 * Delete a Store by id
	 * @param id
	 * @return 
	 */
	public Store deleteStore(Long id) {
		if (!stores.containsKey(id)) {
			throw new StoreNotFoundException("Can't delete Store. Store for oid: " + id + " not found");
		}
		return stores.remove(id);
	}

	/**
	 * Add promotion in specific store
	 * @param idStore
	 * @param list
	 */
	public void addPromotion(Long idStore, Promotion promotion) {
		//find the store with idStore
		Store storeAux = this.stores.get(idStore);
		
		//Add promotion in store found
		storeAux.addPromotion(promotion);
		
		//update list of store with new store
		this.updateStore(idStore, storeAux);
	}
	
	/**
	 * Add a Coupon in Promotion from Store
	 * @param idStore
	 * @param idPromotion
	 * @param coupon
	 */
	public void addCoupon(Long idStore, Long idPromotion, Coupon coupon) {
		//find a store with idStore in list of stores
		Store storeAux = this.stores.get(idStore);
		
		//find a promotion with idPromotion in a specifc store
		Promotion promotionAux = storeAux.getPromotionFromPromotionList(idPromotion);
		
		//add coupon in promotion with idPromotion
		promotionAux.addCoupon(coupon);
		//TODO : FAZER A ATUALIZAÇÃO DA PROMOCAO NA LOJA ESPECIFICA
	}
	
	/**
	 * Retund all promotion from specifc store
	 * @param idStore
	 * @return list of promotion from specif store
	 */
	public List<Promotion> getAllPromotionOneStore(Long idStore){
		//find a store with idStore in list of stores
		Store storeAux = this.stores.get(idStore);
		
		return storeAux.getPromotionList();
	}
	
	
	public Promotion getPromotionFromStore(Long idStore, Long idPromotion) {
		//find a store with idStore in list of stores
		Store storeAux = this.stores.get(idStore);
				
		return storeAux.getPromotionFromPromotionList(idPromotion);
	}
	
	public List<Coupon> getAllCouponsFromPromotionAndStore(long idStore, long idPromotion) {
		//find a store with idStore in list of stores
		Store storeAux = this.stores.get(idStore);
		
		return storeAux.getPromotionFromPromotionList(idPromotion).getCouponList();
	}
	
	public Coupon getCouponFromPromotionAndStore(long idStore, long idPromotion, long idCoupon) {
		//find a store with idStore in list of stores
		Store storeAux = this.stores.get(idStore);
		
		//Find a promotion in a list of promotions
		Promotion promotionAux = storeAux.getPromotionFromPromotionList(idPromotion);
		
		return promotionAux.getCouponFromCouponList(idCoupon);
	}
	
	public void createTestStores() {
		Promotion p = new Promotion();
		p.setId(Long.valueOf(1));
		p.setDescription("Promocao 1");
		
		Coupon coupon = new Coupon();
		coupon.setId(Long.valueOf(1));
		coupon.setDescription("Coupon 1");
		coupon.setQrCode("123456789");
		p.addCoupon(coupon);
		
		Store s1 = new Store();
		s1.setId(Long.valueOf(1));
		s1.setName("Store 1");
		s1.setAddress("add 1");
		s1.setCity("City 1");
		s1.setState("State 1");
		s1.setLocation("Location 1");
		s1.addPromotion(p);

		Promotion p2 = new Promotion();
		p2.setId(Long.valueOf(2));
		p2.setDescription("Promo 2");
		
		Coupon coupon2 = new Coupon();
		coupon2.setId(Long.valueOf(2));
		coupon2.setDescription("Coupon 2");
		coupon2.setQrCode("12345678910");
		p2.addCoupon(coupon2);
		
		Store s2 = new Store();
		s2.setId(Long.valueOf(2));
		s2.setName("Store 2");
		s2.setAddress("add 2");
		s2.setCity("City 2");
		s2.setState("State 2");
		s2.setLocation("Location 2");
		s2.addPromotion(p2);

		Promotion p3 = new Promotion();
		p3.setId(Long.valueOf(3));
		p3.setDescription("Promo 3");
		
		Coupon coupon3 = new Coupon();
		coupon3.setId(Long.valueOf(3));
		coupon3.setDescription("Coupon 3");
		coupon3.setQrCode("12345678911");
		p3.addCoupon(coupon3);
		
		s2.addPromotion(p3);

		this.addStore(s1);
		this.addStore(s2);
	}
	
}