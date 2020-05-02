package Impl;

import dataAccess.IFlight;
import error.NoFlightsBetweenTheseCitiesOnThisDayException;
import error.NoFlightsFromThisLocationException;
import model.Airport;
import DTO.CityDTO;
import DTO.CountryDTO;
import model.Flight;

import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@WebService(endpointInterface = "dataAccess.IFlight")
public class FlightImpl implements IFlight {
    public List<Flight> findFlightsBetweenGivenCities(ArrayList<Flight> flights,
                                                      String countryFrom,
                                                      String cityFrom,
                                                      String countryTo,
                                                      String cityTo,
                                                      String flightDate){
        List<Flight> flightsListBetweenGivenCities = flights.
                stream()
                .filter(g -> g.from_AirPort.getCountry().equals(countryFrom))
                .filter(g -> g.from_AirPort.getCity().equals(cityFrom))
                .filter(g -> g.to_AirPort.getCountry().equals(countryTo))
                .filter(g -> g.to_AirPort.getCity().equals(cityTo))
                .filter(g -> g.flightDate.equals(flightDate))
                .collect(Collectors
                        .toList());
        if(flightsListBetweenGivenCities.isEmpty()){
            throw new NoFlightsBetweenTheseCitiesOnThisDayException("No flights between:" +
                    countryFrom +
                    ", " +
                    cityFrom +
                    " - " +
                    countryTo +
                    ", " +
                    countryTo +
                    " on " +
                    flightDate);
        }
        return flightsListBetweenGivenCities;
    }

    public Set<CountryDTO> findAllTheCountriesThatPlanesDepartFrom(List<Flight> flightList){
        Set<CountryDTO> countryList = new HashSet<>();
        for(int i=0; i<flightList.size(); i++){
            Set<CityDTO> citiesSet = new HashSet<>();
            String countryName = flightList.get(i).from_AirPort.getCountry();
            for(int j=0; j<flightList.size(); j++){
                if(flightList.get(j).from_AirPort.getCountry().equals(countryName)) {
                    String cityName = flightList.get(j).from_AirPort.getCity();
                    CityDTO cityDTO = new CityDTO(cityName);
                    citiesSet.add(cityDTO);
                }
            }
            CountryDTO countryDTO = new CountryDTO(countryName, citiesSet);
            countryList.add(countryDTO);
        }
        return countryList;
    }

    public Set<CountryDTO> findAllCountriesToWhichPlanesDepartFromAgivenCity(List<Flight> flightList, String country, String city) {
        Set<CountryDTO> countryList = new HashSet<>();
        for(int i=0; i<flightList.size(); i++) {
            if (flightList.get(i).from_AirPort.getCountry().equals(country)) {
                Set<CityDTO> citiesSet = new HashSet<>();
                String countryName = flightList.get(i).to_AirPort.getCountry();
                    for(int j=0; j<flightList.size(); j++){
                        if(flightList.get(j).to_AirPort.getCountry().equals(countryName) && flightList.get(j).from_AirPort.getCity().equals(city)) {
                            String cityName = flightList.get(j).to_AirPort.getCity();
                            CityDTO cityDTO = new CityDTO(cityName);
                            citiesSet.add(cityDTO);
                        }
                    }
                CountryDTO countryDTO = new CountryDTO(countryName, citiesSet);
                countryList.add(countryDTO);
            }
        }
        if(countryList.isEmpty()){
            throw new NoFlightsFromThisLocationException("No flights from:" + country + ", " + city);
        }
            return countryList;
    }

    public List<Flight> generateFlightsList(List<Airport> airportList) {
        List<Flight> flightList = new ArrayList<Flight>();
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        flightList.add(new Flight(1, airportList.get(0), airportList.get(1), date, 60));
        return flightList;
    }

    public List<Flight> getAllFlights(List<Flight> flightList) {
        return flightList;
    }
}
