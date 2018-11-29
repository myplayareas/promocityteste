package ufc.cmu.promocity.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import ufc.cmu.promocity.backend.model.MyStores;
import ufc.cmu.promocity.backend.model.MyTracking;
import ufc.cmu.promocity.backend.model.Users;
import ufc.cmu.promocity.backend.repository.MyStoresRepository;
import ufc.cmu.promocity.backend.repository.MyTrackingRepository;

@Service
public class MyTrackingService extends AbstractService<MyTracking, Long>{

	@Autowired
	private MyTrackingRepository myTrackingRepository; 
	
	@Override
	protected JpaRepository<MyTracking, Long> getRepository() {		
		return myTrackingRepository;
	}
	
	public MyTracking getMyTrackingByUser(Users user) {
		return myTrackingRepository.findByUser(user);
	}

}
