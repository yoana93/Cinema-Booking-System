package slbedu.library.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the RESERVATION_ENTITY database table.
 * 
 */
@Entity
@Table(name="RESERVATION_ENTITY")
@NamedQuery(name="ReservationEntity.findAll", query="SELECT r FROM ReservationEntity r")
public class ReservationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to Reservation
	@ManyToOne
	private Reservation reservation;

	//bi-directional many-to-one association to Seat
	@ManyToOne
	private Seat seat;

	public ReservationEntity() {
	}

	public ReservationEntity(Reservation reservation, Seat seat) {
		this.reservation = reservation;
		this.seat = seat;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reservation getReservation() {
		return this.reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Seat getSeat() {
		return this.seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		ReservationEntity other = (ReservationEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReservationEntity [id=" + id + ", reservation=" + reservation + ", seat=" + seat + "]";
	}

}