package ufc.cmu.promocity.backend.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import ufc.cmu.promocity.backend.model.*;
import ufc.cmu.promocity.backend.service.exceptions.UserNotFoundException;
import ufc.cmu.promocity.backend.service.interfaces.IUserService;
import ufc.cmu.promocity.backend.utils.geographic.GPSPoint;

/**
 * Class the manipulate the repository of users
 * @author armandosoaressousa
 *
 */
@Service
public class UserService implements IUserService{
	private static AtomicLong contador = new AtomicLong(0);
	private final Map<Long, User> users;
	
	public UserService() {
		this.users = new HashMap<Long, User>();
	}

	/**
	 * Returns all users in repository
	 * @return List of users
	 */
	public List<User> getAllUsers() {
		List<User> lista = new LinkedList<User>();
		
		for (User element : users.values()) {
			lista.add(element);
		}
		
		return lista;
	}
	
	/**
	 * get User by id
	 * @param id
	 * @return user
	 */
	public User getUser(Long id) {
		return this.users.get(id);
	}
	
	/**
	 * Add a new user
	 * @param user
	 */
	public void addUser(User user) {
		Long id = contador.incrementAndGet();
		user.setId(id);
		users.put(id, user);
	}
	
	/**
	 * Replace a new user by id
	 * @param id
	 * @param newUser
	 */
	public void updateUser(Long id, User newUser) {
		users.replace(id, newUser);
	}
	
	/**
	 * Delete a user by id
	 * @param id
	 * @return 
	 */
	public User deleteUser(Long id) {
		if (!users.containsKey(id)) {
			throw new UserNotFoundException("Can't delete user. User for oid: " + id + " not found");
		}
		return users.remove(id);
	}
	
	public void createTestUsers() {
		User u1 = new User(Long.valueOf(1), "Armando Soares Sousa", "123456", "armando@ufpi.edu.br", new GPSPoint(0,0)); 
		User u2 = new User(Long.valueOf(2), "Jaão", "123", "joao@gmail.com", new GPSPoint(0,0));
		User u3 = new User(Long.valueOf(3), "Maria", "456", "maria@gmail.com", new GPSPoint(0,0));
		this.addUser(u1);
		this.addUser(u2);
		this.addUser(u3);
	}
	
}