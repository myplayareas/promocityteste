package ufc.cmu.promocity.backend.controller;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import ufc.cmu.promocity.backend.model.Coupon;
import ufc.cmu.promocity.backend.model.Promotion;
import ufc.cmu.promocity.backend.model.Store;
import ufc.cmu.promocity.backend.service.StoreService;

/**
 * Stores Controller
 * @author armandosoaressousa
 */
@Component
@Path("/stores")
public class StoreController {
	private StoreService storeService;
	
	/**
	 * Contrutor of StoreController
	 * @param StoreService
	 */
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}

	 /**
     * Retorna em um JSON todos as lojas cadastrados
     * @return lista de lojas
     */
    @GET
    @Produces("application/json")
    public List<Store> getAllStores() {
        return storeService.getListAll();
    }
    
    /**
     * Dado um id de uma loja retorna em JSON dos dados da loja
     * @param id da loja
     * @return dados da loja
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Store getStore(@PathParam("id") String id) {
    	return storeService.get(Long.parseLong(id));
    }
    
    /**
     * Dados os dados de uma loja faz seu registro no repositorio 
     * @param Store
     * @return codigo http
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response addStore(Store Store) {
        storeService.save(Store);
        URI uri = URI.create("/" + String.valueOf(Store.getId()));
		return Response.created(uri).build();
    }
    
    /**
     * Dado um id e os dados de uma loja faz sua atualizacao no repositorio
     * @param id da loja
     * @param Store dados da loja
     * @return codigo http
     */
    @PUT
    @Consumes("application/json")
    @Path("/{id}")
    public Response updateStore(@PathParam("id") String id, Store Store) {
        storeService.update(Store);
        return Response.noContent().build();
    }
    
    /**
     * Dado um id de uma loja faz sua remocao do repositorio
     * @param id da loja
     * @return codigo http
     */
    @DELETE
    @Path("/{id}")
    public Response deleteStore(@PathParam("id") String id) {
        storeService.delete(Long.parseLong(id));
        return Response.ok().build();
    }
    
    /**
     * Get all promotions from specific idStore
		stores/id/promotions
 
     * Retorna em um JSON todas as promocoes de uma dada lojas cadastrados
     * id da loja
     * @return lista de promocoes de uma dada loja
     */
    @GET
    @Produces("application/json")
    @Path("/{id}/promotions")
    public List<Promotion> getAllPromotionsfromStore(@PathParam("id") String id) {
        return storeService.get(Long.parseLong(id)).getPromotionList();
    }

    /**
     * Get a specific idPromotion from specific idStore
	stores/id/promotions/id

     * Return data from promotion in store
     * @param idStore da loja
     * @param idPromotion da promocao
     * @return dados da promocao especifica
     */
    @GET
    @Produces("application/json")
    @Path("/{idStore}/promotions/{idPromotion}")
    public Promotion getPromotionFromStore(@PathParam("idStore") String idStore, @PathParam("idPromotion") String idPromotion) {
    	Store store = storeService.get(Long.parseLong(idStore));
    	
    	for (Promotion element : store.getPromotionList()) {
    		if (element.getId() == Long.parseLong(idPromotion)) {
    			return element;
    		}
    	}
    	
    	return null;
    }
    
    /**
    Get all coupons from a specific idPromotion from specific idStore
    stores/id/promotions/id/coupons
	
     * Retorna em um JSON todos os cupons de uma data promocao em uma da loja
     * id da loja
     * id da promocao
     * @return lista de todos os cupons de uma promocao de uma loja
     */
    @GET
    @Produces("application/json")
    @Path("/{idStore}/promotions/{idPromotion}/coupons")
    public List<Coupon> getCouponsFromPromotionAndStore(@PathParam("idStore") String idStore, @PathParam("idPromotion") String idPromotion) {
    	Store store = storeService.get(Long.parseLong(idStore));
    	Promotion promotion = null; 
    	
    	for (Promotion element : store.getPromotionList()) {
    		if (element.getId() == Long.parseLong(idPromotion)) {
    			promotion = element;
    		}
    	}
    	
    	return promotion.getCoupons();
    }

    /**
    Get a specific coupon from a specific idPromotion and specific idStore
    stores/id/promotions/id/coupons/id
	
     * Retorna em um JSON todos os cupons de uma data promocao em uma da loja
     * id da loja
     * id da promoca
     * @return um cupom de uma dada promocao de uma dada loja
     */
    @GET
    @Produces("application/json")
    @Path("/{idStore}/promotions/{idPromotion}/coupons/{idCoupon}")
    public Coupon getCouponFromPromotionAndStore(@PathParam("idStore") String idStore, @PathParam("idPromotion") String idPromotion, @PathParam("idCoupon") String idCoupon) {
    	Store store = storeService.get(Long.parseLong(idStore));
    	Promotion promotion = null; 
    	
    	for (Promotion element : store.getPromotionList()) {
    		if (element.getId() == Long.parseLong(idPromotion)) {
    			promotion = element;
    		}
    	}
    	   	
    	for (Coupon element : promotion.getCoupons()) {
    		if (element.getId() == Long.parseLong(idCoupon)) {
    			return element;
    		}
    	}
    	
    	return null;
    }

}