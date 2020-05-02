package dataAccess;

import model.Airport;
import model.Flight;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IAirport {

    @WebMethod
    public List<Airport> findAirPortByCity(ArrayList<Airport> airports, String City);

    @WebMethod
    public List<Airport> generateAirPortsList();

    @WebMethod
    public List<Airport> findAirPortByCountry(ArrayList<Airport> airports, String Country);



}
