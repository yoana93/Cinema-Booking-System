package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.HallDAO;
import dao.MovieDAO;
import dao.TicketDAO;
import dao.UserDAO;
import model.Hall;
import model.Movie;
import model.Ticket;
import model.User;

@Stateless
public class DatabaseUtils {
    
    private static User[] USERS = {
            new User("admin", "admin","admin@test.bg", true),
            new User("pesho", "123","pesho_hakera@test.bg", false),
            new User("stamat", "stamat", "stamat_stamatov@test.bg",false)};
    
    private static Hall[] HALLS = {
        	new Hall("Pink", 5, 5), new Hall("Blue",10,10)
        };
        
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static Movie[] MOVIES = {
    		new Movie("Me Before You",LocalDateTime.parse("2016-06-25 20:00",formatter), 120, "Louisa Clark must find a "
    				+ "new job after being laid off from a café. She finds work in taking care of Will Traynor,"
    				+ " a cynical banker who was completely paralyzed in by a motorcycle accident. At first, "
    				+ "he reacts coldly to her spunkiness, but they soon become friends and develop feelings "
    				+ "for each other, even though Louisa has an unthoughtful, marathon-running boyfriend named"
    				+ " Patrick. Louisa learns that Will has given his parents six months before they must bring "
    				+ "him to Switzerland for euthanasia. Will cannot deal with the pain and suffering of his disability. "
    				+ "Louisa secretly makes it her mission to change his mind and takes him"
    				+ " on all the adventures that she can to prove that life is worth living. However, at their "
    				+ "final trip to Mauritius with Will's nurse Nathan, Will confesses he intends to follow "
    				+ "through with the euthanasia and asks for her to accompany him. She is heartbroken and does "
    				+ "not speak to him the rest of the trip. After they arrive home, she goes to Switzerland to "
    				+ "see Will in his final moments. After his death, he bequeaths her enough money her to "
    				+ "continue her education and instructs her to, 'live well'.", 
    				"../../../../WebContent/pictures/me_before_you.jpg", HALLS[0]),
    		
    		
    		new Movie("Finding Dory",LocalDateTime.parse("2016-06-25 18:00",formatter), 115, "'Finding Dory' reunites Dory "
    				+ "with friends Nemo and Marlin on a search for answers about her past. What can she remember? Who are her"
    				+ " parents? And where did she learn to speak to whales", 
    				"../../../../WebContent/pictures/finding_dori.jpg", HALLS[1]),
    		
   
    		new Movie("Ice Age 5: Collision Course",LocalDateTime.parse("2016-06-26 20:00",formatter), 90, "Scrat's epic pursuit of his"
    				+ " elusive acorn catapults him outside of Earth, where he accidentally sets off a series of cosmic "
    				+ "events that transform and threaten the planet. To save themselves from peril, Manny, Sid, Diego,"
    				+ " and the rest of the herd leave their home and embark on a quest full of thrills and spills, highs "
    				+ "and lows, laughter and adventure while traveling to exotic new lands and encountering a host of colorful"
    				+ " new characters.", 
    				"../../../../WebContent/pictures/pictures/ice_age.jpg", HALLS[0])
    			
    };
    
    private static Ticket[] BUYED_TICKETS = {
    	new Ticket(USERS[0].getUserName(), MOVIES[0].getMovieName(),3,3,12),
    	new Ticket(USERS[0].getUserName(), MOVIES[0].getMovieName(),3,4,12),
    	new Ticket(USERS[1].getUserName(), MOVIES[1].getMovieName(),3,3,12)
    };

    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private UserDAO userDAO;
    
    @EJB
    private TicketDAO ticketDAO;
    
    @EJB
    private HallDAO hallDAO;
    
    @EJB
    private MovieDAO movieDAO;
    
    public void addTestDataToDB() {
        deleteData();
        addTestUsers();
    }

    private void deleteData() {
        em.createQuery("DELETE FROM Ticket").executeUpdate();
        em.createQuery("DELETE FROM Movie").executeUpdate();
        em.createQuery("DELETE FROM Hall").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();
   }

    private void addTestUsers() {
        for (User user : USERS) {
            userDAO.addUser(user);
           
        }
        
        for (Hall hall: HALLS) {
        	hallDAO.addHall(hall);
        }
        
        for (Movie m: MOVIES) {
        	movieDAO.addMovie(m);
        }
        
        for (Ticket t: BUYED_TICKETS) {
        	ticketDAO.addTicket(t);
        	
        }
 
    }
}

