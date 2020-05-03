package Impl;

import DTO.CityDTO;
import DTO.CountryDTO;
import dataAccess.IAirport;
import dataAccess.IFlight;
import error.NoFlightsBetweenTheseCitiesOnThisDayException;
import error.NoFlightsFromThisLocationException;
import model.Airport;
import model.Flight;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class FlightImplTest {
    private IFlight iFlight = new FlightImpl();
    private IAirport iAirport = new AirPortImp();
    private Set<CountryDTO> countryDTOList = new HashSet<>();
    private List<Airport> airportList = iAirport.generateAirPortsList();
    private List<Flight> flightList = iFlight.generateFlightsList(airportList);
    private List<Flight> flightListBetweenTwoCities = new ArrayList<>();


    @Before
    public void setUp() {
        countryDTOList.add(new CountryDTO("Italy", new HashSet<>(Collections.singleton(new CityDTO("Rome")))));
        countryDTOList.add(new CountryDTO("France", new HashSet<>(Collections.singleton(new CityDTO("Paris")))));
        countryDTOList.add(new CountryDTO("Germany", new HashSet<>(Collections.singleton(new CityDTO("Norymberga")))));
        countryDTOList.add(new CountryDTO("Spain", new HashSet<>(Collections.singleton(new CityDTO("Reus")))));
        countryDTOList.add(new CountryDTO("Portugal", new HashSet<>(Collections.singleton(new CityDTO("Porto")))));
        countryDTOList.add(new CountryDTO("Russia", new HashSet<>(Collections.singleton(new CityDTO("Moscow")))));
        countryDTOList.add(new CountryDTO("USA", new HashSet<>(Collections.singleton(new CityDTO("New york")))));
        countryDTOList.add(new CountryDTO("France", new HashSet<>(Collections.singleton(new CityDTO("Paris")))));
        countryDTOList.add(new CountryDTO("Brasil", new HashSet<>(Collections.singleton(new CityDTO("São Paulo")))));

        flightListBetweenTwoCities.add(new Flight(
                new Airport(
                        "France",
                        "Paris",
                        "Paris-Roissy-Charles de Gaulle",
                        "Roissy-en-France",
                        95700),
                new Airport(
                        "USA",
                        "New york",
                        "LaGuardia",
                        "Queens",
                        11371
                        ),
                "03-05-2020",
                60));

        flightListBetweenTwoCities.add(new Flight(
                new Airport(
                        "France",
                        "Paris",
                        "Beauvais-Tillé",
                        "Route de l'Aéroport",
                        60000),
                new Airport(
                        "USA",
                        "New york",
                        "LaGuardia",
                        "Queens",
                        11371
                ),
                "03-05-2020",
                60));
    }

    @Test
    public void shouldReturnSetWithNineCountriesAndCities(){
        Set<CountryDTO> result = iFlight.findAllTheCountriesThatPlanesDepartFrom(flightList);
        assertEquals(result, countryDTOList);
    }

    @Test
    public void shouldReturnNoFlightsFromThisLocationException(){
        String country = "Poland";
        String city = "Warsaw";
        try{
            Set<CountryDTO> result = iFlight.findAllCountriesToWhichPlanesDepartFromAgivenCity(flightList, country, city);
        }
        catch (NoFlightsFromThisLocationException exception){
            assertEquals("No flights from:" + country + ", " + city, exception.getMessage());
        }
    }

    @Test
    public void shouldReturnListWithTwoFlights(){
        String firstCountry = "USA";
        String firstCity = "New york";
        String secondCountry = "France";
        String secondCity = "Paris";
        String date = "03-05-2020";
        List<Flight> result = iFlight.findFlightsBetweenGivenCities(
                (ArrayList<Flight>) flightList,
                firstCountry,
                firstCity,
                secondCountry,
                secondCity,
                date);
        assertEquals(result, flightListBetweenTwoCities);
    }

    @Test
    public void shouldReturnNoFlightsBetweenTheseCitiesOnThisDayException(){
        String firstCountry = "USA";
        String firstCity = "New york";
        String secondCountry = "Poland";
        String secondCity = "Radom";
        String date = "03-05-2020";
        try{
            List<Flight> result = iFlight.findFlightsBetweenGivenCities(
                    (ArrayList<Flight>) flightList,
                    firstCountry,
                    firstCity,
                    secondCountry,
                    secondCity,
                    date);        }
        catch (NoFlightsBetweenTheseCitiesOnThisDayException exception){
            assertEquals("No flights between:" +
                    firstCountry +
                    ", " +
                    firstCity +
                    " - " +
                    secondCountry +
                    ", " +
                    secondCity +
                    " on " +
                    date, exception.getMessage());
        }
    }

}
