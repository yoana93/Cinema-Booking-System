package slbedu.library.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import slbedu.library.dao.HallDAO;
import slbedu.library.dao.ReservationDAO;
import slbedu.library.model.Movie;
import slbedu.library.model.Reservation;
import slbedu.library.model.ReservationEntity;
import slbedu.library.model.Seat;
import slbedu.library.model.User;

@Singleton
public class ReservationService {
	
	
	@EJB
	private HallDAO hallDAO;
	
	@EJB
	private ReservationDAO resrvationDAO;
	
	private List<TemporateReservation> reservationsUnpaid = new ArrayList<>();
	
	public synchronized List<SeatTrans> getSeats(Movie movie) {
		List<Seat> seats = hallDAO.findSeatsInHall(movie.getHall());
		List<SeatTrans> result = new ArrayList<>();
		for(Seat s: seats) {
			SeatTrans trans = new SeatTrans(s);
			trans.movieId = movie.getId();
			result.add(trans);
		}
		List<Reservation> reservations = resrvationDAO.getAllReservationsForMovie(movie);
		mapSeats(movie, result, reservations);
		mapSeats(movie, result, getListFromTemporateReservations(reservationsUnpaid));
		
		return result;
	}
	
	public synchronized void sheduleTemproates(int minutes) {
		for(TemporateReservation tmp: reservationsUnpaid) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.MINUTE, -minutes);
			if(tmp.date.before(c.getTime())) {
				//shitvame go
				reservationsUnpaid.remove(tmp);
			}
		}
	}
	
	public synchronized void payReservation(User user, Movie movie) {
		for(TemporateReservation tmp: this.reservationsUnpaid) {
			if(tmp.res.getMovie().equals(movie) && tmp.res.getUser().equals(user)) {
				resrvationDAO.addReservation(tmp.res);
				this.reservationsUnpaid.remove(tmp);
				break;
			}
		}
	}
	
	private synchronized List<Reservation> getListFromTemporateReservations(List<TemporateReservation> tmplist) {
		List<Reservation> result = new ArrayList<Reservation>();
		for(TemporateReservation tmp: tmplist) {
			result.add(tmp.res);
		}
		return result;
	}
	
	public synchronized void startReservation(Movie movie, User user) {
		Reservation res = new Reservation(user, movie);
		reservationsUnpaid.add(new TemporateReservation(res, new Date()));
	}
	
	public synchronized boolean addPlaceToReservation(User user, Movie movie, Seat seat) {
		//check if not taken
		if(checkIfSeatIsTaken(movie, seat)) {
			return false;
		}
		for(TemporateReservation tmp: this.reservationsUnpaid) {
			if(tmp.res.getUser().equals(user) && tmp.res.getMovie().equals(movie)) {
				ReservationEntity r = new ReservationEntity(tmp.res, seat);
				r.setReservation(tmp.res);
				tmp.res.addReservationEntity(r);
				return true;
			}
		}
		//if there is no reservation, it will be created
		startReservation(movie, user);
		return addPlaceToReservation(user, movie, seat);
	}
	
	private synchronized boolean checkIfSeatIsTaken(Movie movie, Seat seat) {
		List<Reservation> reservationsFromDb = resrvationDAO.getAllReservationsForMovie(movie);
		for(Reservation res: reservationsFromDb) {
			for(ReservationEntity e: res.getReservationEntities()) {
				if(e.getSeat().equals(seat)) {
					return true;
				}
			}
		}
		
		for(TemporateReservation tmp: reservationsUnpaid) {
			for(ReservationEntity e: tmp.res.getReservationEntities()) {
				if(e.getSeat().equals(seat)) {
					return true;
				}
			}
		}
		
		return false;
	}

	private synchronized void mapSeats(Movie movie, List<SeatTrans> result,
			List<Reservation> reservations) {
		for(Reservation reservation: reservations) {
			if(reservation.getMovie().equals(movie)) {
				for(ReservationEntity r: reservation.getReservationEntities()) {
					matchTaken(result, r.getSeat().getId());
				}
			}
		}
	}
	
	private synchronized void matchTaken(List<SeatTrans> seats, int seatId) {
		for(SeatTrans s: seats) {
			if(s.id == seatId) {
				s.isTaken = true;
			}
		}
	}
}

class TemporateReservation {
	Reservation res;
	Date date;
	
	TemporateReservation(Reservation r, Date d) {
		this.res = r;
		this.date = d;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((res == null) ? 0 : res.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TemporateReservation other = (TemporateReservation) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (res == null) {
			if (other.res != null)
				return false;
		} else if (!res.equals(other.res))
			return false;
		return true;
	}
	
}
