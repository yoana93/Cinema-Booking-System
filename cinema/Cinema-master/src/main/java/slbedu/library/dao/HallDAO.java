package slbedu.library.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import slbedu.library.model.Hall;
import slbedu.library.model.Seat;

@Singleton
public class HallDAO {

	@PersistenceContext
	private EntityManager em;

	public void addHall(Hall hall) {
		em.persist(hall);
		em.flush();
	}
	
	public List<Hall> findAll() {
		String txtQuery = "SELECT h FROM Hall h";
		TypedQuery<Hall> query = em.createQuery(txtQuery, Hall.class);
		return query.getResultList();
	}

	public void updateHall(Hall hall) {
		Hall hallFromDb;
		if((hallFromDb = em.find(Hall.class, hall.getId())) == null) {
			System.out.println("hall with id does not exist: " + hall.getId());
		} else {
			hallFromDb.setMovies(hall.getMovies());
			hallFromDb.setSeats(hall.getSeats());
			em.merge(hallFromDb);
			em.flush();
		}
	}

	public List<Seat> findSeatsInHall(Hall hall) {
		String txtQuery = "SELECT s FROM Seat s WHERE s.hall = :hall";
		TypedQuery<Seat> query = em.createQuery(txtQuery, Seat.class);
		query.setParameter("hall", hall);
		return querySeat(query);
	}

	private List<Seat> querySeat(TypedQuery<Seat> query) {
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public Hall findHallById(int id) {
		String txtQuery = "SELECT u FROM Hall u WHERE u.id = :id";
		TypedQuery<Hall> query = em.createQuery(txtQuery, Hall.class);
		query.setParameter("id", id);
		return queryHall(query);
	}

	private Hall queryHall(TypedQuery<Hall> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
