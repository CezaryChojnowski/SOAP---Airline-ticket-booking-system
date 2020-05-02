package dataAccess;

import model.Airport;
import model.Flight;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IFlight {

    @WebMethod
    public List<Flight> findFlightsBetweenGivenCities(ArrayList<Flight> flights, Airport from, Airport to);

    @WebMethod
    public List<Flight> generateFlightsList(List<Airport> airportList);

    @WebMethod
    public List<Flight> getAllFlights(List<Flight> flightList);
}
