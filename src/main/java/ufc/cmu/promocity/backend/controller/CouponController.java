package ufc.cmu.promocity.backend.controller;

import ufc.cmu.promocity.backend.context.PromotionArea;
import ufc.cmu.promocity.backend.model.Coupon;
import ufc.cmu.promocity.backend.model.Promotion;
import ufc.cmu.promocity.backend.model.Store;
import ufc.cmu.promocity.backend.model.Users;
import ufc.cmu.promocity.backend.report.ReportCoupon;
import ufc.cmu.promocity.backend.service.CouponsService;
import ufc.cmu.promocity.backend.service.PromotionsService;
import ufc.cmu.promocity.backend.service.StoreService;
import ufc.cmu.promocity.backend.service.UsersService;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Coupons Controller
 * @author armandosoaressousa
 *
 */
@Component
@Path("/coupons")
public class CouponController {
	private CouponsService couponService;
	private UsersService userService;
	private StoreService storeService;
	private PromotionsService promotionService;
	
	@Autowired
	public void setCouponService(CouponsService couponServices){
		this.couponService = couponServices;
	}
	
	@Autowired
	public void setUserService(UsersService userServices){
		this.userService = userServices;
	}

	@Autowired
	public void StoreController(StoreService storeService) {
		this.storeService = storeService;
	}

	@Autowired
	public void setPromotionService(PromotionsService promotionServices){
		this.promotionService = promotionServices;
	}

	
	/**
	 * Contrutor of couponController
	 * @param couponService
	 */
	public CouponController() {
	}

	 /**
     * Retorna em um JSON todos os cupons cadastrados
     * @return código http
     */
    @GET
    @Produces("application/json")
    public List<Coupon> getAllCoupons() {
       	List<Coupon> listCoupon = new LinkedList<Coupon>();
    	listCoupon = couponService.getListAll();
    	return listCoupon;
    }
    
    /**
     * Dado um id retorna o JSON dos dados do cupom
     * @param id
     * @return código http
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Coupon getCoupon(@PathParam("id") String id) {
    	return couponService.get(Long.parseLong(id));
    }
    
    /**
     * Dados os dados de um usuario adiciona um cupom no repositorio
     * @param coupon
     * @return código http
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response addCoupon(Coupon coupon) {
        couponService.save(coupon);
        URI uri = URI.create("/" + String.valueOf(coupon.getId()));
		return Response.created(uri).build();
    }
    
    /**
     * Dado um id e os dados do coupon faz sua atualizacao
     * @param id
     * @param coupon
     * @return código http
     */
    @PUT
    @Consumes("application/json")
    @Path("/{id}")
    public Response updateCoupon(@PathParam("id") String id, Coupon coupon) {
       couponService.save(coupon);
       return Response.noContent().build();
    }
    
    /**
     * Dado um id de um cupom faz sua remocao do repositorio
     * @param id
     * @return código http
     */
    @DELETE
    @Path("/{id}")
    public Response deletecoupon(@PathParam("id") String id) {
        couponService.delete(Long.parseLong(id));
        return Response.ok().build();
    }
 
    /**
     * Dado um id de um cupom retorna o JSON dos usuários 
     * @param id
     * @return código http
     */
    @GET
    @Produces("application/json")
    @Path("/{id}/users")
    public List<Users> getUsersCoupon(@PathParam("id") String id) {
    	Coupon myCoupon =  couponService.get(Long.parseLong(id));
    	
    	List<Users> myUsers = myCoupon.getUsers();
    	
    	return myUsers; 
    }

    /**
     * Dado um id de um cupom, id de loja e de id promocao retorna o JSON dos usuários 
     * @param id
     * @return código http
     */
    @GET
    @Produces("application/json")
    @Path("/basic/{id}/promotion/{idPromotion}/store/{idStore}")
    public ReportCoupon getBasicCoupon(@PathParam("id") String id, @PathParam("idPromotion") String idPromotion, @PathParam("idStore") String idStore) {
    	ReportCoupon basicCoupon=null;
    	Coupon myCoupon =  couponService.get(Long.parseLong(id));
    	Promotion myPromotion = promotionService.get(Long.parseLong(idPromotion));
    	Store myStore = storeService.get(Long.parseLong(idStore));
    	
    	boolean myStoreExist=false;
    	boolean myPromotionExist=false;
    	boolean myCouponExist=false;
    	
    	if (myStore != null) {
    		myStoreExist = true;
    		for (Promotion promotion : myStore.getPromotionList()) {
    			if (promotion.getId() == myPromotion.getId()) {
    				myPromotionExist = true;
    				for (Coupon coupon : myPromotion.getCoupons()) {
    					if (coupon.getId() == myCoupon.getId()) {
    						myCouponExist = true;
    						break;
    					}
    				}
    				break;
    			}
    		}
    	}
    	
    	if (myStoreExist && myPromotionExist && myCouponExist) {
    		basicCoupon = new ReportCoupon(myStore, myPromotion, myCoupon);
    	}
    	    	
    	return basicCoupon; 
    }

    
}