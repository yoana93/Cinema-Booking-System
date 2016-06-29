package dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Ticket;

@Singleton
public class TicketDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Ticket> getAll() {
        String txtQuery = "SELECT t FROM Ticket t";
        TypedQuery<Ticket> query = em.createQuery(txtQuery, Ticket.class);
        return query.getResultList();
    }
    
    public List<Ticket> getAllTicketsByMovie(String movieName){
        String txtQuery = "SELECT t FROM Ticket t WHERE t.movieName=:movieName";
        TypedQuery<Ticket>query = em.createQuery(txtQuery,Ticket.class);
        query.setParameter("movieName", movieName);
        return query.getResultList();
    }
    
    public List<Ticket> getAllTicketsByUser(String userName){
        String txtQuery = "SELECT t FROM Ticket t WHERE t.user_name=:user_name";
        TypedQuery<Ticket>query = em.createQuery(txtQuery,Ticket.class);
        query.setParameter("user_name", userName);
        return query.getResultList();
    }
}
