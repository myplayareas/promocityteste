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
		Store storeAux = this.stores.get(idStore);
		storeAux.addPromotion(promotion);
		this.updateStore(idStore, storeAux);
	}
	
	public void createTestStores() {
		Promotion p = new Promotion();
		p.setId(Long.valueOf(1));
		p.setDescription("Promo 1");
		
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
		
		this.addStore(s1);
	}
	
}