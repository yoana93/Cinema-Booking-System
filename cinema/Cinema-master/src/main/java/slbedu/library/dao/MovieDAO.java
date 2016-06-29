package slbedu.library.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import slbedu.library.model.Movie;


@Singleton
public class MovieDAO {

    @PersistenceContext
    private EntityManager em;
    
    public void addMovie(Movie movie) {
    	em.persist(movie);
    	em.flush();
    }
    
    public Movie getById(int id) {
    	return em.find(Movie.class, id);
    }
        
    public List<Movie> getAll() {
    	String txtQuery = "SELECT m FROM Movie m";
        TypedQuery<Movie> query = em.createQuery(txtQuery, Movie.class);
        return query.getResultList();
    }
    
    public List<Movie> getMoviesAfter(Date startingTime) {
    	String txtQuery = "SELECT m FROM Movie m WHERE m.startingTime>=:startingTime";
        TypedQuery<Movie> query = em.createQuery(txtQuery, Movie.class);
        query.setParameter("startingTime", startingTime);
        return query.getResultList();
    }
    
    public Movie findMoviebyName(String moviename)
    {
    	String txtQuery = "Select m FROM Movie m WHERE m.movieName=:moviename";
    	TypedQuery<Movie>query = em.createQuery(txtQuery,Movie.class);
    	query.setParameter("moviename", moviename);
    	return query.getSingleResult();
    }

}
