package slbedu.library.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MOVIE database table.
 * 
 */
@Entity
@XmlRootElement
@Table(name="MOVIE")
@NamedQuery(name="Movie.findAll", query="SELECT m FROM Movie m")
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name="movie_name")
	private String movieName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="starting_time")
	private Date startingTime;

	public Movie(String movieName, Date startingTime, Hall hall) {
		this.movieName = movieName;
		this.startingTime = startingTime;
		this.hall = hall;
	}

	//bi-directional many-to-one association to Hall
	@ManyToOne
	private Hall hall;

	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="movie")
	//@XmlElement
	private List<Reservation> reservations = new ArrayList<>();

	public Movie() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovieName() {
		return this.movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Date getStartingTime() {
		return this.startingTime;
	}

	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	public Hall getHall() {
		return this.hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public List<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation addReservation(Reservation reservation) {
		getReservations().add(reservation);
		reservation.setMovie(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setMovie(null);

		return reservation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((movieName == null) ? 0 : movieName.hashCode());
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
		if (id != other.id)
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
		return "Movie [id=" + id + ", movieName=" + movieName
				+ ", startingTime=" + startingTime + ", hall=" + hall + "]";
	}

}