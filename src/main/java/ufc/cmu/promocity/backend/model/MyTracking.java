package ufc.cmu.promocity.backend.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class MyTracking extends AbstractModel<Long>{
	@OneToOne
	private Users user;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany
	private List<Track> trackingList = new LinkedList<Track>();

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Track> getTrackingList() {
		return trackingList;
	}

	public void setTrackingList(List<Track> trackingList) {
		this.trackingList = trackingList;
	}

}
