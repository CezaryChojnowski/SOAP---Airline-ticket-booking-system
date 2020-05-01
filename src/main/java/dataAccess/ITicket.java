package dataAccess;

import model.Airport;
import model.Flight;
import model.Passenger;
import model.Ticket;

import java.util.Date;

public interface ITicket {

    public Ticket checkReservation(int number_of_reservation);

    public Flight BookFlight(Passenger passenger, Airport from, Airport to, Date date);

}
