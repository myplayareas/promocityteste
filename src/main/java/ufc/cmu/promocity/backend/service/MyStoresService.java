package ufc.cmu.promocity.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import ufc.cmu.promocity.backend.model.MyStores;
import ufc.cmu.promocity.backend.model.Users;
import ufc.cmu.promocity.backend.repository.MyStoresRepository;

@Service
public class MyStoresService extends AbstractService<MyStores, Long>{

	@Autowired
	private MyStoresRepository myStoresRepository; 
	
	@Override
	protected JpaRepository<MyStores, Long> getRepository() {		
		return myStoresRepository;
	}
	
	public MyStores getMyStoresByUser(Users user) {
		return myStoresRepository.findByUser(user);
	}

}
