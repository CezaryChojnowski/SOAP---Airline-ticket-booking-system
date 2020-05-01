package dataAccess;

import model.Airport;
import model.Flight;

import java.util.List;

public interface IAirport {

    public List<Airport> findAirPortByCity(String City);

}
