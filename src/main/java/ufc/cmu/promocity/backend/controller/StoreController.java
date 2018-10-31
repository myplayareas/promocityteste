package ufc.cmu.promocity.backend.controller;

import java.net.URI;
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

import ufc.cmu.promocity.backend.model.Store;
import ufc.cmu.promocity.backend.service.StoreService;

/**
 * Stores Controller
 * @author armandosoaressousa
 *
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
		this.storeService.createTestStores();
	}

	 /**
     * Retorna em um JSON todos as lojas cadastrados
     * @return
     */
    @GET
    @Produces("application/json")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }
    
    /**
     * Dado um id retorna o JSON dos dados da loja
     * @param id
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Store getStore(@PathParam("id") String id) {
    	return storeService.getStore(Long.parseLong(id));
    }
    
    /**
     * Dados os dados uma loja no repositorio
     * @param Store
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response addStore(Store Store) {
        storeService.addStore(Store);
        URI uri = URI.create("/" + String.valueOf(Store.getId()));
		return Response.created(uri).build();
    }
    
    /**
     * Dado um id e os dados do Store faz sua atualizacao
     * @param id
     * @param Store
     * @return
     */
    @PUT
    @Consumes("application/json")
    @Path("/{id}")
    public Response updateStore(@PathParam("id") String id, Store Store) {
        storeService.updateStore(Long.parseLong(id), Store);
        return Response.noContent().build();
    }
    
    /**
     * Dado um id de um livro faz sua remocao do repositorio
     * @param id
     * @return
     */
    @DELETE
    @Path("/{id}")
    public Response deleteStore(@PathParam("id") String id) {
        storeService.deleteStore(Long.parseLong(id));
        return Response.ok().build();
    }
    
    /*
	 * 	lojistas\idLojista\promocoes\add
		lojistas\idLojista\promocoes\idPromocao
		lojistas\idLojista\promocoes\list
	 */
	/*
	 * 	lojistas\idLojista\promocoes\idPromocao\cupons\add
		lojistas\idLojista\promocoes\idPromocao\cupons\idCupom
		lojistas\idLojista\promocoes\idPromocao\cupons\list
	 */

}
