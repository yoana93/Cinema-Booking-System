package slbedu.library.services;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

//each minute remove the unpaid reservations
@Stateful
@StatefulTimeout(1)
public class ReservationSheduler {
	private static final int MINUTES_TO_KEEP_RESERVATION = 10;
	
	@EJB
	private ReservationService reservationService;
	
	@Remove
	public void checkout() {
		reservationService.sheduleTemproates(MINUTES_TO_KEEP_RESERVATION);
	}
}
