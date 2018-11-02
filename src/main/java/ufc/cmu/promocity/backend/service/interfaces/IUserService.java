package ufc.cmu.promocity.backend.service.interfaces;

import java.util.List;

import ufc.cmu.promocity.backend.model.Users;

public interface IUserService {

	public List<Users> getAllUsers();
	public Users getUser(Long id);
	public void addUser(Users user);
	public void updateUser(Long id, Users newUser);
	public Users deleteUser(Long id);	
	
}
