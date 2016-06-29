package slbedu.library.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import slbedu.library.model.Hall;
import slbedu.library.model.Movie;
import slbedu.library.model.Reservation;
import slbedu.library.model.User;

@Singleton
public class ReservationDAO {
	

    @PersistenceContext
    private EntityManager em;
    
    public void addReservation(Reservation reservation) {
    	em.persist(reservation);
    	em.flush();
    }
    
    public void updateReservation(Reservation reservation) {
    	Reservation reservationFromDb;
		if((reservationFromDb = em.find(Reservation.class, reservation.getId())) == null) {
			System.out.println("reservation with id does not exist: " + reservation.getId());
		} else {
			reservationFromDb.setReservationEntities(reservation.getReservationEntities());
			reservationFromDb.setUsed(reservation.isUsed());
			em.merge(reservationFromDb);
			em.flush();
		}
    }
    
    public List<Reservation> getAll() {
    	String txtQuery = "SELECT r FROM Reservation r";
        TypedQuery<Reservation> query = em.createQuery(txtQuery, Reservation.class);
        return query.getResultList();
    }
    
    
    public List<Reservation> getAllUserReservations(User user) {
    	String txtQuery = "SELECT r FROM Reservation r WHERE r.user=:user";
        TypedQuery<Reservation> query = em.createQuery(txtQuery, Reservation.class);
        query.setParameter("user", user);
        return query.getResultList();
    }
    
    public List<Reservation> getAllReservationsForMovie(Movie movie) {
    	String txtQuery = "SELECT r FROM Reservation r WHERE r.movie=:movie";
        TypedQuery<Reservation> query = em.createQuery(txtQuery, Reservation.class);
        query.setParameter("movie", movie);
        return query.getResultList();
    }
}
