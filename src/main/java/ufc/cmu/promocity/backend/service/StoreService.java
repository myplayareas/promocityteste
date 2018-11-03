package ufc.cmu.promocity.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import ufc.cmu.promocity.backend.context.PromotionArea;
import ufc.cmu.promocity.backend.model.Coupon;
import ufc.cmu.promocity.backend.model.Promotion;
import ufc.cmu.promocity.backend.model.Store;
import ufc.cmu.promocity.backend.service.exceptions.StoreNotFoundException;
import ufc.cmu.promocity.backend.service.interfaces.IStoreService;
import ufc.cmu.promocity.backend.utils.geographic.GPSPoint;

/**
 * Class the manipulate the repository of Stores
 * @author armandosoaressousa
 *
 */
@Service
public class StoreService implements IStoreService{
	private static AtomicLong contador = new AtomicLong(0);
	private final Map<Long, Store> stores;
	public PromotionArea globalPromotionArea;
	
	public StoreService() {
		this.stores = new HashMap<Long, Store>();
		this.globalPromotionArea = PromotionArea.getInstance();
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
		this.globalPromotionArea.addStoresAreas(Store);
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
		//promotionAux.addCoupon(coupon);
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
		
		return null;//storeAux.getPromotionFromPromotionList(idPromotion).getCouponList();
	}
	
	public Coupon getCouponFromPromotionAndStore(long idStore, long idPromotion, long idCoupon) {
		//find a store with idStore in list of stores
		Store storeAux = this.stores.get(idStore);
		
		//Find a promotion in a list of promotions
		//Promotion promotionAux = storeAux.getPromotionFromPromotionList(idPromotion);
		
		return null;//promotionAux.getCouponFromCouponList(idCoupon);
	}
	
	public void createTestStores() {
		Promotion p1 = new Promotion();
		p1.setId(Long.valueOf(1));
		p1.setDescription("Promocao 1");
		Date d1 = new Date();
		Date d2 = new Date();
		p1.setFromDate(d1);
		p1.setToDate(d2);
		
		Coupon coupon1 = new Coupon();
		coupon1.setId(Long.valueOf(1));
		coupon1.setDescription("Coupon 1");
		coupon1.setQrCode("123456789");
		coupon1.setDiscount(10);
		//p1.addCoupon(coupon1);
		
		Store s1 = new Store();
		s1.setId(Long.valueOf(1));
		s1.setName("Store 1");
		s1.setAddress("add 1");
		s1.setCity("City 1");
		s1.setState("State 1");
		GPSPoint location1 = new GPSPoint(0,0);
		s1.setLocation(location1);
		s1.addPromotion(p1);

		Promotion p2 = new Promotion();
		p2.setId(Long.valueOf(2));
		p2.setDescription("Promo 2");
		Date d3 = new Date();
		Date d4 = new Date();
		p2.setFromDate(d3);
		p2.setToDate(d4);
		
		Coupon coupon2 = new Coupon();
		coupon2.setId(Long.valueOf(2));
		coupon2.setDescription("Coupon 2");
		coupon2.setQrCode("12345678910");
		coupon2.setDiscount(20);
		//p2.addCoupon(coupon2);
		
		Store s2 = new Store();
		s2.setId(Long.valueOf(2));
		s2.setName("Store 2");
		s2.setAddress("add 2");
		s2.setCity("City 2");
		s2.setState("State 2");
		GPSPoint location2 = new GPSPoint(0,0);
		s2.setLocation(location2);
		
		s2.addPromotion(p2);

		Promotion p3 = new Promotion();
		p3.setId(Long.valueOf(3));
		p3.setDescription("Promo 3");
		Date d5 = new Date();
		Date d6 = new Date();
		p3.setFromDate(d5);
		p3.setToDate(d6);
		
		Coupon coupon3 = new Coupon();
		coupon3.setId(Long.valueOf(3));
		coupon3.setDescription("Coupon 3");
		coupon3.setQrCode("12345678911");
		coupon3.setDiscount(30);
		//p3.addCoupon(coupon3);
		
		s2.addPromotion(p3);

		this.addStore(s1);
		this.addStore(s2);
	}
	
}