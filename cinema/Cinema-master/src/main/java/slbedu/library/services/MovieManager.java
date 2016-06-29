package slbedu.library.services;


import slbedu.library.dao.HallDAO;
import slbedu.library.dao.MovieDAO;
import slbedu.library.dao.SeatDAO;
import slbedu.library.dao.UserDAO;
import slbedu.library.model.Hall;
import slbedu.library.model.Movie;
import slbedu.library.model.Seat;
import slbedu.library.model.User;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("movie")
public class MovieManager {
    
    private static final Response RESPONSE_OK = Response.ok().build();

    @EJB
    private UserDAO userDAO;

    @EJB
    private MovieDAO movieDAO;
    
    @EJB
    private HallDAO hallDAO;
    
    @EJB
    private SeatDAO seatDAO;
    
    @EJB
    private ReservationService reservationService;
    
    @Inject
    private UserContext context;
    
    @GET
    @Produces("application/json")
    public Collection<Movie> getAllMovies(){
    	List<Movie> result = movieDAO.getAll();
    	
    	return result;
    }
    
    @Path("seats/{id}")
    @GET
    @Produces("application/json")
    public Collection<SeatTrans> getSeats(@PathParam("id") String id) {
    	int movieId = Integer.parseInt(id);
    	Movie movie = movieDAO.getById(movieId);
    	List<SeatTrans> result = reservationService.getSeats(movie);
    	return result;
    };
    
    @Path("reserve/{movieId}/{seatId}")
    @GET
    @Produces("application/json")
    public Response ebaLiGoKvoE(@PathParam("movieId") String movieId, @PathParam("seatId") String seatId) {
    	User user = context.getCurrentUser();
    	Movie movie = movieDAO.getById(Integer.parseInt(movieId));
    	Seat seat = seatDAO.getById(Integer.parseInt(seatId));
    	boolean isSuccess = reservationService.addPlaceToReservation(user, movie, seat);
    	if(isSuccess) {
    		return RESPONSE_OK;
    	} else {
    		return Response.status(HttpURLConnection.HTTP_NOT_FOUND).build(); 
    	}
    }
    
    
}
