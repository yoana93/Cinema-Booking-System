package slbedu.library.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the SEAT database table.
 * 
 */
@Entity
@XmlRootElement
@Table(name="SEAT")
@NamedQuery(name="Seat.findAll", query="SELECT s FROM Seat s")
public class Seat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int col;

	private int row;

	//bi-directional many-to-one association to ReservationEntity
	@OneToMany(mappedBy="seat")
	private List<ReservationEntity> reservationEntities = new ArrayList<>();

	//bi-directional many-to-one association to Hall
	@ManyToOne
	private Hall hall;

	public Seat() {
	}

	public Seat(int col, int row) {
		this.col = col;
		this.row = row;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCol() {
		return this.col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public List<ReservationEntity> getReservationEntities() {
		return this.reservationEntities;
	}

	public void setReservationEntities(List<ReservationEntity> reservationEntities) {
		this.reservationEntities = reservationEntities;
	}

	public ReservationEntity addReservationEntity(ReservationEntity reservationEntity) {
		getReservationEntities().add(reservationEntity);
		reservationEntity.setSeat(this);

		return reservationEntity;
	}

	public ReservationEntity removeReservationEntity(ReservationEntity reservationEntity) {
		getReservationEntities().remove(reservationEntity);
		reservationEntity.setSeat(null);

		return reservationEntity;
	}

	public Hall getHall() {
		return this.hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
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
		Seat other = (Seat) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seat [id=" + id + ", col=" + col + ", row=" + row + ", hall="
				+ hall + "]";
	}

}