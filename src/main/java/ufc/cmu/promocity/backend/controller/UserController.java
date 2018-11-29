package ufc.cmu.promocity.backend.controller;

import ufc.cmu.promocity.backend.context.PromotionArea;
import ufc.cmu.promocity.backend.context.UserLocationMonitoring;
import ufc.cmu.promocity.backend.model.Coupon;
import ufc.cmu.promocity.backend.model.Promotion;
import ufc.cmu.promocity.backend.model.Store;
import ufc.cmu.promocity.backend.model.Users;
import ufc.cmu.promocity.backend.report.ReportCoupon;
import ufc.cmu.promocity.backend.service.CouponsService;
import ufc.cmu.promocity.backend.service.MyStoresService;
import ufc.cmu.promocity.backend.service.StoreService;
import ufc.cmu.promocity.backend.service.UsersService;
import ufc.cmu.promocity.backend.utils.GeradorSenha;
import ufc.cmu.promocity.backend.utils.Message;

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


/**
 * Users Controller
 * @author armandosoaressousa
 *
 */
@Component
@Path("/users")
public class UserController {
	private UsersService userService;
	private UserLocationMonitoring userLocationMonitoring;
	public PromotionArea globalPromotionArea;
	private StoreService storeService;	
	private MyStoresService myStoresService;	
	private CouponsService couponService;
	
	@Autowired
	public void setCouponService(CouponsService couponServices){
		this.couponService = couponServices;
	}

	@Autowired
	public void setMyStoresService(MyStoresService myStoresService) {
	 		this.myStoresService = myStoresService;
	}
	
	@Autowired
	public void setUserService(UsersService userServices){
		this.userService = userServices;
	}
	
	@Autowired
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	
	/**
	 * Contrutor of UserController
	 * @param userService
	 */
	public UserController() {
	    this.userLocationMonitoring = new UserLocationMonitoring(globalPromotionArea);	   
	}

	 /**
     * Retorna em um JSON todos os usuarios cadastrados
     * @return código http
     */
    @GET
    @Produces("application/json")
    public List<Users> getAllUsers() {
       	List<Users> listUsers = new LinkedList<Users>();
    	listUsers = userService.getListAll();
    	return listUsers;
    }
    
    /**
     * Dado um id retorna o JSON dos dados do usuario
     * @param id
     * @return código http
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Users getUser(@PathParam("id") String id) {
    	return userService.get(Long.parseLong(id));
    }
    
    /**
     * Dados os dados de um usuario adiciona um usuario no repositorio
     * @param user
     * @return código http
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response addUser(Users user) {
        userService.save(user);
        URI uri = URI.create("/" + String.valueOf(user.getId()));
		return Response.created(uri).build();
    }
    
    /**
     * Dado um id e os dados do user faz sua atualizacao
     * @param id
     * @param user
     * @return código http
     */
    @PUT
    @Consumes("application/json")
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id, Users user) {
       userService.save(user);
       return Response.noContent().build();
    }

    /**
     * ../1/monitoring/location/0/0
     * exemplo dados enviados em forma de json: 
     * {
  		"id": 1,
  		"latitude": 0,
  		"longitude": 0
		}
     * Dado um id, latitude e longitude de um usuário envia sua localização instantanea
     * @param id
     * @param latitude
     * @param longitude
     * @return código http
     */
    @GET
    @Produces("application/json")
    @Path("{id}/monitoring/location/{latitude}/{longitude}")
    public List<ReportCoupon> monitoringUserLocation(@PathParam("id") String id, @PathParam("latitude") String latitude, @PathParam("longitude") String longitude) {
    	List<Coupon> couponList = new LinkedList<Coupon>();
    	List<ReportCoupon> cuponsDetalhados = new LinkedList<ReportCoupon>();
    	
    	Users user = userService.get(Long.parseLong(id));
    	user.setLatitude(Double.valueOf(latitude));
    	user.setLongitude(Double.valueOf(longitude));
    	
	    this.globalPromotionArea = PromotionArea.getInstance();
	    this.globalPromotionArea.setStoreAreasRegistered(this.storeService.getListAll());
	    this.userLocationMonitoring.setPromotionArea(globalPromotionArea);
    	
	    userLocationMonitoring.checkUserContext(user);
        
	    couponList = userLocationMonitoring.getListaDeCuponsColetados();
	    cuponsDetalhados = userLocationMonitoring.getListaDeReportCupomColetados();
	            
        if (couponList.size() > 0) {
        	for (Coupon cupom : couponList) {
        		user.addCoupon(cupom);
        	}        	
            userService.save(user);
            return cuponsDetalhados;
        }
        else {
        	return null;
        }        
    }

    /**
     * Dado um id de um usuario faz sua remocao do repositorio
     * @param id
     * @return código http
     */
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        userService.delete(Long.parseLong(id));
        return Response.ok().build();
    }
    
    /**
     * Get all coupons from specific idUser
		users/id/coupons
 
     * Retorna em um JSON todas os cupons de um dado user cadastrado
     * id do user
     * @return lista de cupons de um usuario
     */
    @GET
    @Produces("application/json")
    @Path("/{id}/coupons")
    public List<Coupon> getAllCouponsfromUser(@PathParam("id") String id) {
        return userService.get(Long.parseLong(id)).getCouponList();
    }

    /**
     * Get a specific coupon from specific user
	users/idUser/coupons/idCoupon

     * Return data from coupon in user
     * @param idUser 
     * @param idPCoupon
     * @return dados do coupon especifico
     */
    @GET
    @Produces("application/json")
    @Path("/{idUser}/promotions/{idCoupon}")
    public Coupon getCouponFromUser(@PathParam("idUser") String idUser, @PathParam("idCoupon") String idCoupon) {
    	Users users = userService.get(Long.parseLong(idUser));
    	
    	for (Coupon element : users.getCouponList()) {
    		if (element.getId() == Long.parseLong(idCoupon)) {
    			return element;
    		}
    	}
    	
    	return null;
    }
    
    /**
     * Dado email e senha retorna o JSON dos dados do usuario
     * @param 
     * @return código http
     */
    @GET
    @Produces("application/json")
    @Path("/{email}/{senha}")
    public Object getUserAutenticado(@PathParam("email") String email,@PathParam("senha") String senha) {
    	Users user = userService.getUserByEmail(email);
    	Message message = new Message();
    	
    	//consulta o usuário por email e se existe retorna os dados do usuário
    	if (user != null) { //usuário existe
    		boolean checaSenha = new GeradorSenha().comparaSenhas(senha, user.getPassword());
        	if (senha.length() >0 && checaSenha){        		
                return user;	
        	}else{        		
        		message.setId(1);
        		message.setConteudo("Senha incorreta!");
                return message;	    		
        	}    	
    		
    	}else {
    		message.setId(2);
    		message.setConteudo("Usuário não existe!");
    		return message;
    	}    	
    }

    /**
     * Adiciona um amigo de forma birecional
     * @param idUser
     * @param idFriend
     * @return
     */
    @GET
    @Produces("application/json")
    @Path(value = "/{idUser}/add/friend/{idFriend}")
    public Object addFriend(@PathParam("idUser") long idUser, @PathParam("idFriend") long idFriend) {
    	Message message = new Message();
    	
    	Users user = this.userService.get(idUser);
    	Users friend = this.userService.get(idFriend);
    	
    	if (user.addIdFriend(friend)) {
    		this.userService.save(user);
    		if (friend.addIdFriend(user)){
    			this.userService.save(friend);	
    		}    
    		message.setId(3);
    		message.setConteudo("O amigo foi salvo com sucesso.");
    	}else {
    		message.setId(4);
    		message.setConteudo("O amigo já existe!!!!.");
    	}
    	
    	return message;	
    }
    
    /**
     * Dado um usuário logado lista os amigos dele
     * @param idUser
     * @param model
     * @return
     */
    @GET
    @Produces("application/json")
    @Path(value = "/{idUser}/list/friends")
    public List<Users> listFriends(@PathParam("idUser") long idUser) {    

		Users user = this.userService.get(idUser);
		List<Users> idFriends = user.getIdFriendsList();
		
		List<Users> listaAmigos = new LinkedList<Users>();
		
		for (Users id : idFriends) {
			listaAmigos.add(this.userService.get(id.getId()));
		}
        
        return listaAmigos;
    }
    
    /**
     * Dado um usuário logado, ele remove o amigo selecionado
     * @param idUser
     * @param idFriend
     * @param model
     * @param ra
     * @return
     */
    @GET
    @Produces("application/json")
    @Path(value = "/{idUser}/delete/friend/{idFriend}")
    public Object deleteFriend(@PathParam("idUser") long idUser, @PathParam("idFriend") long idFriend) {
    	Message message = new Message();
    	
    	Users user = this.userService.get(idUser);
    	Users friend = this.userService.get(idFriend);
    	
    	if (user.deleteFriend(friend)) {        	 
        	this.userService.save(user);
        	if(friend.deleteFriend(user)) {
        		this.userService.save(friend);
        	}
        	message.setId(5);
        	message.setConteudo("Amigo removido com sucesso!");
    	}else {
        	message.setId(6);
        	message.setConteudo("O amigo não foi removido.");
    	}
    	
    	return message;
    }
    
    /**
     * Ativa um cupom para os 3 amigos que tiverem o mesmo cupom e estivem na proximidade da loja
     * @param idUser
     * @param idCoupon
     * @param idStore
     * @param idFriend1
     * @param idFriend2
     * @return
     */
    @GET
    @Produces("application/json")
    @Path(value="/{idUser}/activate/coupon/{idCoupon}/store/{idStore}/friends/{idFriend1}/{idFriend2}")
    public Object activeCouponFriends(@PathParam("idUser") long idUser, @PathParam("idCoupon") long idCoupon, @PathParam("idStore") long idStore, 
    		@PathParam("idFriend1") long idFriend1, @PathParam("idFriend2") long idFriend2) {
    	
    	Users user = this.userService.get(idUser);
    	Users friend1 = this.userService.get(idFriend1);
    	Users friend2 = this.userService.get(idFriend2);
    	Store store = this.storeService.get(idStore);
    	Coupon myCoupon = this.couponService.get(idCoupon);
    	    	
    	return checkRulesActivateCoupon(user, friend1, friend2, store, myCoupon);
    }

    /**
     * Checa as regras de validação de cupom por 3 amigos
     * @param message
     * @param user
     * @param friend1
     * @param friend2
     * @param store
     * @param myCoupon
     * @return
     */
	private Object checkRulesActivateCoupon(Users user, Users friend1, Users friend2, Store store, Coupon myCoupon) {
		Message message = new Message();
		
		//1. Checa validade do cupom idCoupon
    	if (isValidCoupon(myCoupon)) {
    		//2. Checa se Friend1 e Friend2 são amigos de user
    		//3. Checa se Friend1 tem Coupon
    		//4. Checa se Friend1 está na vizinhança de Store	
    		//5. Checa se Friend2 tem Coupon			
    		//6. Checa se Friend2 está na vizinhança de Store
    		if (user.alreadyFriend(friend1) && user.alreadyFriend(friend2) &&
    				friend1.alreadyCoupon(myCoupon) && isUserNearByStore(friend1, store) && 
    				friend2.alreadyCoupon(myCoupon) && isUserNearByStore(friend2, store)) {
        		//7. Ativa idCoupon para User, Friend1 e Friend2 com o dobro do desconto original
    			float descontoOriginal = myCoupon.getDiscount();
    			myCoupon.setDiscount(descontoOriginal*2);
    			myCoupon.setActivated(true);
    			this.couponService.save(myCoupon);
        		message.setId(7);
        		message.setConteudo("Você e seus amigos " + friend1.getId() + ", " + friend2.getId() + ", ativaram o cupom " + myCoupon.getId() +" com sucesso!");
    		}else {
        		message.setId(8);
        		message.setConteudo("Seus amigos " + friend1.getId() + ", " + friend2.getId() + ", não estão na proximidade necessária para ativar o cupom");
    		}
    	}else {
    		message.setId(9);
    		message.setConteudo("O coupon "+ myCoupon.getId() + " não é válido." );
    	}
    	return message;
	}
 
    /**
     * Checa se um cupom é valido
     * @param IdCoupon
     * @return
     */
    public boolean isValidCoupon(Coupon coupon) {
    	boolean valid=false;    	    	    	
    	Coupon myCoupon = this.couponService.get(coupon.getId());
    	
    	if (myCoupon != null) {
    		valid = true;
    	}else {
    		valid = false;
    	}
    	
    	return valid;
    }
    
    /**
     * Checa se o usuário está nas proximidades da loja dada
     * @param idUser
     * @param idStore
     * @return
     */
    public boolean isUserNearByStore(Users user, Store store) {
    	boolean valid=false;    	
    	double distance = new UserLocationMonitoring(null).checkDistanceFromStore(user, store);
    	double radius = new UserLocationMonitoring(null).getRadius();
    	
    	if (distance <= radius) {
    		valid = true;
    	}else {
    		valid = false;
    	}
    	
    	return valid;
    }
    
    
    
}