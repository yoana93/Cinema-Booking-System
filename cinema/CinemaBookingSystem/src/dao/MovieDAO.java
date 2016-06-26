package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Movie;
import model.Ticket;


@Singleton
public class MovieDAO {

    @PersistenceContext
    private EntityManager em;
    
    public void addMovie(Movie movie) {
        em.persist(movie);
        em.flush();
    }

        
    public List<Movie> getAll() {
        String txtQuery = "SELECT m FROM Movie m";
        TypedQuery<Movie> query = em.createQuery(txtQuery, Movie.class);
        return query.getResultList();
    }
    
    public List<Movie> getMoviesAfter(Date startingTime) {
        String txtQuery = "SELECT m FROM Movie m WHERE m.startingTime>=:startingTime";
        TypedQuery<Movie> query = em.createQuery(txtQuery, Movie.class);
        query.setParameter("date", startingTime);
        return query.getResultList();
    }
    
    public Movie findMoviebyName(String moviename)
    {
        String txtQuery = "Select m FROM Movie m WHERE m.movieName=:moviename";
        TypedQuery<Movie>query = em.createQuery(txtQuery,Movie.class);
        query.setParameter("moviename", moviename);
        return query.getSingleResult();
    }
    
    public List<Ticket> findBuyedTicketsForTheMovie(Movie m) {
	String txtQuery = "SELECT t1, t2 FROM sold_ticket t1 inner join film-show t2 on t1.neighbors t2 ";
	TypedQuery<Ticket> query = em.createQuery(txtQuery, Ticket.class);
    return query.getResultList();
}

}

