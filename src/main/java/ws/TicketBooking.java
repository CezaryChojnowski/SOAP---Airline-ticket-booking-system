package ws;

import DTO.CountryDTO;
import DTO.PassengerDTO;
import Impl.AirPortImp;
import Impl.FlightImpl;
import Impl.PassengerImpl;
import Impl.TicketImpl;
import dataAccess.IAirport;
import dataAccess.IFlight;
import dataAccess.IPassenger;
import dataAccess.ITicket;
import model.*;

import javax.activation.DataHandler;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebService
@MTOM
@BindingType(value = SOAPBinding.SOAP11HTTP_MTOM_BINDING)
//@HandlerChain(file= "mainpackage/handler-chain.xml")
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
    public Set<CountryDTO> findAllTheCountriesThatPlanesDepartFrom(){
        IFlight iFlight = new FlightImpl();
        return iFlight.findAllTheCountriesThatPlanesDepartFrom(flightList);
    }

    @WebMethod
    public Set<CountryDTO> findAllCountriesToWhichPlanesDepartFromAgivenCity(String country, String city){
        IFlight iFlight = new FlightImpl();
        return iFlight.findAllCountriesToWhichPlanesDepartFromAgivenCity(flightList, country, city);
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
    public List<Flight> findFlightsBetweenGivenCities(String countryFrom,
                                                      String cityFrom,
                                                      String countryTo,
                                                      String cityTo,
                                                      String flightDate){
        IFlight flight = new FlightImpl();
        return flight.findFlightsBetweenGivenCities((ArrayList<Flight>) flightList,
                 countryFrom,
                 cityFrom,
                 countryTo,
                 cityTo,
                 flightDate);
    }

    @WebMethod
    public Ticket BookFlight(Flight flight, PassengerDTO passenger){
        ITicket iTicket = new TicketImpl();
        List<Ticket> ticketsList = iTicket.bookFlight(passenger, flight, ticketList);
        Ticket ticket = ticketsList.get(ticketsList.size()-1);
        return ticket;
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

    @WebMethod
    public DataHandler printTicketToPdf(Ticket ticket){
        ITicket iTicket = new TicketImpl();
        return  iTicket.printTicketToPdf(ticket);
    }
}
