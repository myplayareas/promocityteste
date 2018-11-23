package ufc.cmu.promocity.backend.controller;

import ufc.cmu.promocity.backend.context.PromotionArea;
import ufc.cmu.promocity.backend.context.UserLocationMonitoring;
import ufc.cmu.promocity.backend.model.Coupon;
import ufc.cmu.promocity.backend.model.Promotion;
import ufc.cmu.promocity.backend.model.Users;
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
     * requisicao do servico via PUT
     * ../1/location/0/0
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
    @Path("{id}/location/{latitude}/{longitude}")
    public List<Promotion> updateUserLocation(@PathParam("id") String id, @PathParam("latitude") String latitude, @PathParam("longitude") String longitude) {
    	List<Coupon> couponList = new LinkedList<Coupon>();
    	List<Promotion> promotionList = new LinkedList<Promotion>();
    	List<Promotion> allPromotionsList = new LinkedList<Promotion>();
    	
    	Users user = userService.get(Long.parseLong(id));
    	user.setLatitude(Double.valueOf(latitude));
    	user.setLongitude(Double.valueOf(longitude));
    	
	    this.globalPromotionArea = PromotionArea.getInstance();
	    this.globalPromotionArea.setStoreAreasRegistered(this.storeService.getListAll());
	    this.userLocationMonitoring.setPromotionArea(globalPromotionArea);
    	
        userLocationMonitoring.checkNearby(user);
        
        List<Long> idStoreList = userLocationMonitoring.getIdStoreList();
        
        if (idStoreList.size() > 0) {
            //percorre as lojas que o usuário ficou próximo
            for (Long idStore : idStoreList) {
            	promotionList = storeService.get(idStore).getPromotionList();
            	//pupula a lista de cupons no usuário
            	for (Promotion element : promotionList) {
            		allPromotionsList.add(element);
            		for (Coupon coupon : element.getCoupons()) {
            			couponList.add(coupon);
            		}
            	}            	
            }
            //envia mensagem para o usuário
        	System.out.println("O usuário " + id + " acaba de receber os cupons das promoções das Lojas registradas ");
            user.setCouponList(couponList);
            userService.save(user);
            return allPromotionsList;
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

    @GET
    @Produces("application/json")
    @Path(value = "/{idUser}/add/friend/{idFriend}")
    public Object addFriend(@PathParam("idUser") long idUser, @PathParam("idFriend") long idFriend) {
    	Message message = new Message();
    	
    	Users user = this.userService.get(idUser);
    	Users friend = this.userService.get(idFriend);
    	
    	if (user.addIdFriend(friend)) {
    		this.userService.save(user);
    		message.setId(1);
    		message.setConteudo("O amigo foi salvo com sucesso.");
    	}else {
    		message.setId(2);
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
     * Dado um usuário logado, ele remove o amigo selcionado
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
        	message.setId(1);
        	message.setConteudo("Amigo removido com sucesso!");
    	}else {
        	message.setId(1);
        	message.setConteudo("O amigo não foi removido.");
    	}
    	
    	return message;
    }
    
    
}