package ws;

import Impl.AirPortImp;
import Impl.FlightImpl;
import Impl.PassengerImpl;
import Impl.TicketImpl;
import dataAccess.IAirport;
import dataAccess.IFlight;
import dataAccess.IPassenger;
import dataAccess.ITicket;
import model.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class TicketBooking {
    AirPortImp airPortImp = new AirPortImp();
    List<Airport> airPortsList = airPortImp.generateAirPortsList();

    FlightImpl flightImpl = new FlightImpl();
    List<Flight> flightList = flightImpl.generateFlightsList(airPortsList);

    PassengerImpl passengerImpl = new PassengerImpl();
    List<Passenger> passengerList = passengerImpl.generatePassengerList();

    TicketImpl ticketImpl = new TicketImpl();
    List<Ticket> ticketList = ticketImpl.generateTickets(flightList, passengerList);


    @WebMethod
    public List<Airport> findAirPortByCity(String City){
        IAirport airport = new AirPortImp();
        return airport.findAirPortByCity((ArrayList<Airport>) airPortsList, City);
    }

    @WebMethod
    public List<Airport> findAirPortByCountry(String Country){
        IAirport airport = new AirPortImp();
        return airport.findAirPortByCountry((ArrayList<Airport>) airPortsList, Country);
    }

    @WebMethod
    public ArrayList<Flight> getAllFlight(){
        IFlight flight = new FlightImpl();
        return (ArrayList<Flight>) flight.getAllFlights(flightList);
    }

    @WebMethod
    public List<Flight> findFlightsBetweenGivenCities(Airport from, Airport to){
        IFlight flight = new FlightImpl();
        return flight.findFlightsBetweenGivenCities((ArrayList<Flight>) flightList, from, to);
    }

    @WebMethod
    public boolean BookFlight(Flight flight, PassengerDTO passenger){
        ITicket iTicket = new TicketImpl();
        iTicket.BookFlight(passenger, flight, ticketList);
        return true;
    }

    @WebMethod
    public boolean register(Passenger passenger){
        IPassenger iPassenger = new PassengerImpl();
        iPassenger.register(passenger,passengerList);
        return true;
    }

    @WebMethod
    public PassengerDTO login(String login, String password){
        IPassenger passenger = new PassengerImpl();
        return passenger.login(passengerList, login, password);
    }

    @WebMethod
    public Ticket checkReservation(int number_of_reservation){
        ITicket iTicket = new TicketImpl();
        return iTicket.checkReservation(number_of_reservation, ticketList);
    }

}
