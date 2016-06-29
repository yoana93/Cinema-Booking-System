package model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
//import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the film-show database table.
 * 
 */
@Entity
@XmlRootElement
@Table(name="film-show")
@NamedQuery(name="Movie.findAll", query="SELECT film_name FROM film-show ")
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(name="film_name")
	private String movieName;
	
	//bi-directional many-to-one association to Hall
	@ManyToOne
	private Hall hall;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="starting_time")
	private LocalDateTime date;
	
	@Column(name="duration_in_min")
	private int durationInMin;
	
	@Column(name="description")
	private String description;
	
	@Column(name="img_path")
	private String imgPath;
	
	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="movie")
	//@XmlElement
	private List<Ticket> soldTickets = new ArrayList<>();

	
	public Movie() {
	}
	
	public Movie(String movieName, LocalDateTime date, int durationInMin, Hall hall) {
		this();
		this.movieName = movieName;
		this.date = date;
		this.durationInMin = durationInMin;
		this.hall = hall;
	}
	

	public Movie(String movieName, LocalDateTime date, int durationInMin, String description, String imgPath, Hall hall) {
		this(movieName, date, durationInMin, hall);
		this.movieName = movieName;
		this.date = date;
		this.durationInMin = durationInMin;
		this.description = description;
		this.imgPath = imgPath;
		this.hall = hall;
	}


	public String getMovieName() {
		return this.movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public LocalDateTime getDate() {
		return this.date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Hall getHall() {
		return this.hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public List<Ticket> getSoldTickets() {
		return this.soldTickets;
	}

	public void setSoldTickets(List<Ticket> soldTickets) {
		this.soldTickets = soldTickets;
	}

	public Ticket addSoldTicket(Ticket soldTicket) {
		getSoldTickets().add(soldTicket);
		soldTicket.setMovieName(this.movieName);

		return soldTicket;
	}

	public Ticket removeReservation(Ticket soldTicket) {
		getSoldTickets().remove(soldTicket);
		soldTicket.setMovieName(null);

		return soldTicket;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + durationInMin;
		result = prime * result + ((hall == null) ? 0 : hall.hashCode());
		result = prime * result + ((movieName == null) ? 0 : movieName.hashCode());
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
		Movie other = (Movie) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (durationInMin != other.durationInMin)
			return false;
		if (hall == null) {
			if (other.hall != null)
				return false;
		} else if (!hall.equals(other.hall))
			return false;
		if (movieName == null) {
			if (other.movieName != null)
				return false;
		} else if (!movieName.equals(other.movieName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [movieName=" + movieName+ ", "
				+ "startingTime=" + date+ ", hall=" + hall + "]";
	}

}
