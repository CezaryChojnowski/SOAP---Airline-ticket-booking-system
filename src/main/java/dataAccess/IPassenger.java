package dataAccess;

import model.Passenger;
import model.PassengerDTO;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IPassenger {

    public List<Passenger> register(Passenger passenger, List<Passenger> passengerList);

    public PassengerDTO login(List<Passenger> passengerList, String login, String password);

    public List<Passenger> generatePassengerList();

}
