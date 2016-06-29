package slbedu.library.services;

import javax.xml.bind.annotation.XmlRootElement;

import slbedu.library.model.Seat;

//Data transfer object
@XmlRootElement
public class SeatTrans {
	public int row;
	public int col;
	public int id;
	public boolean isTaken;
	public int movieId;

	public SeatTrans(){};
	
	public SeatTrans(Seat s) {
		this.row = s.getRow();
		this.col = s.getCol();
		this.id = s.getId();
	}
}
