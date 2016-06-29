package dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Hall;

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
		if((hallFromDb = em.find(Hall.class, hall.getName())) == null) {
			System.out.println("hall with name does not exist: " + hall.getName());
		} else {
			hallFromDb.setMovies(hall.getMovies());
			hallFromDb.setName(hall.getName());
			hallFromDb.setFreeSeats(hall.getFreeSeats());
			em.merge(hallFromDb);
			em.flush();
		}
	}

	public Hall findHallByName(String name) {
		String txtQuery = "SELECT u FROM Hall u WHERE u.name = :name";
		TypedQuery<Hall> query = em.createQuery(txtQuery, Hall.class);
		query.setParameter("name", name);
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

