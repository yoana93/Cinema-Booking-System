package slbedu.library.services;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import slbedu.library.dao.MovieDAO;
import slbedu.library.dao.ReservationDAO;
import slbedu.library.dao.ReservationEntityDAO;
import slbedu.library.dao.SeatDAO;
import slbedu.library.dao.UserDAO;
import slbedu.library.model.Movie;
import slbedu.library.model.Reservation;
import slbedu.library.model.ReservationEntity;
import slbedu.library.model.Seat;

@Stateless
@Path("/employee")
public class EmployeeManager {
	
	@EJB
    private UserDAO userDAO;

    @EJB
    private MovieDAO movieDAO;
    
    @EJB
    private SeatDAO seatDAO;
    
    @EJB
    private ReservationDAO reservationDAO;
    
    @EJB
    private ReservationEntityDAO reservationEntityDAO;
	
    
	@Path("/movies/{name}")
    @GET
    @Produces("application/json")
    public Collection<Seat> getBoughtTickets(@PathParam("name") String moviename){
    	
    	Movie movie = movieDAO.findMoviebyName(moviename);
    	List<ReservationEntity> movieReservationsEntities = reservationEntityDAO.getAllReservationEntitiesfrom((reservationDAO.getAllReservationsForMovie(movie)));
    	
    	return seatDAO.getAllSeatsfrom(movieReservationsEntities);  	  	
    }
    
}