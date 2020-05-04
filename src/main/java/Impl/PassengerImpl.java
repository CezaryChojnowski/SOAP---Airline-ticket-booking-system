package Impl;

import dataAccess.IPassenger;
import error.AllFieldsMustBeCompletedExpcetion;
import error.InvalidLoginDataException;
import error.UserWithGivenLoginOrEmailExistsException;
import model.Passenger;
import DTO.PassengerDTO;

import javax.jws.WebService;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        if(!isValid(passenger)){
            throw new AllFieldsMustBeCompletedExpcetion("All fields must be completed");
        }
        String hashPassword = hashPassword(passenger.getPassword());
        passenger.setPassword(hashPassword);
        passengerList.add(passenger);
        return passengerList;
    }

    public String hashPassword(String password)
        {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-512");
                byte[] messageDigest = md.digest(password.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                String hashtext = no.toString(16);
                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }
                return hashtext;
            }
            catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

    public boolean isValid(Passenger passenger){
        if(passenger.getName().isEmpty() || passenger.getSurname().isEmpty() || passenger.getEmail().isEmpty() || passenger.getLogin().isEmpty() || passenger.getPassword().isEmpty()){
            return false;
        }
        return true;
    }

    public PassengerDTO login(List<Passenger> passengerList, String login, String password) {
        String hashPassword = hashPassword(password);
        List<Passenger> passengers = passengerList
                .stream()
                .filter(p -> p.login.equals(login))
                .filter(p -> p.password.equals(hashPassword))
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
