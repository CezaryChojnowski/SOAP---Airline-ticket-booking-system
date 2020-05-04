package Impl;

import dataAccess.IPassenger;
import error.InvalidLoginDataException;
import error.UserWithGivenLoginOrEmailExistsException;
import model.Passenger;
import DTO.PassengerDTO;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "dataAccess.IPassenger")
public class PassengerImpl implements IPassenger {

    public List<Passenger> register(Passenger passenger, List<Passenger> passengerList) {
        List<Passenger> passengers = passengerList
                .stream()
                .filter(u -> u.login.equals(passenger.login) || u.email.equals(passenger.email))
                .collect(Collectors
                .toList());
        if(!passengers.isEmpty()){
            throw new UserWithGivenLoginOrEmailExistsException("User with the given data exists: login: " + passenger.login + ", email: " + passenger.email + ". Please change login or email");
        }
        passengerList.add(passenger);
        return passengerList;
    }

    public PassengerDTO login(List<Passenger> passengerList, String login, String password) {
        List<Passenger> passengers = passengerList
                .stream()
                .filter(p -> p.login.equals(login))
                .filter(p -> p.password.equals(password))
                .collect(Collectors.toList());
        if (passengers.isEmpty()){
            throw new InvalidLoginDataException("Invalid login or password");
        }else{
            Passenger passenger = passengers.get(0);
            PassengerDTO passengerDTO = new PassengerDTO(passenger.getName(), passenger.getSurname(), passenger.getEmail());
            return passengerDTO;
        }
    }

    public List<Passenger> generatePassengerList() {
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger("Temp", "Temp", "Temp@temp.temp", "temp", "temp"));
        return passengers;
    }
}
