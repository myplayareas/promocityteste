package ufc.cmu.promocity.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufc.cmu.promocity.backend.model.MyStores;
import ufc.cmu.promocity.backend.model.Users;

@Repository
public interface MyStoresRepository extends JpaRepository<MyStores, Long> {
	MyStores findByUser(Users user);
}
