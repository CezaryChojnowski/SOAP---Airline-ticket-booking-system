package dataAccess;

import model.Airport;
import model.Flight;

import java.util.List;

public interface IFlight {

    public List<Flight> findFlights(Airport from, Airport to);
}
