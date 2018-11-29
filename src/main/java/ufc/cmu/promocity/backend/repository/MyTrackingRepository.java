package ufc.cmu.promocity.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufc.cmu.promocity.backend.model.MyTracking;
import ufc.cmu.promocity.backend.model.Users;

@Repository
public interface MyTrackingRepository extends JpaRepository<MyTracking, Long> {
	MyTracking findByUser(Users user);
}
