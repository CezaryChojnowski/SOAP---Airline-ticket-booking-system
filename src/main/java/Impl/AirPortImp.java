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
        List<Airport> airportList = new ArrayList<>();
        airportList.add(new Airport("Poland","Warsaw","Chopin","Franciszka Żwirki i Stanisława Wigury",1));
        airportList.add(new Airport("Italy","Rome","Rome-Ciampino","Via Appia Nuova",1651));
        airportList.add(new Airport("France","Paris","Paris-Roissy-Charles de Gaulle","Roissy-en-France",95700 ));
        airportList.add(new Airport("Germany","Norymberga","Nürnberg","Flughafenstraße",100));
        airportList.add(new Airport("Spain","Reus","de Reus","Av. de Tarragona",43204));
        airportList.add(new Airport("Portugal","Porto","Francisco Sá Carneiro","Maia",4470 ));
        airportList.add(new Airport("Russia","Moscow","Шереметьево - аэропорт имени","Khimki",1414));
        airportList.add(new Airport("USA","New york","LaGuardia","Queens",11371));
        airportList.add(new Airport("France","Paris","Beauvais-Tillé","Route de l'Aéroport",60000 ));
        airportList.add(new Airport("Brasil","São Paulo","São Paulo-Guarulhos","Rod.Helio Smidt",719));
        return airportList;
    }

}
