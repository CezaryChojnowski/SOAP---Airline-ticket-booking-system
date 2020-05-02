package ws;

import Impl.AirPortImp;
import Impl.FlightImpl;
import Impl.PassengerImpl;
import dataAccess.IAirport;
import dataAccess.IFlight;
import dataAccess.IPassenger;
import model.Airport;
import model.Flight;
import model.Passenger;
import model.PassengerDTO;

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

    PassengerImpl passagerImpl = new PassengerImpl();
    List<Passenger> passengerList = passagerImpl.generatePassengerList();


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
    public List<Passenger> register(Passenger passenger){
        IPassenger iPassenger = new PassengerImpl();
        return iPassenger.register(passenger,passengerList);
    }

    @WebMethod
    public PassengerDTO login(String login, String password){
        IPassenger passenger = new PassengerImpl();
        return passenger.login(passengerList, login, password);
    }

}
