package Impl;

import dataAccess.IFlight;
import model.Airport;
import model.Flight;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "dataAccess.IFlight")
public class FlightImpl implements IFlight {
    public List<Flight> findFlightsBetweenGivenCities(ArrayList<Flight> flights, Airport from, Airport to) {
        List<Flight> flightsListBetweenGivenCities = flights.
                stream()
                .filter(g -> g.to_AirPort.equals(to))
                .filter(g -> g.from_AirPort.equals(from))
                .collect(Collectors
                        .toList());
        return flightsListBetweenGivenCities;
    }

    public List<Flight> generateFlightsList(List<Airport> airportList) {
        List<Flight> flightList = new ArrayList<Flight>();
        flightList.add(new Flight(1, airportList.get(0), airportList.get(1), new Date(2020, 11, 21), 60));
        return flightList;
    }

    public List<Flight> getAllFlights(List<Flight> flightList) {
        return flightList;
    }
}
