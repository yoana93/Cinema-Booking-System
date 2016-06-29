package slbedu.library.utils;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import slbedu.library.dao.HallDAO;
import slbedu.library.dao.MovieDAO;
import slbedu.library.dao.ReservationDAO;
import slbedu.library.dao.ReservationEntityDAO;
import slbedu.library.dao.UserDAO;
import slbedu.library.model.Hall;
import slbedu.library.model.Movie;
import slbedu.library.model.Reservation;
import slbedu.library.model.ReservationEntity;
import slbedu.library.model.Seat;
import slbedu.library.model.User;

@Stateless
public class DatabaseUtils {
    
    private static User[] USERS = {
            new User("admin", "admin", true),
            new User("vasko", "123", true),
            new User("dancho", "123", false)};
    
    private static Movie[] MOVIES = {
    	new Movie("fast and furious", new Date(), null),
    	new Movie("qko film4e", new Date(), null),
    	new Movie("maliii", new Date(), null)
    };
    
    private static Reservation[] RESERVATIONS = {
    	new Reservation(USERS[0], MOVIES[0]),
    	new Reservation(USERS[0], MOVIES[1]),
    	new Reservation(USERS[2], MOVIES[2])
    };

    private static Seat[] SEATS_1 = {
    	new Seat(1, 1), new Seat(1, 2), new Seat(1, 3), new Seat(1, 4), new Seat(1, 5),
    	new Seat(2, 1), new Seat(2, 2), new Seat(2, 3), new Seat(2, 4), new Seat(2, 5),
    	new Seat(3, 1), new Seat(3, 2), new Seat(3, 3), new Seat(3, 4), new Seat(3, 5)
    };

    private static Seat[] SEATS_2 = {
    	new Seat(1, 1), new Seat(1, 2), new Seat(1, 3), new Seat(1, 4),
    	new Seat(2, 1), new Seat(2, 2), new Seat(2, 3), new Seat(2, 4),
    	new Seat(3, 1), new Seat(3, 2), new Seat(3, 3), new Seat(3, 4),
    };
    
    private static Hall[] HALLS = {
    	new Hall("Majna town"), new Hall("qkata rabota")
    };
    
    private static ReservationEntity[] RES_ENTITIES = {
    	new ReservationEntity(RESERVATIONS[0], SEATS_1[2]),
    	new ReservationEntity(RESERVATIONS[1], SEATS_2[1]),
    	new ReservationEntity(RESERVATIONS[2], SEATS_2[3]),
    	new ReservationEntity(RESERVATIONS[0], SEATS_1[1]),
    	new ReservationEntity(RESERVATIONS[0], SEATS_1[4]),
    };

    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private UserDAO userDAO;
    
    @EJB
    private ReservationDAO reservationDAO;
    
    @EJB
    private HallDAO hallDAO;
    
    @EJB
    private MovieDAO movieDAO;
    
    @EJB
    private ReservationEntityDAO reservationEntityDAO;
    
    public void addTestDataToDB() {
        deleteData();
        addTestUsers();
    }

    private void deleteData() {
        em.createQuery("DELETE FROM ReservationEntity").executeUpdate();
        em.createQuery("DELETE FROM Seat").executeUpdate();
        em.createQuery("DELETE FROM Reservation").executeUpdate();
        em.createQuery("DELETE FROM Movie").executeUpdate();
        em.createQuery("DELETE FROM Hall").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();
   }

    private void addTestUsers() {
    	MOVIES[0].setHall(HALLS[0]);
    	MOVIES[1].setHall(HALLS[1]);
    	MOVIES[2].setHall(HALLS[1]);
        for (User user : USERS) {
            userDAO.addUser(user);
           
        }
        
        for (Hall hall: HALLS) {
        	hallDAO.addHall(hall);
        }
        
        for (Seat seat: SEATS_1) {
        	HALLS[0].addSeat(seat);
        }
        
        for (Seat seat: SEATS_2) {
        	HALLS[1].addSeat(seat);
        }
        
        for (Hall hall: HALLS) {
        	hallDAO.updateHall(hall);
        }
        
        for (Movie m: MOVIES) {
        	movieDAO.addMovie(m);
        }
        
        System.out.println("MOVIES[0].ID : " + MOVIES[0].getId());
        
        for (Reservation r: RESERVATIONS) {
        	reservationDAO.addReservation(r);
        }
        
        for (ReservationEntity r: RES_ENTITIES) {
        	reservationEntityDAO.addEntity(r);
        }
    }
}
