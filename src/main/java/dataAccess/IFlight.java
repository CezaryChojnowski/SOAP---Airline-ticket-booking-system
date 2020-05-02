package dataAccess;

import model.Airport;
import DTO.CountryDTO;
import model.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IFlight {

    @WebMethod
    public List<Flight> findFlightsBetweenGivenCities(ArrayList<Flight> flights,
                                                      String countryFrom,
                                                      String cityFrom,
                                                      String countryTo,
                                                      String cityTo,
                                                      String flightDate);
    @WebMethod
    public List<Flight> generateFlightsList(List<Airport> airportList);

    @WebMethod
    public List<Flight> getAllFlights(List<Flight> flightList);

    @WebMethod
    public Set<CountryDTO> findAllTheCountriesThatPlanesDepartFrom(List<Flight> flightList);

    @WebMethod
    public Set<CountryDTO> findAllCountriesToWhichPlanesDepartFromAgivenCity(List<Flight> flightList, String country, String city);

}
