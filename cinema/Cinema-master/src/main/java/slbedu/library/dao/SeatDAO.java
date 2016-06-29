package slbedu.library.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import slbedu.library.model.ReservationEntity;
import slbedu.library.model.Seat;

@Singleton
public class SeatDAO {

	@PersistenceContext
	private EntityManager em;

	public void addHall(Seat seat) {
		em.persist(seat);
		em.flush();
	}

	public Seat getById(int id) {
		return em.find(Seat.class, id);
	}

	public List<Seat> getAllSeatsfrom(List<ReservationEntity> reservationEntity) {
		List<Seat> seats = new ArrayList<Seat>();
		for (int i = 0; i < reservationEntity.size(); i++) {
			seats.add(reservationEntity.get(i).getSeat());
		}
		return seats;
	}
}
