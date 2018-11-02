package ufc.cmu.promocity.backend.controller;

import ufc.cmu.promocity.backend.context.PromotionArea;
import ufc.cmu.promocity.backend.context.UserLocationMonitoring;
import ufc.cmu.promocity.backend.model.Users;
import ufc.cmu.promocity.backend.service.UsersService;
import ufc.cmu.promocity.backend.utils.geographic.GPSPoint;

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
	
	@Autowired
	public void setUserService(UsersService userServices){
		this.userService = userServices;
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
    @PUT
    @Consumes("application/json")
    @Path("{id}/location/{latitude}/{longitude}")
    public Response updateUserLocation(@PathParam("id") String id, @PathParam("latitude") String latitude, @PathParam("longitude") String longitude) {
    	Users user = userService.get(Long.parseLong(id));
    	
    	user.setLatitude(Double.valueOf(latitude));
    	user.setLongitude(Double.valueOf(longitude));
    	
	    this.globalPromotionArea = PromotionArea.getInstance();
	    this.userLocationMonitoring.setPromotionArea(globalPromotionArea);
    	
        userLocationMonitoring.checkNearby(user);

        return Response.noContent().build();
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
    
}