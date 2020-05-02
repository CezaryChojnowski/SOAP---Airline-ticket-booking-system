package dataAccess;

import model.Airport;
import model.Flight;
import model.Passenger;
import model.Ticket;

import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface ITicket {

    @WebMethod
    public Ticket checkReservation(int number_of_reservation);

    @WebMethod
    public List<Ticket> BookFlight(Passenger passenger, Flight flight);

}
