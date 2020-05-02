package ws;

import Impl.AirPortImp;
import Impl.FlightImpl;
import dataAccess.IAirport;
import dataAccess.IFlight;
import model.Airport;
import model.Flight;

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
}
