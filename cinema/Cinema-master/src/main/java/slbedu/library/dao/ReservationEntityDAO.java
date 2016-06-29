package slbedu.library.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import slbedu.library.model.Reservation;
import slbedu.library.model.ReservationEntity;

@Singleton
public class ReservationEntityDAO {

	@PersistenceContext
	private EntityManager em;

	public void addEntity(ReservationEntity rEntity) {
		em.persist(rEntity);
	}

	public List<ReservationEntity> getAllReservationEntitiesfrom(
			List<Reservation> reservations) {
		List<ReservationEntity> reservationEntity = new ArrayList<ReservationEntity>();
		for (int i = 0; i < reservations.size(); i++) {
			String txtQuery = "SELECT r FROM ReservationEntity r WHERE r.reservation=:reservation";
			TypedQuery<ReservationEntity> query = em.createQuery(txtQuery,
					ReservationEntity.class);
			query.setParameter("reservation", reservations.get(i));
			List<ReservationEntity> queryEntity = query.getResultList();
			for (int j = 0; j < queryEntity.size(); j++) {
				reservationEntity.add(queryEntity.get(j));
			}
		}
		return reservationEntity;
	}
}