package ufc.cmu.promocity.backend.context;

import java.util.LinkedList;
import java.util.List;

import ufc.cmu.promocity.backend.model.Users;

/**
 * Classe que registra em uma lista cupons já enviados para um usuario
 * @author armandosoaressousa
 *
 */
public class CouponsSent {
	private static CouponsSent instance = null;
	private List<Users> UserAlreadyRegistered; 
	
	public CouponsSent() {		
		this.UserAlreadyRegistered = new LinkedList<Users>();
	}
		
	public CouponsSent(List<Users> list) {
		this.UserAlreadyRegistered = list;
	}
	
	/**
	 * Create a singleton to share this class with all application members
	 * @return instância Global do Controle de cupons
	 */
	public static CouponsSent getInstance() {
		if (instance == null) {
			instance = new CouponsSent();
		}
		return instance;
	}
	
	/**
	 * Add a User list
	 * @param sa
	 */
	public void addUsersAlready(Users sa) {
		this.UserAlreadyRegistered.add(sa);
	}
	
	/**
	 * Remove a User from list of UserArea
	 * @param sa UserArea of User unregistered
	 */
	public void removeUserFromUsersAlready(Users sa) {
		this.UserAlreadyRegistered.remove(sa);
	}

	/**
	 * Retorna todas as Already de promocao das lojas registradas
	 * @return
	 */
	public List<Users> getUserAlreadyRegistered() {
		return this.UserAlreadyRegistered;
	}
		
}