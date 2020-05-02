package dataAccess;

import model.Flight;
import model.Passenger;
import model.PassengerDTO;
import model.Ticket;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface ITicket {

    @WebMethod
    public Ticket checkReservation(Integer number_of_reservation, List<Ticket> ticketList);

    @WebMethod
    public List<Ticket> BookFlight(PassengerDTO passenger, Flight flight, List<Ticket> ticketList);

    public List<Ticket> getAllTicket(List<Ticket> ticketList);

    public List<Ticket> generateTickets(List<Flight> flightList, List<Passenger> passengerList);

    public int generateTicketCode(List<Ticket> ticketList);

}
