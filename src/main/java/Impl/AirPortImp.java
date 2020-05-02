package Impl;

import dataAccess.IAirport;
import model.Airport;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "dataAccess.IAirport")
public class AirPortImp implements IAirport {
    public List<Airport> findAirPortByCity(ArrayList<Airport> airports, String City) {
        List<Airport> airportListByCity = airports.stream().filter(g -> g.city.equals(City)).collect(Collectors.toList());
        return airportListByCity;
    }


    public List<Airport> findAirPortByCountry(ArrayList<Airport> airports, String Country) {
        List<Airport> airportListByCountry = airports.stream().filter(g -> g.city.equals(Country)).collect(Collectors.toList());
        return airportListByCountry;
    }

    public List<Airport> generateAirPortsList() {
        List<Airport> airPortList = new ArrayList<>();
        airPortList.add(new Airport(1,"Poland","Warsaw","Chopin","Franciszka Żwirki i Stanisława Wigury",1));
        airPortList.add(new Airport(2,"Italy","Rome","Rome-Ciampino","Via Appia Nuova",1651));
        airPortList.add(new Airport(3,"France","Paris","Paris-Roissy-Charles de Gaulle","Roissy-en-France",95700 ));
        return airPortList;
    }

}
